namespace NetCore
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
			this.skinLabel1 = new CCWin.SkinControl.SkinLabel();
			this.skinLine1 = new CCWin.SkinControl.SkinLine();
			this.SuspendLayout();
			// 
			// skinLabel1
			// 
			this.skinLabel1.AutoSize = true;
			this.skinLabel1.BackColor = System.Drawing.Color.Transparent;
			this.skinLabel1.BorderColor = System.Drawing.Color.White;
			this.skinLabel1.Font = new System.Drawing.Font("微软雅黑", 18F);
			this.skinLabel1.Location = new System.Drawing.Point(39, 19);
			this.skinLabel1.Name = "skinLabel1";
			this.skinLabel1.Size = new System.Drawing.Size(557, 39);
			this.skinLabel1.TabIndex = 0;
			this.skinLabel1.Text = "请在以下选项中选择您想要执行的操作：";
			this.skinLabel1.Click += new System.EventHandler(this.skinLabel1_Click);
			// 
			// skinLine1
			// 
			this.skinLine1.BackColor = System.Drawing.Color.Transparent;
			this.skinLine1.LineColor = System.Drawing.Color.Black;
			this.skinLine1.LineHeight = 3;
			this.skinLine1.Location = new System.Drawing.Point(46, 71);
			this.skinLine1.Name = "skinLine1";
			this.skinLine1.Size = new System.Drawing.Size(713, 42);
			this.skinLine1.TabIndex = 2;
			this.skinLine1.Text = "skinLine1";
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 15F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(800, 450);
			this.Controls.Add(this.skinLine1);
			this.Controls.Add(this.skinLabel1);
			this.Name = "Form1";
			this.Text = "Form1";
			this.ResumeLayout(false);
			this.PerformLayout();
            this.components = new System.ComponentModel.Container();
        }

        #endregion
        private CCWin.SkinControl.SkinLabel skinLabel1;
        private CCWin.SkinControl.SkinLine skinLine1;
    }
}

