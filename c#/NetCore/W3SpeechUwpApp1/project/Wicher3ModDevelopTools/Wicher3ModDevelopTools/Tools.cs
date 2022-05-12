using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Windows.Foundation.Metadata;
using Windows.Media.Core;
using Windows.Media.Playback;
using Windows.Storage;
using Windows.Storage.Pickers;
using Windows.System;
using Windows.UI.Popups;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Input;

namespace Wicher3ModDevelopTools
{
	class Tools
	{
		public object CurrentPage { get; set; }


		public Tools(object page)
		{
			CurrentPage = page;
		}

		public void Log(string content)
		{
			try { Debug.WriteLine($"宝佳：[{CurrentPage}] {content}"); }
			catch (Exception e) { Debug.WriteLine($"[!!!Tools.Log!!!] {e.Message}"); }
		}

		private static void SelfLog(string text)
		{
			Debug.WriteLine($"宝佳：[Tools SELF!!] {text}");
		}

		public static void Print(string content) => Debug.WriteLine($"宝佳：[Print] {content}");
		public static async Task Speak(string text)
		{
			MediaElement mediaElement = new MediaElement();
			var synth = new Windows.Media.SpeechSynthesis.SpeechSynthesizer();
			Windows.Media.SpeechSynthesis.SpeechSynthesisStream stream = await synth.SynthesizeTextToStreamAsync(text);
			mediaElement.SetSource(stream, stream.ContentType);
			mediaElement.Play();
			SelfLog($"  刚才说了 \"{text}\"");
		}
		public void AddGoBackKeys(VirtualKey[] keys)
		{
			foreach (VirtualKey key in keys)
			{
				KeyboardAccelerator accelerator = new KeyboardAccelerator { Key = key };
				accelerator.Invoked += BackInvoked;
				if (key == VirtualKey.Left)
				{
					accelerator.Modifiers = VirtualKeyModifiers.Menu;
				}
				((Page)CurrentPage).KeyboardAccelerators.Add(accelerator);
			}
		}

		/// <summary>
		/// 处理系统级BackRequested事件和页面级back按钮单击事件
		/// </summary>
		/// <param name="sender"></param>
		/// <param name="args"></param>
		private void BackInvoked(KeyboardAccelerator sender, KeyboardAcceleratorInvokedEventArgs args)
		{
			On_BackRequested();
			args.Handled = true;
		}
		public bool On_BackRequested()
		{
			if (((Page)CurrentPage).Frame.CanGoBack)
			{
				((Page)CurrentPage).Frame.GoBack();
				return true;
			}
			return false;
		}

		/// <summary>
		/// 显示简单的对话框
		/// </summary>
		/// <param name="sender">当前控件的sender</param>
		/// <param name="vs">内容{内容、标题、关闭按钮、主要按钮、次要按钮}</param>
		public async Task<ContentDialogResult> DisplaySimpleDialog(string[] vs)
		{
			string[] args = new string[] { "", "", "", "", "" };
			for (int i = 0; i < vs.Length; i++) args[i] = vs[i];
			if (args[1] == "") args[1] = "提示";
			if (args[2] == "") args[2] = "知道了";
			ContentDialog simpleDialog = new ContentDialog
			{
				Content = args[0],
				Title = args[1],
				CloseButtonText = args[2],
				PrimaryButtonText = args[3],
				SecondaryButtonText = args[4],
				DefaultButton = ContentDialogButton.Close
			};
			// Use this code to associate the dialog to the appropriate AppWindow by setting
			// the dialog's XamlRoot to the same XamlRoot as an element that is already present in the AppWindow.
			try
			{
				if (ApiInformation.IsApiContractPresent("Windows.Foundation.UniversalApiContract", 8))
					simpleDialog.XamlRoot = (CurrentPage as UIElement).XamlRoot;
			}
			catch (Exception e)
			{
				Log(e.Message);
			}
			return await simpleDialog.ShowAsync();
		}

		/// <summary>
		/// 显示消息提示框
		/// </summary>
		/// <param name="vs">内容{标题、内容、确定、取消}</param>
		public async Task<IUICommand> DisplayMessageDialog(string[] vs)
		{
			string[] args = new string[] { "", "", "", "" };
			for (int i = 0; i < vs.Length; i++) args[i] = vs[i];
			if (args[2] == "") args[2] = "确定";
			if (args[3] == "") args[3] = "取消";

			var dialog = new MessageDialog(args[0], args[1]);

			dialog.Commands.Add(new UICommand(args[2], cmd => { }, commandId: 0));
			dialog.Commands.Add(new UICommand(args[3], cmd => { }, commandId: 1));

			//设置默认按钮，不设置的话默认的确认按钮是第一个按钮
			dialog.DefaultCommandIndex = 0;
			dialog.CancelCommandIndex = 1;

			//获取返回值
			return await dialog.ShowAsync();
		}

		/// <summary>
		/// 显示简单的消息提示框
		/// </summary>
		/// <param name="s">内容</param>
		public async void DisplayMessage(string s)
		{
			var dialog = new MessageDialog(s, $"来自 {CurrentPage.ToString()} 的报错信息");
			dialog.Commands.Add(new UICommand("知道了", cmd => { }, commandId: 0));

			//设置默认按钮，不设置的话默认的确认按钮是第一个按钮
			dialog.DefaultCommandIndex = 0;

			//获取返回值
			IUICommand result = await dialog.ShowAsync();
		}

