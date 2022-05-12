using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.Foundation.Metadata;
using Windows.Media.Core;
using Windows.UI.WindowManagement;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Hosting;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using static Wicher3ModDevelopTools.Tools;

// https://go.microsoft.com/fwlink/?LinkId=234238 上介绍了“空白页”项模板

namespace Wicher3ModDevelopTools
{
	/// <summary>
	/// 可用于自身或导航至 Frame 内部的空白页。
	/// </summary>
	public sealed partial class Page1 : Page
	{
		readonly Tools tools;
		public AppWindow Page1Window { get; set; }

		public Page1()
		{
			this.InitializeComponent();
			tools = new Tools(this);
			Windows.System.VirtualKey[] keys = new Windows.System.VirtualKey[]{
				Windows.System.VirtualKey.Back,
				Windows.System.VirtualKey.Escape,
				Windows.System.VirtualKey.Left
			};
			tools.AddGoBackKeys(keys);
			PointerPressed += Page1_PointerPressed;
			tools.PlayAudio(MediaSource.CreateFromUri(new Uri("ms-appx:///0x00122dd9.wav.ogg")));
		}

		private void Page1_PointerPressed(object sender, PointerRoutedEventArgs e)
		{
			if (e.GetCurrentPoint(sender as UIElement).Properties.PointerUpdateKind == Windows.UI.Input.PointerUpdateKind.XButton1Pressed)
			{
				e.Handled = tools.On_BackRequested();
			}
		}
		protected override void OnNavigatedTo(NavigationEventArgs e)
		{
			if (e.Parameter is string && !string.IsNullOrWhiteSpace((string)e.Parameter))
				greeting.Text = $"你好， {e.Parameter}";
			else
				greeting.Text = "Hi!!!!";
			base.OnNavigatedTo(e);
		}
		private void GoBack_Click(object sender, RoutedEventArgs e) => tools.On_BackRequested();
		public static ContentDialog CurrentDialog { get; set; } = null;
		private async void Button_Click(object sender, RoutedEventArgs e)
		{
			tools.PlayAudio(MediaSource.CreateFromUri(new Uri("ms-appx:///0x00122dd9.wav.ogg")));
			await tools.DisplaySimpleDialog(new string[] { "信息", "点击了" });
			if (CurrentDialog != null) CurrentDialog.Hide();
			CurrentDialog = null;
		}
	}
}