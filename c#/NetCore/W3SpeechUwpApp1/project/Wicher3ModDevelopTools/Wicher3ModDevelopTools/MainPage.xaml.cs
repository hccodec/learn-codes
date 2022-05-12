using OfficeOpenXml;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Text;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Media.Core;
using Windows.Media.Playback;
using Windows.Storage;
using Windows.Storage.Pickers;
using Windows.System;
using Windows.UI;
using Windows.UI.Core;
using Windows.UI.Popups;
using Windows.UI.ViewManagement;
using Windows.UI.WindowManagement;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Hosting;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;

// https://go.microsoft.com/fwlink/?LinkId=402352&clcid=0x804 上介绍了“空白页”项模板

namespace Wicher3ModDevelopTools
{
	/// <summary>
	/// 可用于自身或导航至 Frame 内部的空白页。
	/// </summary>
	public sealed partial class MainPage : Page
	{
		private readonly Tools tools;
		private StorageFolder folder;
		public MainPage()
		{
			this.InitializeComponent();
			this.NavigationCacheMode = NavigationCacheMode.Enabled;
			myStoryboard.Begin();
			tools = new Tools(this);
			Window.Current.CoreWindow.Dispatcher.AcceleratorKeyActivated += Dispatcher_AcceleratorKeyActivated;
			if (string.IsNullOrEmpty(string.Join(",", ApplicationData.Current.RoamingSettings.Values)))
				tools.Log("没有存储项");
			else tools.Log(string.Join(",", ApplicationData.Current.RoamingSettings.Values));
			init();
		}
		private async void init()
		{
			try
			{
				folder = await StorageFolder.GetFolderFromPathAsync(tools.Load("audioFolder").ToString());
			}
			catch (Exception e)
			{
				tools.Log(e.Message.ToString());
			}

			judgeFolder(folder != null);
			judgeSound(ElementSoundPlayer.State == ElementSoundPlayerState.On);

		}
		private void Dispatcher_AcceleratorKeyActivated(CoreDispatcher sender, AcceleratorKeyEventArgs args)
		{
			if (args.EventType.ToString().Contains("KeyUp"))
			{
				VirtualKey virtualKey = args.VirtualKey;

				switch (virtualKey)
				{
					case VirtualKey.Enter:
						{
							tools.Log("按了回车键, Combox 的聚焦状态为：" + audioName.FocusState.ToString());
							//PlayAudio(audioName);
							PlayAudio();
							break;
						}
					default:
						{
							break;
						}
				}
			}
		}

		private async void PlayAudio()
		{
			if (folder == null)
				await tools.DisplaySimpleDialog(new string[] { "尚未选择音频文件文件夹，请选择目标文件夹" });
			else
				try
				{
					tools.Log(folder.DisplayName);
					var filePath = string.IsNullOrEmpty(audioName.Text) ? "0x0012dd9.wav.ogg" : $"{audioName.Text}.wav.ogg";
					tools.Log($"文件名是 {filePath}");
					StorageFile file = await folder.GetFileAsync("ogg\\" + filePath);
					if (file == null)
					{
						tools.Log("音频文件不要了.");
					}
					else
					{
						// Application now has read/write access to the picked file

						tools.PlayAudio(file);
					}
				}
				catch (Exception exception)
				{
					tools.DisplayMessage("异常：" + exception);
					tools.Log(exception.StackTrace);
				}
		}

		private async void Speak_Click(object sender, RoutedEventArgs e)
		{
			await Tools.Speak("Speak");
		}

		private void GoToPage1Button_Click(object sender, RoutedEventArgs e)
		{
			this.Frame.Navigate(typeof(Page1), "Geralt");

		}

