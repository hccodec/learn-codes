using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;



namespace Wicher3ModDevelopTools
{

	public enum DialogResult
	{
		DialogCancel,
		DialogFail,
		DialogOK
	}

	/// <summary>
	/// 用法：
	/// CustomContentDialog DialogDialog = new CustomContentDialog(content);
	/// await DialogDialog.ShowAsync();
	/// 
	/// https://go.microsoft.com/fwlink/?LinkId=234238 上介绍了“内容对话框”项模板
	/// </summary>
	public partial class CustomContentDialog : ContentDialog
	{
		public DialogResult Result { get; private set; }

		readonly Tools tools;
		readonly string content;

		public CustomContentDialog(string body)
		{
			this.InitializeComponent();
			tools = new Tools(this);
			content = body;
			this.Opened += CustomContentDialog_Opened;
			this.Closing += CustomContentDialog_Closing;
		}

		void CustomContentDialog_Closing(ContentDialog sender, ContentDialogClosingEventArgs args) => tools.Log(Result.ToString());

		void CustomContentDialog_Opened(ContentDialog sender, ContentDialogOpenedEventArgs args)
		{
			body.Text = content ?? "[无信息]";
		}
	}
}