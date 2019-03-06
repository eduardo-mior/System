package rush.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import rush.Main;
import rush.utils.manager.DataManager;

public class Backup {

	public static void create() {
		try {
			DataManager.createFolder("backups");
			Date d = Calendar.getInstance().getTime();
			String data = DateFormat.getDateTimeInstance(2, 3).format(d).replace('/', '-').replace(':', ';');
			String nome = "Dia " + data.split(" ")[0] + " Hora " + data.split(" ")[1];
			String srcFolder = Main.get().getDataFolder().getAbsolutePath();
			String destZipFile = DataManager.getFile(nome, "backups").getAbsolutePath().replace(".yml", ".zip");
			zipFolder(srcFolder, destZipFile);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static void zipFolder(String srcFolder, String destZipFile) throws Exception {
		FileOutputStream fileWriter = new FileOutputStream(destZipFile);
		ZipOutputStream zip = new ZipOutputStream(fileWriter);
		addFolderToZip("", srcFolder, zip);
		zip.flush();
		zip.close();
	}

	private static void addFileToZip(String path, String srcFile, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFile);
		if (folder.isDirectory()) {
			addFolderToZip(path, srcFile, zip);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zip.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
			while ((len = in.read(buf)) > 0) {
				zip.write(buf, 0, len);
			}
			in.close();
		}
	}

	private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zip) throws Exception {
		File folder = new File(srcFolder);
		if (folder.getName().equals("backups")) return;
		for (String fileName : folder.list()) {
			if (path.equals("")) {
				addFileToZip(folder.getName(), srcFolder + "/" + fileName, zip);
			} else {
				addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zip);
			}
		}
	}
	
}