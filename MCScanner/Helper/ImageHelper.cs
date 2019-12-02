using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;

namespace ServerScan.Helper
{
    class ImageHelper
    {
        public static bool createTif(List<Bitmap> bmp, string location)
        {
            if (bmp != null)
            {
                try
                {
                    ImageCodecInfo codecInfo = GetEncoderInfo("image/tiff");
                    if (bmp.Count == 1)
                    {
                        EncoderParameters iparams = new EncoderParameters(1);
                        Encoder iparam = Encoder.Compression;
                        EncoderParameter iparamPara = new EncoderParameter(iparam, (long)(EncoderValue.CompressionLZW));
                        iparams.Param[0] = iparamPara;
                        bmp[0].Save(location, codecInfo, iparams);
                    }
                    else if (bmp.Count > 1)
                    {

                        Encoder saveEncoder;
                        Encoder compressionEncoder;
                        EncoderParameter SaveEncodeParam;
                        EncoderParameter CompressionEncodeParam;
                        EncoderParameters EncoderParams = new EncoderParameters(2);

                        saveEncoder = Encoder.SaveFlag;
                        compressionEncoder = Encoder.Compression;

                        // Save the first page (frame).
                        SaveEncodeParam = new EncoderParameter(saveEncoder, (long)EncoderValue.MultiFrame);
                        CompressionEncodeParam = new EncoderParameter(compressionEncoder, (long)EncoderValue.CompressionLZW);
                        EncoderParams.Param[0] = CompressionEncodeParam;
                        EncoderParams.Param[1] = SaveEncodeParam;

                        File.Delete(location);
                        bmp[0].Save(location, codecInfo, EncoderParams);

                        for (int i = 1; i < bmp.Count; i++)
                        {
                            if (bmp[i] == null)
                                break;

                            SaveEncodeParam = new EncoderParameter(saveEncoder, (long)EncoderValue.FrameDimensionPage);
                            CompressionEncodeParam = new EncoderParameter(compressionEncoder, (long)EncoderValue.CompressionLZW);
                            EncoderParams.Param[0] = CompressionEncodeParam;
                            EncoderParams.Param[1] = SaveEncodeParam;
                            bmp[0].SaveAdd(bmp[i], EncoderParams);

                        }

                        SaveEncodeParam = new EncoderParameter(saveEncoder, (long)EncoderValue.Flush);
                        EncoderParams.Param[0] = SaveEncodeParam;
                        bmp[0].SaveAdd(EncoderParams);
                    }
                }
                catch (System.Exception ee)
                {
                    throw new Exception(ee.Message + " Merge image error");
                }
                return true;
            }
            else
            {
                return false;
            }
        }

        private static ImageCodecInfo GetEncoderInfo(string mimeType)
        {
            ImageCodecInfo[] encoders = ImageCodecInfo.GetImageEncoders();
            for (int j = 0; j < encoders.Length; j++)
            {
                if (encoders[j].MimeType == mimeType)
                    return encoders[j];
            }

            throw new Exception(mimeType + " mime type not found in ImageCodecInfo");
        }

        private static Boolean PathWritable(String path)
        {
            try
            {
                System.Security.AccessControl.DirectorySecurity ds = Directory.GetAccessControl(path);
                return true;
            }
            catch (UnauthorizedAccessException)
            {
                return false;
            }
            catch (Exception)
            {
                Program.ShowError("Không thể truy cập vào đường dẫn lưu kết quả");
                return false;
            }
        }

        public static int SaveImages(List<Bitmap> list)
        {
            if (!PathWritable(Program.config.SavePath))
            {
                Program.ShowError("Đường dẫn lưu kết quả không được phép ghi");
                return 0;
            }
            for (int i = 0; i < list.Count; i++)
            {
                list[i] = ResizeImage(list[i], 1653, 2338);
                list[i].SetResolution(200, 200);
            }
            Logger.Log("Finished scanning " + list.Count + " files");
            DateTime now = DateTime.Now;
            File.WriteAllBytes(Program.config.SavePath + "\\SignedImages_" + now.ToString("ddMMyyyyHHmmss") + ".dat", SigningHelper.signImages(list));
            ImageHelper.createTif(list, Program.config.SavePath + "\\" + now.ToString("yy_MM_dd-H_mm_ss") + ".tif");
            return 1;
        }

        private static Bitmap ResizeImage(Bitmap image, int newWidth, int newHeight)
        {
            //Image<Bgr, Byte> img = new Image<Bgr, Byte>(image);
            //CvInvoke.Resize(img, img, new Size(newWidth, newHeight), 0, 0, Emgu.CV.CvEnum.Inter.Linear);
            //return img.ToBitmap();

            Bitmap b = new Bitmap(newWidth, newHeight);
            Graphics g = Graphics.FromImage((Image)b);

            g.DrawImage(image, 0, 0, newWidth, newHeight);
            g.Dispose();

            return b;
        }
    }
}
