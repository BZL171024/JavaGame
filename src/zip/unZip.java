package zip;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class unZip {
	
	/*
	 * 解压缩Zip文件
	 * 
	 */

	public static void zipUnCompress(String inputFile, String destDirPath) throws Exception {
		//获取压缩文件
		File srcFile = new File(inputFile);
		//判断是否存在
		if(!srcFile.exists()) {
			throw new Exception(srcFile.getPath() + "所指文件不存在！");
		}
		//创建压缩文件对象
		ZipFile zipFile = new ZipFile(srcFile);
		//开始解压缩
		Enumeration<?> entries = zipFile.entries();
		while(entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			//若是文件夹，就创建文件夹
			if(entry.isDirectory()) {
				String dirPath = destDirPath + "/" + entry.getName();
				srcFile.mkdirs();
			} else {
				//若是文件，就先创建一个文件，然后用io流把内容copy进去
				File targetFile = new File(destDirPath + "/" + entry.getName());
				//保证这个文件夹的父文件夹必须存在
				if(!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				targetFile.createNewFile();
				//将压缩文件内容写入到这个文件中
				InputStream is = zipFile.getInputStream(entry);
				FileOutputStream fos = new FileOutputStream(targetFile);
				int len;
				byte[] buf = new byte[1024];
				while ((len = is.read(buf)) != -1) {
					fos.write(buf, 0, len);
				}
				//关闭流操作
				fos.close();
				is.close();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			//第一个路径为要解压缩的Zip文件，第二个路径是Zip文件解压缩之后的路径和文件名称
			zipUnCompress("D:\\TestFinish.zip", "D:\\UnTestFinish");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
