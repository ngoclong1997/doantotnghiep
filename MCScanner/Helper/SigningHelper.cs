using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Runtime.Serialization.Formatters.Binary;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;
using System.Text;

namespace ServerScan.Helper
{
    class SigningHelper
    {
        private static byte[] Sign(byte[] data)
        {
            X509Certificate2 cert = LoadPrivateKey();
            if (data == null)
            {
                throw new ArgumentNullException("data");
            }
            if (cert == null)
            {
                throw new ArgumentNullException("privateKey");
            }
            if (!cert.HasPrivateKey)
            {
                throw new ArgumentException("invalid certificate", "privateKey");
            }
            var provider = (RSACryptoServiceProvider)cert.PrivateKey;
            return provider.SignData(data, new SHA1CryptoServiceProvider());
        }

        private static X509Certificate2 LoadPrivateKey()
        {
            return new X509Certificate2(Properties.Resources.cert, "Dragon1997");
        }

        public static byte[] signImages(List<Bitmap> imgs)
        {
            byte[] originalImage = ObjToByteArray(imgs);
            byte[] signature = Sign(originalImage);
            List<byte[]> imageWithSignature = new List<byte[]>();
            imageWithSignature.Add(originalImage);
            imageWithSignature.Add(signature);
            return ObjToByteArray(imageWithSignature);
        }

        public static byte[] ObjToByteArray(Object data)
        {
            var binFormatter = new BinaryFormatter();
            var mStream = new MemoryStream();
            binFormatter.Serialize(mStream, data);
            return mStream.ToArray();
        }
    }
}
