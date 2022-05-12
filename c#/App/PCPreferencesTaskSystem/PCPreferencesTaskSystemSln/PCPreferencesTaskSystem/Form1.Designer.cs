namespace PCPreferencesTaskSystem
{
	partial class WelcomeForm
	{
		/// <summary>
		/// 必需的设计器变量。
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// 清理所有正在使用的资源。
		/// </summary>
		/// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows 窗体设计器生成的代码

		/// <summary>
		/// 设计器支持所需的方法 - 不要修改
		/// 使用代码编辑器修改此方法的内容。
		/// </summary>
		private void InitializeComponent()
		{
			System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(WelcomeForm));
			this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
			this.label1 = new System.Windows.Forms.Label();
			this.skinLine1 = new CCWin.SkinControl.SkinLine();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.button2 = new System.Windows.Forms.Button();
			this.button1 = new System.Windows.Forms.Button();
			this.groupBox2 = new System.Windows.Forms.GroupBox();
			this.button4 = new System.Windows.Forms.Button();
			this.button3 = new System.Windows.Forms.Button();
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
			this.groupBox1.SuspendLayout();
			this.groupBox2.SuspendLayout();
			this.menuStrip1.SuspendLayout();
			this.SuspendLayout();
			// 
			// openFileDialog1
			// 
			this.openFileDialog1.FileName = "openFileDialog1";
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Font = new System.Drawing.Font("微软雅黑", 16.2F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
			this.label1.Location = new System.Drawing.Point(64, 78);
			this.label1.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(269, 37);
			this.label1.TabIndex = 0;
			this.label1.Text = "选择您要执行的任务";
			// 
			// skinLine1
			// 
			this.skinLine1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.skinLine1.BackColor = System.Drawing.Color.Transparent;
			this.skinLine1.LineColor = System.Drawing.Color.Black;
			this.skinLine1.LineHeight = 1;
			this.skinLine1.Location = new System.Drawing.Point(71, 128);
			this.skinLine1.Name = "skinLine1";
			this.skinLine1.Size = new System.Drawing.Size(878, 10);
			this.skinLine1.TabIndex = 1;
			this.skinLine1.Text = "skinLine1";
			// 
			// groupBox1
			// 
			this.groupBox1.Controls.Add(this.button2);
			this.groupBox1.Controls.Add(this.button1);
			this.groupBox1.Font = new System.Drawing.Font("微软雅黑", 10F);
			this.groupBox1.Location = new System.Drawing.Point(71, 145);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(415, 332);
			this.groupBox1.TabIndex = 2;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "本地操作";
			// 
			// button2
			// 
			this.button2.Location = new System.Drawing.Point(214, 36);
			this.button2.Name = "button2";
			this.button2.Size = new System.Drawing.Size(132, 45);
			this.button2.TabIndex = 1;
			this.button2.Text = "打开控制台";
			this.button2.UseVisualStyleBackColor = true;
			this.button2.Click += new System.EventHandler(this.button2_Click);
			// 
			// button1
			// 
			this.button1.Location = new System.Drawing.Point(28, 36);
			this.button1.Name = "button1";
			this.button1.Size = new System.Drawing.Size(132, 45);
			this.button1.TabIndex = 0;
			this.button1.Text = "打开文件";
			this.button1.UseVisualStyleBackColor = true;
			this.button1.Click += new System.EventHandler(this.button1_Click);
			// 
			// groupBox2
			// 
			this.groupBox2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.groupBox2.Controls.Add(this.button4);
			this.groupBox2.Controls.Add(this.button3);
			this.groupBox2.Font = new System.Drawing.Font("微软雅黑", 10F);
			this.groupBox2.Location = new System.Drawing.Point(531, 145);
			this.groupBox2.Name = "groupBox2";
			this.groupBox2.Size = new System.Drawing.Size(418, 332);
			this.groupBox2.TabIndex = 3;
			this.groupBox2.TabStop = false;
			this.groupBox2.Text = "联网操作";
			// 
			// button4
			// 
			this.button4.Location = new System.Drawing.Point(235, 36);
			this.button4.Name = "button4";
			this.button4.Size = new System.Drawing.Size(132, 45);
			this.button4.TabIndex = 3;
			this.button4.Text = "访问数据库";
			this.button4.UseVisualStyleBackColor = true;
			this.button4.Click += new System.EventHandler(this.button4_Click);
			// 
			// button3
			// 
			this.button3.Location = new System.Drawing.Point(45, 36);
			this.button3.Name = "button3";
			this.button3.Size = new System.Drawing.Size(132, 45);
			this.button3.TabIndex = 2;
			this.button3.Text = "访问教务网";
			this.button3.UseVisualStyleBackColor = true;
			this.button3.Click += new System.EventHandler(this.button3_Click);
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
			this.menuStrip1.RenderMode = System.Windows.Forms.ToolStripRenderMode.Professional;
			this.menuStrip1.Size = new System.Drawing.Size(1028, 28);
			this.menuStrip1.TabIndex = 5;
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
			this.新建NToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.N)));
			this.新建NToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.新建NToolStripMenuItem.Text = "新建(&N)";
			this.新建NToolStripMenuItem.Click += new System.EventHandler(this.新建NToolStripMenuItem_Click);
			// 
			// 打开OToolStripMenuItem
			// 
			this.打开OToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打开OToolStripMenuItem.Image")));
			this.打开OToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打开OToolStripMenuItem.Name = "打开OToolStripMenuItem";
			this.打开OToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.O)));
			this.打开OToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.打开OToolStripMenuItem.Text = "打开(&O)";
			// 
			// toolStripSeparator
			// 
			this.toolStripSeparator.Name = "toolStripSeparator";
			this.toolStripSeparator.Size = new System.Drawing.Size(227, 6);
			// 
			// 保存SToolStripMenuItem
			// 
			this.保存SToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("保存SToolStripMenuItem.Image")));
			this.保存SToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.保存SToolStripMenuItem.Name = "保存SToolStripMenuItem";
			this.保存SToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.S)));
			this.保存SToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.保存SToolStripMenuItem.Text = "保存(&S)";
			// 
			// 另存为AToolStripMenuItem
			// 
			this.另存为AToolStripMenuItem.Name = "另存为AToolStripMenuItem";
			this.另存为AToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.另存为AToolStripMenuItem.Text = "另存为(&A)";
			// 
			// toolStripSeparator1
			// 
			this.toolStripSeparator1.Name = "toolStripSeparator1";
			this.toolStripSeparator1.Size = new System.Drawing.Size(227, 6);
			// 
			// 打印PToolStripMenuItem
			// 
			this.打印PToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打印PToolStripMenuItem.Image")));
			this.打印PToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打印PToolStripMenuItem.Name = "打印PToolStripMenuItem";
			this.打印PToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.P)));
			this.打印PToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.打印PToolStripMenuItem.Text = "打印(&P)";
			// 
			// 打印预览VToolStripMenuItem
			// 
			this.打印预览VToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("打印预览VToolStripMenuItem.Image")));
			this.打印预览VToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.打印预览VToolStripMenuItem.Name = "打印预览VToolStripMenuItem";
			this.打印预览VToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.打印预览VToolStripMenuItem.Text = "打印预览(&V)";
			// 
			// toolStripSeparator2
			// 
			this.toolStripSeparator2.Name = "toolStripSeparator2";
			this.toolStripSeparator2.Size = new System.Drawing.Size(227, 6);
			// 
			// 退出XToolStripMenuItem
			// 
			this.退出XToolStripMenuItem.Name = "退出XToolStripMenuItem";
			this.退出XToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Alt | System.Windows.Forms.Keys.F4)));
			this.退出XToolStripMenuItem.Size = new System.Drawing.Size(230, 26);
			this.退出XToolStripMenuItem.Text = "退出系统(&X)";
			this.退出XToolStripMenuItem.Click += new System.EventHandler(this.退出XToolStripMenuItem_Click);
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
			this.撤消UToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Z)));
			this.撤消UToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.撤消UToolStripMenuItem.Text = "撤消(&U)";
			// 
			// 重复RToolStripMenuItem
			// 
			this.重复RToolStripMenuItem.Name = "重复RToolStripMenuItem";
			this.重复RToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.Y)));
			this.重复RToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.重复RToolStripMenuItem.Text = "重复(&R)";
			// 
			// toolStripSeparator3
			// 
			this.toolStripSeparator3.Name = "toolStripSeparator3";
			this.toolStripSeparator3.Size = new System.Drawing.Size(221, 6);
			// 
			// 剪切TToolStripMenuItem
			// 
			this.剪切TToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("剪切TToolStripMenuItem.Image")));
			this.剪切TToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.剪切TToolStripMenuItem.Name = "剪切TToolStripMenuItem";
			this.剪切TToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.X)));
			this.剪切TToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.剪切TToolStripMenuItem.Text = "剪切(&T)";
			// 
			// 复制CToolStripMenuItem
			// 
			this.复制CToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("复制CToolStripMenuItem.Image")));
			this.复制CToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.复制CToolStripMenuItem.Name = "复制CToolStripMenuItem";
			this.复制CToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.C)));
			this.复制CToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.复制CToolStripMenuItem.Text = "复制(&C)";
			// 
			// 粘贴PToolStripMenuItem
			// 
			this.粘贴PToolStripMenuItem.Image = ((System.Drawing.Image)(resources.GetObject("粘贴PToolStripMenuItem.Image")));
			this.粘贴PToolStripMenuItem.ImageTransparentColor = System.Drawing.Color.Magenta;
			this.粘贴PToolStripMenuItem.Name = "粘贴PToolStripMenuItem";
			this.粘贴PToolStripMenuItem.ShortcutKeys = ((System.Windows.Forms.Keys)((System.Windows.Forms.Keys.Control | System.Windows.Forms.Keys.V)));
			this.粘贴PToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.粘贴PToolStripMenuItem.Text = "粘贴(&P)";
			// 
			// toolStripSeparator4
			// 
			this.toolStripSeparator4.Name = "toolStripSeparator4";
			this.toolStripSeparator4.Size = new System.Drawing.Size(221, 6);
			// 
			// 全选AToolStripMenuItem
			// 
			this.全选AToolStripMenuItem.Name = "全选AToolStripMenuItem";
			this.全选AToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
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
			this.自定义CToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
			this.自定义CToolStripMenuItem.Text = "自定义(&C)";
			// 
			// 选项OToolStripMenuItem
			// 
			this.选项OToolStripMenuItem.Name = "选项OToolStripMenuItem";
			this.选项OToolStripMenuItem.Size = new System.Drawing.Size(224, 26);
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
			this.内容CToolStripMenuItem.Size = new System.Drawing.Size(155, 26);
			this.内容CToolStripMenuItem.Text = "内容(&C)";
			// 
			// 索引IToolStripMenuItem
			// 
			this.索引IToolStripMenuItem.Name = "索引IToolStripMenuItem";
			this.索引IToolStripMenuItem.Size = new System.Drawing.Size(155, 26);
			this.索引IToolStripMenuItem.Text = "索引(&I)";
			// 
			// 搜索SToolStripMenuItem
			// 
			this.搜索SToolStripMenuItem.Name = "搜索SToolStripMenuItem";
			this.搜索SToolStripMenuItem.Size = new System.Drawing.Size(155, 26);
			this.搜索SToolStripMenuItem.Text = "搜索(&S)";
			// 
			// toolStripSeparator5
			// 
			this.toolStripSeparator5.Name = "toolStripSeparator5";
			this.toolStripSeparator5.Size = new System.Drawing.Size(152, 6);
			// 
			// 关于AToolStripMenuItem
			// 
			this.关于AToolStripMenuItem.Name = "关于AToolStripMenuItem";
			this.关于AToolStripMenuItem.Size = new System.Drawing.Size(155, 26);
			this.关于AToolStripMenuItem.Text = "关于(&A)...";
			this.关于AToolStripMenuItem.Click += new System.EventHandler(this.关于AToolStripMenuItem_Click);
			// 
			// WelcomeForm
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(14F, 27F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(1036, 566);
			this.Controls.Add(this.menuStrip1);
			this.Controls.Add(this.groupBox2);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.skinLine1);
			this.Controls.Add(this.label1);
			this.Font = new System.Drawing.Font("宋体", 16.2F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
			this.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
			this.MaximumSize = new System.Drawing.Size(3000, 3000);
			this.MinimumSize = new System.Drawing.Size(100, 100);
			this.Name = "WelcomeForm";
			this.Text = "PC定制任务管理系统";
			this.groupBox1.ResumeLayout(false);
			this.groupBox2.ResumeLayout(false);
			this.menuStrip1.ResumeLayout(false);
			this.menuStrip1.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion
		private System.Windows.Forms.OpenFileDialog openFileDialog1;
		private System.Windows.Forms.Label label1;
		private CCWin.SkinControl.SkinLine skinLine1;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.GroupBox groupBox2;
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
		private System.Windows.Forms.Button button2;
		private System.Windows.Forms.Button button1;
		private System.Windows.Forms.Button button3;
		private System.Windows.Forms.Button button4;
	}
}

