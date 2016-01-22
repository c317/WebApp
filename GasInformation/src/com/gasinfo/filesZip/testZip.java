package com.gasinfo.filesZip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipOutputStream;

import javax.swing.JFileChooser;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

public class testZip {
	/**
	 * 压缩文件-由于out要在递归调用外,所以封装一个方法用来 调用ZipFiles(ZipOutputStream out,String
	 * path,File... srcFiles)
	 * 
	 * @param zip
	 * @param path
	 * @param srcFiles
	 * @throws IOException
	 * @author isea533
	 */
	public void ZipFiles(File zip, String path, File[] srcFiles)
			throws IOException {
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zip));
		new testZip().FilesZip(out, path, srcFiles);
		out.close();
		System.out.println("*****************压缩完毕*******************");
	}

	/**
	 * 压缩文件-File
	 * 
	 * @param zipFile
	 *            zip文件
	 * @param srcFiles
	 *            被压缩源文件
	 * @author isea533
	 */
	public void FilesZip(ZipOutputStream out, String path, File[] srcFiles) {
		path = path.replaceAll("\\*", "/");
		if (!path.endsWith("/")) {
			path += "/";
		}
		byte[] buf = new byte[1024];
		try {
			for (int i = 0; i < srcFiles.length; i++) {
				if (srcFiles[i].isDirectory()) {
					File[] files = srcFiles[i].listFiles();
					String srcPath = srcFiles[i].getName();
					srcPath = srcPath.replaceAll("\\*", "/");
					if (!srcPath.endsWith("/")) {
						srcPath += "/";
					}
					out.putNextEntry(new ZipEntry(path + srcPath));
					FilesZip(out, path + srcPath, files);
				} else {
					FileInputStream in = new FileInputStream(srcFiles[i]);
					System.out.println(path + srcFiles[i].getName());
					out.putNextEntry(new ZipEntry(path + srcFiles[i].getName()));
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.closeEntry();
					in.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 * @param descDir
	 * @author isea533
	 */
	public void unZipFiles(File zipFile, String descDir) throws IOException {
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		ZipFile zip = new ZipFile(zipFile);
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
			;
			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}
			// 输出文件路径信息
			System.out.println(outPath);

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[1024];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		System.out.println("******************解压完毕********************");
	}

	public static void main(String[] temp) throws ParseException {
		ArrayList<String> datas = new testZip().copyFiles("2015-07-08",
				"2015-07-08", "D:\\husenyong\\");

		// File f1=new File("D:\\javaworksapce\\20150706214305");
		// File f2=new File("D:\\javaworksapce\\20150708160416");

		File[] srcfile = new File[datas.size()];
		for (int i = 0; i < datas.size(); i++) {
			srcfile[i] = new File("D:\\husenyong\\" + datas.get(i));
		}
		// JFileChooser chooser = new JFileChooser();
		// JPanel parent = new JPanel();
		// int returnVal = chooser.showOpenDialog(parent);
		// if (returnVal == JFileChooser.APPROVE_OPTION) {
		// System.out.println("选择的文件地址为："
		// + chooser.getSelectedFile().getPath());
		// }
		JFileChooser fc = new JFileChooser();
		fc.setDialogType(JFileChooser.FILES_ONLY);
		fc.setDialogTitle("选择文件");
		fc.setSelectedFile(new File("增量包.zip"));
		fc.setMultiSelectionEnabled(true);
		fc.showSaveDialog(fc);
		if (fc.getSelectedFile() == null) {
		}
		System.out.println(fc.getSelectedFile().getPath());
		// 压缩后的文件
		File zipfile = new File(fc.getSelectedFile().getPath());
		String descDir = "D:\\Arcgis\\hu\\";
		try {
			new testZip().ZipFiles(zipfile, "abc", srcfile);
			new testZip().unZipFiles(zipfile, descDir);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> copyFiles(String B_time, String E_time,
			String EnterPath) {
		ArrayList<String> listFile = new ArrayList<String>();
		B_time = B_time.replace("-", "") + "000000";
		E_time = E_time.replace("-", "") + "000000";
		long long_B_time;
		long long_E_time;
		File[] files = new File(EnterPath).listFiles();
		for (int i = 0; i < files.length; i++) {
			try {
				long_B_time = Long.valueOf(B_time);
				long_E_time = Long.valueOf(E_time);
				long long_File_time = Long.valueOf(files[i].getName()
						.substring(0, 8) + "000000");
				if (long_File_time >= long_B_time
						&& long_File_time <= long_E_time) {
					listFile.add(EnterPath + File.separator
							+ files[i].getName());
					System.out.println(files[i].getName());
				}
			} catch (Exception e) {
				continue;
			}

		}
		return listFile;
	}

}
