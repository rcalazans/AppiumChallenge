package Challenge.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.cucumber.listener.Reporter;

public class Report {
	
	private static String screenshotName;
	
	public static void copyLatestExtentReport() throws IOException {
		File source = new File(System.getProperty("user.dir") + Configuration.getValue("report.file"));

		String date = getLastReportDate(source).replace(":", "_").replace(" ", "_").replace(".","_");
		File dest = new File(System.getProperty("user.dir") + String.format(Configuration.getValue("report.oldFile"), date));

		copyFileUsingStream(source, dest);
	}

	private static String getLastReportDate(File myFile) {
		try {
			Path path = myFile.toPath();

			BasicFileAttributes fatr;
			fatr = Files.readAttributes(path, 
					BasicFileAttributes.class);
			return fatr.lastModifiedTime().toString();

		} catch (IOException e) {}

		return null;
	}

	private static void copyFileUsingStream(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;

		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;

			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}

		} finally {
			is.close();
			os.close();
		}
	}
	
	public static String returnDateStamp(String fileExtension) {
		Date d = new Date();
		String date = d.toString().replace(":", "_").replace(" ", "_") + fileExtension;
		return date;
	}
	
	public static void reportScreenshot(File srcFile) throws IOException, InterruptedException {

		screenshotName = returnDateStamp(".png");

		FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + Configuration.getValue("image.directory") + screenshotName));

		Reporter.addStepLog("Taking a screenshot!");
		Reporter.addStepLog("<a target=\"_blank\", href="+ returnScreenshotName() + "><img src="+ returnScreenshotName()+ " height=320 width=180></img></a>");
	}

	public static String returnScreenshotName() {
		return (System.getProperty("user.dir") + Configuration.getValue("image.directory") + screenshotName).toString();
	}
}
