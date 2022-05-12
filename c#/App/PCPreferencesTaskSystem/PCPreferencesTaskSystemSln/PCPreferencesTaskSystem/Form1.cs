using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using CCWin;

namespace PCPreferencesTaskSystem
{
	public partial class WelcomeForm : Skin_DevExpress
	{
		public WelcomeForm()
		{
			InitializeComponent();
		}

		private void 退出XToolStripMenuItem_Click(object sender, EventArgs e)
		{
			DialogResult s = MessageBox.Show("您确定要退出吗？", "退出提示", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
			if (s == DialogResult.Yes) Environment.Exit(0);
		}

		private void 关于AToolStripMenuItem_Click(object sender, EventArgs e)
		{
			MessageBox.Show("项目负责人：Jack Sparrow\n项目团队：+科技实验团队\n联系电话：290-66556225\n电子邮箱：99287743@qq.com","关于系统",MessageBoxButtons.OK,MessageBoxIcon.Information);
		}

		private void button1_Click(object sender, EventArgs e)
		{
			openFileDialog1.Title = "hbj";
			openFileDialog1.FileName = "";
			openFileDialog1.Filter = "所有文件(*.*)|*.*|学术性文件：Matlab(*.m))|*.m|文本文件(*.txt;*.rtf)|*.txt;*.rtf|多媒体文件(*.mp4;*.mp3;*.acc;*.jpg;*.png;*.bmp;*.psd;)|*.jpg;*.png|图标文件(*.ico)|*.ico";
			openFileDialog1.ShowDialog();
			string s = openFileDialog1.GetType().ToString();
			MessageBox.Show(s);
		}

		private void button2_Click(object sender, EventArgs e)
		{
			IWin32Window win = new NativeWindow();
			Console.WriteLine("hfvdhfvldnlvdfv");
			Console.WriteLine(win.Handle.GetType());
		}

		private void button3_Click(object sender, EventArgs e)
		{
			System.Diagnostics.Process.Start("chrome.exe", "http://jwc.swjtu.edu.cn/service/login.html");
		}

		private void button4_Click(object sender, EventArgs e)
		{
			new DatabaseForm().Show();
		}

		private void 新建NToolStripMenuItem_Click(object sender, EventArgs e)
		{
			new WelcomeForm().Show();
		}

		private void WelcomeForm_FormClosing(object sender, FormClosingEventArgs e)
		{
			DialogResult s = MessageBox.Show("您确定要退出吗？", "退出提示", MessageBoxButtons.YesNo, MessageBoxIcon.Question);
			if (s == DialogResult.Yes) Environment.Exit(0);
			else e.Cancel = true;
		}

		private void WelcomeForm_Load(object sender, EventArgs e)
		{

		}
	}
}
