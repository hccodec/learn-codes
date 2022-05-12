using CCWin;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PCPreferencesTaskSystem
{
	public partial class DatabaseForm : Skin_DevExpress
	{
		public DatabaseForm()
		{
			InitializeComponent();
		}

		private void DatabaseForm_Load(object sender, EventArgs e)
		{
			try
			{
				// TODO: 这行代码将数据加载到表“swjtuDataSet.电磁学期中成绩”中。您可以根据需要移动或删除它。
				this.电磁学期中成绩TableAdapter.Fill(this.swjtuDataSet.电磁学期中成绩);
			}catch (Exception e)
			{
				MessageBox.Show(e.ToString(), "数据库打开失败");
			}

		}
	}
}