		/// <summary>
		/// 存储设置
		/// </summary>
		/// <param name="key">设置名</param>
		/// <param name="value">设置值</param>
		public void Save(string key, object value)
		{
			Windows.Storage.ApplicationDataContainer roamingSettings = Windows.Storage.ApplicationData.Current.RoamingSettings;
			roamingSettings.Values[key] = value;
			Log($"[Settings Save] 已保存 “{key}”：“{value}”。");
		}

		/// <summary>
		/// 载入设置项
		/// </summary>
		/// <param name="key">设置名</param>
		public object Load(string key)
		{
			ApplicationDataContainer roamingSettings = ApplicationData.Current.RoamingSettings;
			if (roamingSettings.Values.ContainsKey(key))
			{
				Log($"[Settings Load] 已读取储存项： {key}: \"{roamingSettings.Values[key]}\" ");
				return roamingSettings.Values[key].ToString();
			}
			else Log($"[Settings Load] 尚未存储“{key}”这一项。");
			return null;
		}

		/// <summary>
		/// 删除设置
		/// </summary>
		/// <param name="key">设置名</param>
		public bool Delete(string key)
		{
			ApplicationDataContainer roamingSettings = ApplicationData.Current.RoamingSettings;
			if (roamingSettings.Values[key] != null)
			{
				roamingSettings.Values[key] = null;
				SelfLog($" “{key}”已删除。");
				return true;
			}
			else
				SelfLog($" “{key}”已删除。");
			return false;

		}

		[Obsolete]
		/// <summary>
		/// 蜜汁运行cmd命令
		/// </summary>
		/// <param name="command">命令字符串</param>
		/// <returns></returns>
		public static string RunCmd(string command)
		{
			Process p = new Process();
			p.StartInfo.FileName = "cmd.exe"; //待执行的文件路径
			p.StartInfo.UseShellExecute = false; //重定向输出，这个必须为false
			p.StartInfo.RedirectStandardError = true; //重定向错误流
			p.StartInfo.RedirectStandardInput = true; //重定向输入流
			p.StartInfo.RedirectStandardOutput = true; //重定向输出流
			p.StartInfo.CreateNoWindow = true; //不启动cmd黑框框
			p.Start();
			StreamWriter writer = p.StandardInput;
			writer.WriteLine(string.IsNullOrEmpty(command) ? "help" : command);
			writer.AutoFlush = true;
			p.StandardInput.WriteLine("exit");// 前面一个命令不管是否执行成功都执行后面(exit)命令，如果不执行exit命令，后面调用ReadToEnd()方法会假死

			string strRst = p.StandardOutput.ReadToEnd(); //获取cmd处理输出信息
			string error = p.StandardError.ReadToEnd(); //获取错误信息
														//new Tools(this).DisplaySimpleDialog(sender, new string[] {"返回的信息",strRst + "\n错误信息:" + error});
			p.WaitForExit();  //等待程序执行完退出进程
			p.Close();
			var result = strRst + (string.IsNullOrEmpty(error) ? "" : "\n发生错误: " + error);
			SelfLog(result);
			return result;
		}

		/// <summary>
		/// 播放音频
		/// </summary>
		/// <param name="source">音频源</param>
		public void PlayAudio(StorageFile file)
		{
			Tools.SelfLog(file.Path);
			var source = MediaSource.CreateFromStorageFile(file);
			MediaPlayer MediaPlayer = new MediaPlayer();
			MediaPlayer.Source = source;
			MediaPlayer.Play();
			MediaPlayer.MediaEnded += delegate
			{
				Log("播放完毕");
				MediaPlayer.Dispose();
			};
		}
		/// <summary>
		/// 播放音频
		/// </summary>
		/// <param name="source">音频源</param>
		public void PlayAudio(MediaSource source)
		{
			MediaPlayer MediaPlayer = new MediaPlayer();
			MediaPlayer.Source = source;
			MediaPlayer.Play();
			MediaPlayer.MediaEnded += delegate
			{
				Log("播放完毕");
				MediaPlayer.Dispose();
			};
		}

		public async Task<StorageFolder> SelectFolder()
		{

			var folderPicker = new FolderPicker();
			folderPicker.SuggestedStartLocation = PickerLocationId.Desktop;
			folderPicker.FileTypeFilter.Add("*");

			StorageFolder folder = await folderPicker.PickSingleFolderAsync();
			if (folder == null) Log("文件夹你不选啦.");
			else
			{
				// Application now has read/write access to all contents in the picked folder
				// (including other sub-folder contents)
				Windows.Storage.AccessCache.StorageApplicationPermissions.FutureAccessList.AddOrReplace("PickedFolderToken", folder);
				Log("已经选取文件夹: " + folder.Path);
				return folder;
			}
			return null;
		}

		public async Task<StorageFile> SelectFile(string[] fileTypes)
		{
			StorageFile file = null;
			try
			{
				var filePicker = new FileOpenPicker();
				foreach (string fileType in fileTypes) filePicker.FileTypeFilter.Add(fileType);
				filePicker.SuggestedStartLocation = PickerLocationId.ComputerFolder;
				file = await filePicker.PickSingleFileAsync();
				return file;
			}
			catch (Exception e)
			{
				SelfLog("[SelectFile]" + e);
			}
			return file;
		}

	}
}
