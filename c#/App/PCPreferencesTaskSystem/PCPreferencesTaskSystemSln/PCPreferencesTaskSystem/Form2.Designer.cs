﻿namespace PCPreferencesTaskSystem
{
	partial class DatabaseForm
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.components = new System.ComponentModel.Container();
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(DatabaseForm));
			this.dataGridView1 = new System.Windows.Forms.DataGridView();
			this.学号DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.姓名DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.平时作业1DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.平时作业3DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.平时作业4DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.考试DataGridViewTextBoxColumn = new System.Windows.Forms.DataGridViewTextBoxColumn();
			this.电磁学期中成绩BindingSource = new System.Windows.Forms.BindingSource(this.components);
			this.swjtuDataSet = new PCPreferencesTaskSystem.swjtuDataSet();
			this.电磁学期中成绩TableAdapter = new PCPreferencesTaskSystem.swjtuDataSetTableAdapters.电磁学期中成绩TableAdapter();
			this.menuStrip1 = new System.Windows.Forms.MenuStrip();
			this.文件FToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.新建NToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.打开OToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator = new System.Windows.Forms.ToolStripSeparator();
			this.保存SToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.另存为AToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
			this.打印PToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.打印预览VToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator2 = new System.Windows.Forms.ToolStripSeparator();
			this.退出XToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.编辑EToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.撤消UToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.重复RToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator3 = new System.Windows.Forms.ToolStripSeparator();
			this.剪切TToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.复制CToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.粘贴PToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator4 = new System.Windows.Forms.ToolStripSeparator();
			this.全选AToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.工具TToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.自定义CToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.选项OToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.帮助HToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.内容CToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.索引IToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.搜索SToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			this.toolStripSeparator5 = new System.Windows.Forms.ToolStripSeparator();
			this.关于AToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
			((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.电磁学期中成绩BindingSource)).BeginInit();
			((System.ComponentModel.ISupportInitialize)(this.swjtuDataSet)).BeginInit();
			this.menuStrip1.SuspendLayout();
			this.SuspendLayout();
			// 
			// dataGridView1
			// 
			this.dataGridView1.AutoGenerateColumns = false;
			this.dataGridView1.AutoSizeColumnsMode = System.Windows.Forms.DataGridViewAutoSizeColumnsMode.Fill;
			this.dataGridView1.AutoSizeRowsMode = System.Windows.Forms.DataGridViewAutoSizeRowsMode.AllCells;
			this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
			this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] {
            this.学号DataGridViewTextBoxColumn,
            this.姓名DataGridViewTextBoxColumn,
            this.平时作业1DataGridViewTextBoxColumn,
            this.平时作业3DataGridViewTextBoxColumn,
            this.平时作业4DataGridViewTextBoxColumn,
            this.考试DataGridViewTextBoxColumn});
			this.dataGridView1.DataSource = this.电磁学期中成绩BindingSource;
			this.dataGridView1.Dock = System.Windows.Forms.DockStyle.Fill;
			this.dataGridView1.Location = new System.Drawing.Point(4, 62);
			this.dataGridView1.Name = "dataGridView1";
			this.dataGridView1.RowHeadersWidth = 51;
			this.dataGridView1.RowTemplate.Height = 27;
			this.dataGridView1.Size = new System.Drawing.Size(792, 384);
			this.dataGridView1.TabIndex = 0;
			// 
			// 学号DataGridViewTextBoxColumn
			// 
			this.学号DataGridViewTextBoxColumn.DataPropertyName = "学号";
			this.学号DataGridViewTextBoxColumn.HeaderText = "学号";
			this.学号DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.学号DataGridViewTextBoxColumn.Name = "学号DataGridViewTextBoxColumn";
			// 
			// 姓名DataGridViewTextBoxColumn
			// 
			this.姓名DataGridViewTextBoxColumn.DataPropertyName = "姓名";
			this.姓名DataGridViewTextBoxColumn.HeaderText = "姓名";
			this.姓名DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.姓名DataGridViewTextBoxColumn.Name = "姓名DataGridViewTextBoxColumn";
			// 
			// 平时作业1DataGridViewTextBoxColumn
			// 
			this.平时作业1DataGridViewTextBoxColumn.DataPropertyName = "平时作业1";
			this.平时作业1DataGridViewTextBoxColumn.HeaderText = "平时作业1";
			this.平时作业1DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.平时作业1DataGridViewTextBoxColumn.Name = "平时作业1DataGridViewTextBoxColumn";
			// 
			// 平时作业3DataGridViewTextBoxColumn
			// 
			this.平时作业3DataGridViewTextBoxColumn.DataPropertyName = "平时作业3";
			this.平时作业3DataGridViewTextBoxColumn.HeaderText = "平时作业3";
			this.平时作业3DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.平时作业3DataGridViewTextBoxColumn.Name = "平时作业3DataGridViewTextBoxColumn";
			// 
			// 平时作业4DataGridViewTextBoxColumn
			// 
			this.平时作业4DataGridViewTextBoxColumn.DataPropertyName = "平时作业4";
			this.平时作业4DataGridViewTextBoxColumn.HeaderText = "平时作业4";
			this.平时作业4DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.平时作业4DataGridViewTextBoxColumn.Name = "平时作业4DataGridViewTextBoxColumn";
			// 
			// 考试DataGridViewTextBoxColumn
			// 
			this.考试DataGridViewTextBoxColumn.DataPropertyName = "考试";
			this.考试DataGridViewTextBoxColumn.HeaderText = "考试";
			this.考试DataGridViewTextBoxColumn.MinimumWidth = 6;
			this.考试DataGridViewTextBoxColumn.Name = "考试DataGridViewTextBoxColumn";
			// 
			// 电磁学期中成绩BindingSource
			// 
			this.电磁学期中成绩BindingSource.DataMember = "电磁学期中成绩";
			this.电磁学期中成绩BindingSource.DataSource = this.swjtuDataSet;
			// 
			// swjtuDataSet
			// 
			this.swjtuDataSet.DataSetName = "swjtuDataSet";
			this.swjtuDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
			// 
			// 电磁学期中成绩TableAdapter
			// 
			this.电磁学期中成绩TableAdapter.ClearBeforeFill = true;
			// 
			// menuStrip1
			// 
			this.menuStrip1.ImageScalingSize = new System.Drawing.Size(20, 20);
			this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.文件FToolStripMenuItem,
            this.编辑EToolStripMenuItem,
            this.工具TToolStripMenuItem,
            this.帮助HToolStripMenuItem});
			this.menuStrip1.Location = new System.Drawing.Point(4, 34);
			this.menuStrip1.Name = "menuStrip1";
			this.menuStrip1.Size = new System.Drawing.Size(792, 28);
			this.menuStrip1.TabIndex = 1;
			this.menuStrip1.Text = "menuStrip1";
			// 
			// 文件FToolStripMenuItem
			// 
			this.文件FToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.新建NToolStripMenuItem,
            this.打开OToolStripMenuItem,
            this.toolStripSeparator,
            this.保存SToolStripMenuItem,
            this.另存为AToolStripMenuItem,
            this.toolStripSeparator1,
            this.打印PToolStripMenuItem,
            this.打印预览VToolStripMenuItem,
            this.toolStripSeparator2,
            this.退出XToolStripMenuItem});
			this.文件FToolStripMenuItem.Name = "文件FToolStripMenuItem";
			this.文件FToolStripMenuItem.Size = new System.Drawing.Size(71, 24);
			this.文件FToolStripMenuItem.Text = "文件(&F)";
			// 
			// 新建NToolStripMenuItem
			// 
			this.新建NToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("新建NToolStripMenuItem.Image")));
			this.新建NToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.新建NToolStripMenuItem.Name = "新建NToolStripMenuItem";
			this.新建NToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.新建NToolStripMenuItem.Text = "新建(&N)";
			// 
			// 打开OToolStripMenuItem
			// 
			this.打开OToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打开OToolStripMenuItem.Image")));
			this.打开OToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打开OToolStripMenuItem.Name = "打开OToolStripMenuItem";
			this.打开OToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.打开OToolStripMenuItem.Text = "打开(&O)";
			// 
			// toolStripSeparator
			// 
			this.toolStripSeparator.Name = "toolStripSeparator";
			this.toolStripSeparator.Size = new System.Drawing.Size(221, 6);
			// 
			// 保存SToolStripMenuItem
			// 
			this.保存SToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("保存SToolStripMenuItem.Image")));
			this.保存SToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.保存SToolStripMenuItem.Name = "保存SToolStripMenuItem";
			this.保存SToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.保存SToolStripMenuItem.Text = "保存(&S)";
			// 
			// 另存为AToolStripMenuItem
			// 
			this.另存为AToolStripMenuItem.Name = "另存为AToolStripMenuItem";
			this.另存为AToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.另存为AToolStripMenuItem.Text = "另存为(&A)";
			// 
			// toolStripSeparator1
			// 
			this.toolStripSeparator1.Name = "toolStripSeparator1";
			this.toolStripSeparator1.Size = new System.Drawing.Size(221, 6);
			// 
			// 打印PToolStripMenuItem
			// 
			this.打印PToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打印PToolStripMenuItem.Image")));
			this.打印PToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打印PToolStripMenuItem.Name = "打印PToolStripMenuItem";
			this.打印PToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.打印PToolStripMenuItem.Text = "打印(&P)";
			// 
			// 打印预览VToolStripMenuItem
			// 
			this.打印预览VToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打印预览VToolStripMenuItem.Image")));
			this.打印预览VToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打印预览VToolStripMenuItem.Name = "打印预览VToolStripMenuItem";
			this.打印预览VToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.打印预览VToolStripMenuItem.Text = "打印预览(&V)";
			// 
			// toolStripSeparator2
			// 
			this.toolStripSeparator2.Name = "toolStripSeparator2";
			this.toolStripSeparator2.Size = new System.Drawing.Size(221, 6);
			// 
			// 退出XToolStripMenuItem
			// 
			this.退出XToolStripMenuItem.Name = "退出XToolStripMenuItem";
			this.退出XToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.退出XToolStripMenuItem.Text = "退出(&X)";
			// 
			// 编辑EToolStripMenuItem
			// 
			this.编辑EToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.撤消UToolStripMenuItem,
            this.重复RToolStripMenuItem,
            this.toolStripSeparator3,
            this.剪切TToolStripMenuItem,
            this.复制CToolStripMenuItem,
            this.粘贴PToolStripMenuItem,
            this.toolStripSeparator4,
            this.全选AToolStripMenuItem});
			this.编辑EToolStripMenuItem.Name = "编辑EToolStripMenuItem";
			this.编辑EToolStripMenuItem.Size = new System.Drawing.Size(71, 24);
			this.编辑EToolStripMenuItem.Text = "编辑(&E)";
			// 
			// 撤消UToolStripMenuItem
			// 
			this.撤消UToolStripMenuItem.Name = "撤消UToolStripMenuItem";
			this.撤消UToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.撤消UToolStripMenuItem.Text = "撤消(&U)";
			// 
			// 重复RToolStripMenuItem
			// 
			this.重复RToolStripMenuItem.Name = "重复RToolStripMenuItem";
			this.重复RToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.重复RToolStripMenuItem.Text = "重复(&R)";
			// 
			// toolStripSeparator3
			// 
			this.toolStripSeparator3.Name = "toolStripSeparator3";
			this.toolStripSeparator3.Size = new System.Drawing.Size(6, 6);
			// 
			// 剪切TToolStripMenuItem
			// 
			this.剪切TToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("剪切TToolStripMenuItem.Image")));
			this.剪切TToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.剪切TToolStripMenuItem.Name = "剪切TToolStripMenuItem";
			this.剪切TToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.剪切TToolStripMenuItem.Text = "剪切(&T)";
			// 
			// 复制CToolStripMenuItem
			// 
			this.复制CToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("复制CToolStripMenuItem.Image")));
			this.复制CToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.复制CToolStripMenuItem.Name = "复制CToolStripMenuItem";
			this.复制CToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.复制CToolStripMenuItem.Text = "复制(&C)";
			// 
			// 粘贴PToolStripMenuItem
			// 
			this.粘贴PToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("粘贴PToolStripMenuItem.Image")));
			this.粘贴PToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.粘贴PToolStripMenuItem.Name = "粘贴PToolStripMenuItem";
			this.粘贴PToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.粘贴PToolStripMenuItem.Text = "粘贴(&P)";
			// 
			// toolStripSeparator4
			// 
			this.toolStripSeparator4.Name = "toolStripSeparator4";
			this.toolStripSeparator4.Size = new System.Drawing.Size(6, 6);
			// 
			// 全选AToolStripMenuItem
			// 
			this.全选AToolStripMenuItem.Name = "全选AToolStripMenuItem";
			this.全选AToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.全选AToolStripMenuItem.Text = "全选(&A)";
			// 
			// 工具TToolStripMenuItem
			// 
			this.工具TToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.自定义CToolStripMenuItem,
            this.选项OToolStripMenuItem});
			this.工具TToolStripMenuItem.Name = "工具TToolStripMenuItem";
			this.工具TToolStripMenuItem.Size = new System.Drawing.Size(72, 24);
			this.工具TToolStripMenuItem.Text = "工具(&T)";
			// 
			// 自定义CToolStripMenuItem
			// 
			this.自定义CToolStripMenuItem.Name = "自定义CToolStripMenuItem";
			this.自定义CToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.自定义CToolStripMenuItem.Text = "自定义(&C)";
			// 
			// 选项OToolStripMenuItem
			// 
			this.选项OToolStripMenuItem.Name = "选项OToolStripMenuItem";
			this.选项OToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.选项OToolStripMenuItem.Text = "选项(&O)";
			// 
			// 帮助HToolStripMenuItem
			// 
			this.帮助HToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.内容CToolStripMenuItem,
            this.索引IToolStripMenuItem,
            this.搜索SToolStripMenuItem,
            this.toolStripSeparator5,
            this.关于AToolStripMenuItem});
			this.帮助HToolStripMenuItem.Name = "帮助HToolStripMenuItem";
			this.帮助HToolStripMenuItem.Size = new System.Drawing.Size(75, 24);
			this.帮助HToolStripMenuItem.Text = "帮助(&H)";
			// 
			// 内容CToolStripMenuItem
			// 
			this.内容CToolStripMenuItem.Name = "内容CToolStripMenuItem";
			this.内容CToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.内容CToolStripMenuItem.Text = "内容(&C)";
			// 
			// 索引IToolStripMenuItem
			// 
			this.索引IToolStripMenuItem.Name = "索引IToolStripMenuItem";
			this.索引IToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.索引IToolStripMenuItem.Text = "索引(&I)";
			// 
			// 搜索SToolStripMenuItem
			// 
			this.搜索SToolStripMenuItem.Name = "搜索SToolStripMenuItem";
			this.搜索SToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.搜索SToolStripMenuItem.Text = "搜索(&S)";
			// 
			// toolStripSeparator5
			// 
			this.toolStripSeparator5.Name = "toolStripSeparator5";
			this.toolStripSeparator5.Size = new System.Drawing.Size(6, 6);
			// 
			// 关于AToolStripMenuItem
			// 
			this.关于AToolStripMenuItem.Name = "关于AToolStripMenuItem";
			this.关于AToolStripMenuItem.Size = new System.Drawing.Size(32, 19);
			this.关于AToolStripMenuItem.Text = "关于(&A)...";
			// 
			// DatabaseForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(800, 450);
			this.Controls.Add(this.dataGridView1);
			this.Controls.Add(this.menuStrip1);
			this.MainMenuStrip = this.menuStrip1;
			this.Name = "DatabaseForm";
			this.Text = "数据库表格";
			this.Load += new System.EventHandler(this.DatabaseForm_Load);
			((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.电磁学期中成绩BindingSource)).EndInit();
			((System.ComponentModel.ISupportInitialize)(this.swjtuDataSet)).EndInit();
			this.menuStrip1.ResumeLayout(false);
			this.menuStrip1.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.DataGridView dataGridView1;
		private swjtuDataSet swjtuDataSet;
		private System.Windows.Forms.BindingSource 电磁学期中成绩BindingSource;
		private swjtuDataSetTableAdapters.电磁学期中成绩TableAdapter 电磁学期中成绩TableAdapter;
		private System.Windows.Forms.DataGridViewTextBoxColumn 学号DataGridViewTextBoxColumn;
		private System.Windows.Forms.DataGridViewTextBoxColumn 姓名DataGridViewTextBoxColumn;
		private System.Windows.Forms.DataGridViewTextBoxColumn 平时作业1DataGridViewTextBoxColumn;
		private System.Windows.Forms.DataGridViewTextBoxColumn 平时作业3DataGridViewTextBoxColumn;
		private System.Windows.Forms.DataGridViewTextBoxColumn 平时作业4DataGridViewTextBoxColumn;
		private System.Windows.Forms.DataGridViewTextBoxColumn 考试DataGridViewTextBoxColumn;
		private System.Windows.Forms.MenuStrip menuStrip1;
		private System.Windows.Forms.ToolStripMenuItem 文件FToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 新建NToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 打开OToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator;
		private System.Windows.Forms.ToolStripMenuItem 保存SToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 另存为AToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
		private System.Windows.Forms.ToolStripMenuItem 打印PToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 打印预览VToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator2;
		private System.Windows.Forms.ToolStripMenuItem 退出XToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 编辑EToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 撤消UToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 重复RToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator3;
		private System.Windows.Forms.ToolStripMenuItem 剪切TToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 复制CToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 粘贴PToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator4;
		private System.Windows.Forms.ToolStripMenuItem 全选AToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 工具TToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 自定义CToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 选项OToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 帮助HToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 内容CToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 索引IToolStripMenuItem;
		private System.Windows.Forms.ToolStripMenuItem 搜索SToolStripMenuItem;
		private System.Windows.Forms.ToolStripSeparator toolStripSeparator5;
		private System.Windows.Forms.ToolStripMenuItem 关于AToolStripMenuItem;
	}
}