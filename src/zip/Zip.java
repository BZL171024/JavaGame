package zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
	/*
	 * 将文件压缩为Zip文件
	 * 
	 */
	public static void ZipCompress(String inputFile, String outputFile) throws Exception {
		//创建zip输出流
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputFile));
		//创建缓冲输出流
		BufferedOutputStream bos = new BufferedOutputStream(zos);
		File input = new File(inputFile);
		compress(zos, bos, input,null);
		bos.close();
		zos.close();
	}
	   
	//递归压缩
	public static void compress(ZipOutputStream zos, BufferedOutputStream bos, File input, String name) throws IOException {
		if (name == null) {
			name = input.getName();
		}
		//如果路径为目录（文件夹）
		if (input.isDirectory()) {
			//取出文件夹中的文件（或子文件夹）
			File[] flist = input.listFiles();
			//如果文件夹为空，则只需在目的地Zip文件中写入一个目录进入
			if (flist.length == 0)
			{
				zos.putNextEntry(new ZipEntry(name + "/"));
			} else {
				//如果文件夹不为空，则递归调用compress，文件夹中的每一个文件（或文件夹）进行压缩
				for (int i = 0; i < flist.length; i++) {
					compress(zos, bos, flist[i], name + "/" + flist[i].getName());
				}
			}
		} else {
			//如果不是目录（文件夹），即为文件，则先写入目录进入点，之后将文件写入zip文件中
			zos.putNextEntry(new ZipEntry(name));
			FileInputStream fis = new FileInputStream(input);
			BufferedInputStream bis = new BufferedInputStream(fis);
			int len;
			//将源文件写入到zip文件中
			byte[] buf = new byte[1024];
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf,0,len);
			}
			bis.close();
			fis.close();
		}
	}
	public static void main(String[] args) {
		try {
			//第一个路径为要压缩的文件路径和文件名称，第二个路径是压缩之后的Zip文件的存放路径和Zip文件名称
			ZipCompress("D:\\Test", "D:\\TestFinish.zip");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

