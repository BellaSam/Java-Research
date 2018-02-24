package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ConvertKuGouTempFile {

	public static void main(String[] args) throws Exception { 
		byte[] key = { (byte) 0xAC,(byte) 0xEC,(byte) 0xDF,0x57};
		File inputFile = new File("D://KuGou/Temp/718567d263c17bb3945b596cdd887c27.kgtemp");
		File outputFile = new File("D://KuGou/Temp/ss.mp3");
		InputStream input = new FileInputStream(inputFile);
		OutputStream output = new FileOutputStream(outputFile);
		byte[] buffer = new byte[key.length];
		int length = input.read(buffer);
		while(length != -1){
			for(int i=0;i<length;i++)
            {
                byte k = key[i];
                byte kh = (byte) (k>>4);
                byte kl = (byte) (k & 0xf);
                byte b = buffer[i];
                byte low = (byte) (b & 0xf ^ kl);//解密后的低4位
                byte high = (byte) ((b >> 4) ^ kh ^ low & 0xf);//解密后的高4位
                buffer[i] = (byte)(high << 4 | low);
            }
			output.write(buffer);
            length = input.read(buffer);
		}
		output.close();
		input.close();
	}

}
