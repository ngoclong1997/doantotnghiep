namespace ServerScan
{
    partial class Main
    {
        /// <summary>
        /// Variable nécessaire au concepteur.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Nettoyage des ressources utilisées.
        /// </summary>
        /// <param name="disposing">true si les ressources managées doivent être supprimées ; sinon, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {

                if (components != null)
                    components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Code généré par le Concepteur Windows Form

        /// <summary>
        /// Méthode requise pour la prise en charge du concepteur - ne modifiez pas
        /// le contenu de cette méthode avec l'éditeur de code.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Main));
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.check_tryFlatbed = new System.Windows.Forms.CheckBox();
            this.check_useAdf = new System.Windows.Forms.CheckBox();
            this.bt_startScan = new System.Windows.Forms.Button();
            this.lb_path = new System.Windows.Forms.Label();
            this.lb_scanner = new System.Windows.Forms.Label();
            this.bt_path = new System.Windows.Forms.Button();
            this.bt_select = new System.Windows.Forms.Button();
            this.combo_color = new System.Windows.Forms.ComboBox();
            this.label5 = new System.Windows.Forms.Label();
            this.combo_dpi = new System.Windows.Forms.ComboBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.pathBrowserDialog = new System.Windows.Forms.FolderBrowserDialog();
            this.mainNotify = new System.Windows.Forms.NotifyIcon(this.components);
            this.pbAnswerImage = new System.Windows.Forms.PictureBox();
            this.ListBoxImageScanned = new System.Windows.Forms.ListBox();
            this.btnContinue = new System.Windows.Forms.Button();
            this.btnDone = new System.Windows.Forms.Button();
            this.btnRestart = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbAnswerImage)).BeginInit();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBox1.Controls.Add(this.check_tryFlatbed);
            this.groupBox1.Controls.Add(this.check_useAdf);
            this.groupBox1.Controls.Add(this.bt_startScan);
            this.groupBox1.Controls.Add(this.lb_path);
            this.groupBox1.Controls.Add(this.lb_scanner);
            this.groupBox1.Controls.Add(this.bt_path);
            this.groupBox1.Controls.Add(this.bt_select);
            this.groupBox1.Controls.Add(this.combo_color);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Controls.Add(this.combo_dpi);
            this.groupBox1.Controls.Add(this.label7);
            this.groupBox1.Controls.Add(this.label3);
            this.groupBox1.Controls.Add(this.label2);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(416, 213);
            this.groupBox1.TabIndex = 0;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "Thiết lập máy quét";
            // 
            // check_tryFlatbed
            // 
            this.check_tryFlatbed.AutoSize = true;
            this.check_tryFlatbed.Location = new System.Drawing.Point(114, 145);
            this.check_tryFlatbed.Name = "check_tryFlatbed";
            this.check_tryFlatbed.Size = new System.Drawing.Size(216, 17);
            this.check_tryFlatbed.TabIndex = 51;
            this.check_tryFlatbed.Text = "Tự động chuyển sang thu giấy thủ công";
            this.check_tryFlatbed.UseVisualStyleBackColor = true;
            this.check_tryFlatbed.Click += new System.EventHandler(this.check_tryFlatbed_Click);
            // 
            // check_useAdf
            // 
            this.check_useAdf.AutoSize = true;
            this.check_useAdf.Location = new System.Drawing.Point(114, 122);
            this.check_useAdf.Name = "check_useAdf";
            this.check_useAdf.Size = new System.Drawing.Size(146, 17);
            this.check_useAdf.TabIndex = 51;
            this.check_useAdf.Text = "Thu giấy tự động (feeder)";
            this.check_useAdf.UseVisualStyleBackColor = true;
            this.check_useAdf.Click += new System.EventHandler(this.check_useAdf_Click);
            // 
            // bt_startScan
            // 
            this.bt_startScan.Location = new System.Drawing.Point(234, 184);
            this.bt_startScan.Name = "bt_startScan";
            this.bt_startScan.Size = new System.Drawing.Size(162, 23);
            this.bt_startScan.TabIndex = 50;
            this.bt_startScan.Text = "Bắt đầu scan";
            this.bt_startScan.UseVisualStyleBackColor = true;
            this.bt_startScan.Click += new System.EventHandler(this.bt_startScan_Click);
            // 
            // lb_path
            // 
            this.lb_path.AutoEllipsis = true;
            this.lb_path.Location = new System.Drawing.Point(111, 44);
            this.lb_path.Name = "lb_path";
            this.lb_path.Size = new System.Drawing.Size(253, 13);
            this.lb_path.TabIndex = 7;
            this.lb_path.Text = "Đường dẫn đến thư mục chứa kết quả...";
            // 
            // lb_scanner
            // 
            this.lb_scanner.AutoEllipsis = true;
            this.lb_scanner.Location = new System.Drawing.Point(111, 20);
            this.lb_scanner.Name = "lb_scanner";
            this.lb_scanner.Size = new System.Drawing.Size(253, 18);
            this.lb_scanner.TabIndex = 7;
            this.lb_scanner.Text = "Chọn máy quét...";
            // 
            // bt_path
            // 
            this.bt_path.Location = new System.Drawing.Point(371, 39);
            this.bt_path.Name = "bt_path";
            this.bt_path.Size = new System.Drawing.Size(25, 23);
            this.bt_path.TabIndex = 20;
            this.bt_path.Text = "...";
            this.bt_path.UseVisualStyleBackColor = true;
            this.bt_path.Click += new System.EventHandler(this.bt_path_Click);
            // 
            // bt_select
            // 
            this.bt_select.Location = new System.Drawing.Point(371, 15);
            this.bt_select.Name = "bt_select";
            this.bt_select.Size = new System.Drawing.Size(25, 23);
            this.bt_select.TabIndex = 10;
            this.bt_select.Text = "...";
            this.bt_select.UseVisualStyleBackColor = true;
            this.bt_select.Click += new System.EventHandler(this.bt_selectScanner_Click);
            // 
            // combo_color
            // 
            this.combo_color.FormattingEnabled = true;
            this.combo_color.Items.AddRange(new object[] {
            "Color",
            "Grayscale"});
            this.combo_color.Location = new System.Drawing.Point(114, 90);
            this.combo_color.Name = "combo_color";
            this.combo_color.Size = new System.Drawing.Size(282, 21);
            this.combo_color.TabIndex = 40;
            this.combo_color.Leave += new System.EventHandler(this.combo_color_Leave);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(6, 44);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(80, 13);
            this.label5.TabIndex = 2;
            this.label5.Text = "Đường dẫn lưu:";
            // 
            // combo_dpi
            // 
            this.combo_dpi.FormattingEnabled = true;
            this.combo_dpi.Items.AddRange(new object[] {
            "75",
            "150",
            "300"});
            this.combo_dpi.Location = new System.Drawing.Point(114, 67);
            this.combo_dpi.Name = "combo_dpi";
            this.combo_dpi.Size = new System.Drawing.Size(282, 21);
            this.combo_dpi.TabIndex = 30;
            this.combo_dpi.Leave += new System.EventHandler(this.combo_dpi_Leave);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(7, 122);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(85, 13);
            this.label7.TabIndex = 2;
            this.label7.Text = "Chế độ thu giấy:";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(7, 93);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(47, 13);
            this.label3.TabIndex = 2;
            this.label3.Text = "Hệ màu:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(7, 70);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(70, 13);
            this.label2.TabIndex = 1;
            this.label2.Text = "Độ phân giải:";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 20);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(75, 13);
            this.label1.TabIndex = 0;
            this.label1.Text = "Tên máy quét:";
            // 
            // mainNotify
            // 
            this.mainNotify.Icon = ((System.Drawing.Icon)(resources.GetObject("mainNotify.Icon")));
            this.mainNotify.Text = "ServerScan - Disconnected";
            this.mainNotify.DoubleClick += new System.EventHandler(this.mainNotify_DoubleClick);
            // 
            // pbAnswerImage
            // 
            this.pbAnswerImage.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.pbAnswerImage.Location = new System.Drawing.Point(434, 19);
            this.pbAnswerImage.Name = "pbAnswerImage";
            this.pbAnswerImage.Size = new System.Drawing.Size(560, 632);
            this.pbAnswerImage.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.pbAnswerImage.TabIndex = 1;
            this.pbAnswerImage.TabStop = false;
            // 
            // ListBoxImageScanned
            // 
            this.ListBoxImageScanned.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ListBoxImageScanned.FormattingEnabled = true;
            this.ListBoxImageScanned.Location = new System.Drawing.Point(12, 257);
            this.ListBoxImageScanned.Name = "ListBoxImageScanned";
            this.ListBoxImageScanned.Size = new System.Drawing.Size(416, 394);
            this.ListBoxImageScanned.TabIndex = 2;
            this.ListBoxImageScanned.SelectedIndexChanged += new System.EventHandler(this.ListBoxImageScanned_SelectedIndexChanged);
            // 
            // btnContinue
            // 
            this.btnContinue.Location = new System.Drawing.Point(12, 231);
            this.btnContinue.Name = "btnContinue";
            this.btnContinue.Size = new System.Drawing.Size(96, 23);
            this.btnContinue.TabIndex = 3;
            this.btnContinue.Text = "Tiếp tục quét";
            this.btnContinue.UseVisualStyleBackColor = true;
            this.btnContinue.Visible = false;
            this.btnContinue.Click += new System.EventHandler(this.btnContinue_Click);
            // 
            // btnDone
            // 
            this.btnDone.Location = new System.Drawing.Point(114, 231);
            this.btnDone.Name = "btnDone";
            this.btnDone.Size = new System.Drawing.Size(75, 23);
            this.btnDone.TabIndex = 4;
            this.btnDone.Text = "Hoàn tất";
            this.btnDone.UseVisualStyleBackColor = true;
            this.btnDone.Visible = false;
            this.btnDone.Click += new System.EventHandler(this.btnDone_Click);
            // 
            // btnRestart
            // 
            this.btnRestart.Location = new System.Drawing.Point(353, 231);
            this.btnRestart.Name = "btnRestart";
            this.btnRestart.Size = new System.Drawing.Size(75, 23);
            this.btnRestart.TabIndex = 5;
            this.btnRestart.Text = "Bắt đầu lại";
            this.btnRestart.UseVisualStyleBackColor = true;
            this.btnRestart.Visible = false;
            this.btnRestart.Click += new System.EventHandler(this.btnRestart_Click);
            // 
            // Main
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1006, 658);
            this.Controls.Add(this.btnRestart);
            this.Controls.Add(this.btnDone);
            this.Controls.Add(this.btnContinue);
            this.Controls.Add(this.ListBoxImageScanned);
            this.Controls.Add(this.pbAnswerImage);
            this.Controls.Add(this.groupBox1);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "Main";
            this.Text = "  ";
            this.Resize += new System.EventHandler(this.Main_Resize);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pbAnswerImage)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ComboBox combo_color;
        private System.Windows.Forms.ComboBox combo_dpi;
        private System.Windows.Forms.Label lb_scanner;
        private System.Windows.Forms.Button bt_select;
        private System.Windows.Forms.Button bt_startScan;
        private System.Windows.Forms.Label lb_path;
        private System.Windows.Forms.Button bt_path;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.FolderBrowserDialog pathBrowserDialog;
        private System.Windows.Forms.CheckBox check_tryFlatbed;
        private System.Windows.Forms.CheckBox check_useAdf;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.NotifyIcon mainNotify;
        private System.Windows.Forms.PictureBox pbAnswerImage;
        private System.Windows.Forms.ListBox ListBoxImageScanned;
        private System.Windows.Forms.Button btnContinue;
        private System.Windows.Forms.Button btnDone;
        private System.Windows.Forms.Button btnRestart;
    }
}