		private async void Play_Click(object sender, RoutedEventArgs args)
		{
			if (folder == null)
				await tools.DisplaySimpleDialog(new string[] { "尚未选择音频文件文件夹，请选择目标文件夹" });
			else
				try
				{
					StorageFile file = await StorageFile.GetFileFromApplicationUriAsync(new Uri("ms-appx:///ex.xlsx"));
					tools.Log("11111111111111111111111111"); ;
					//FileInfo fif = new FileInfo(folder.Path + "\\ex.xlsx");
					tools.Log(file.Path);
					var p = await file.GetBasicPropertiesAsync();

					StringBuilder fileProperties = new StringBuilder();
					string fileSize = string.Format("{0:n0}", p.Size);
					fileProperties.AppendLine("File size: " + fileSize + " bytes");
					fileProperties.AppendLine("Date modified: " + p.DateModified);

					// Define property names to be retrieved.
					var propertyNames = new List<string>();
					propertyNames.Add("System.DateAccessed");
					propertyNames.Add("System.FileOwner");
					var extraP = await p.RetrievePropertiesAsync(propertyNames);
					var propValue = extraP["System.DateAccessed"];
					if (propValue != null)
					{
						fileProperties.AppendLine("Date accessed: " + propValue);
					}

					// Get file-owner property.
					propValue = extraP["System.FileOwner"];
					if (propValue != null)
					{
						fileProperties.AppendLine("File owner: " + propValue);
					}


					tools.Log(fileProperties.ToString());
					FileInfo fif = new FileInfo(file.Name);
					tools.Log("下一句应该是1111111");

					tools.Log("这里是什么地方");
					//tools.Log(fif.IsReadOnly.ToString());

					ExcelPackage.LicenseContext = LicenseContext.NonCommercial;
					//using (var package = new ExcelPackage(fif))
					//{
					//	var sheet = package.Workbook.Worksheets.Add("My Sheet");
					//	sheet.Cells["A1"].Value = "Hello World!";

					//	// Save to file
					//	package.Save();
					//}

					//查询指定工作表
					ExcelPackage excel = new ExcelPackage(fif);
					ExcelWorksheets sheets = excel.Workbook.Worksheets;
					ExcelWorksheet sheet = sheets.FirstOrDefault();
					tools.Log("11111111111111111111111111");
					tools.Log("BINGO11111111111111111111111");
					string result = sheet.Cells[1, 1].Text.ToString();
					tools.Log("BINGO22222222222222222222222222222");
					tools.DisplayMessage("第一次读EXCEL的结果" + result);
					/*
					StringBuilder sb = new StringBuilder();
					sb.AppendLine(string.Format("文件创建时间：{0}", fif.CreationTime.ToString()));
					tools.Log("22222222222222222222222222");
					sb.AppendLine(string.Format("文件最后一次读取时间：{0}", fif.LastAccessTime.ToString()));
					sb.AppendLine(string.Format("文件最后一次修改时间：{0}", fif.LastWriteTime.ToString()));
					sb.AppendLine(string.Format("文件创建时间(UTC)：{0}", fif.CreationTimeUtc.ToString()));
					sb.AppendLine(string.Format("文件最后一次读取时间(UTC)：{0}", fif.LastAccessTimeUtc.ToString()));
					sb.AppendLine(string.Format("文件最后一次修改时间(UTC)：{0}", fif.LastWriteTimeUtc.ToString()));
					sb.AppendLine(string.Format("文件目录：{0}", fif.Directory));
					////sb.AppendLine(string.Format("文件目录名称：{0}", fif.DirectoryName));
					sb.AppendLine(string.Format("文件扩展名：{0}", fif.Extension));
					sb.AppendLine(string.Format("文件完整名称：{0}", fif.FullName));
					sb.AppendLine(string.Format("文件名：{0}", fif.Name));
					sb.AppendLine(string.Format("文件字节长度：{0}", fif.Length));
					//tools.Log(sb.ToString());*/
				}
				catch (Exception e)
				{
					tools.Log("又出问题啦===============");
					tools.Log(e.ToString());
					tools.Log("=========================");
				}
		}

		private async void SelectFolder_Click(object sender, RoutedEventArgs e)
		{
			if (folder == null)
			{
				folder = await tools.SelectFolder();
				if (folder != null)
				{
					tools.Save("audioFolder", folder.Path.ToString()); //音频文件夹选好并储存
					judgeFolder(true);
				}
			}
			else
			{
				var result = await tools.DisplaySimpleDialog(new string[] {
					"您已选择音频文件夹,要重新指定音频文件夹位置吗", "",
					"不了不了", "重新指定", "删掉原来指定的文件夹"});
				if (result == ContentDialogResult.Primary) await tools.SelectFolder();
				else if (result == ContentDialogResult.Secondary)
				{
					tools.Delete("audioFolder");
					folder = null;
					judgeFolder(false);
				}
				else tools.Log("音频文件夹未改变");
			}
		}

		private void judgeFolder(bool doesExist)
		{
			if (doesExist)
			{
				folderSelection.Label = "文件夹: " + folder.Name;
				folderSelection.Foreground = new SolidColorBrush(Colors.Green);
				folderSelection.Icon = new SymbolIcon(Symbol.Accept);
				folderSelection.Background = null;
			}
			else
			{
				folderSelection.Label = "选择文件夹";
				folderSelection.Background = new SolidColorBrush(Colors.Red);
				folderSelection.Foreground = new SolidColorBrush(Colors.White);
				folderSelection.Icon = new SymbolIcon(Symbol.Help);
			}
		}

		private void judgeSound(bool isOn)
		{
			tools.Log("onbsrotn: " + isOn);
			if (isOn)
			{
				audioToggleBtn.Label = "提示音已开";
				audioToggleBtn.Foreground = new SolidColorBrush(Colors.Black);
				ElementSoundPlayer.State = ElementSoundPlayerState.On;
			}
			else
			{
				audioToggleBtn.Label = "提示音已关";
				audioToggleBtn.Foreground = new SolidColorBrush(Colors.Gray);
				ElementSoundPlayer.State = ElementSoundPlayerState.Off;
			}

		}

		private void audioToggleBtn_Click(object sender, RoutedEventArgs e)
		{
			judgeSound(audioToggleBtn.IsChecked ?? false);
		}
	}
}
