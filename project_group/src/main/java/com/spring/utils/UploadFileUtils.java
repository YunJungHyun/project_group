package com.spring.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

import net.coobird.thumbnailator.Thumbnails;

public class UploadFileUtils {

	static final int THUMB_WIDTH = 32;
	static final int THUMB_HEIGHT = 32;

	public static String fileUpload(String uploadPath, 
			String fileName,
			byte[] fileData, String ymdPath) throws Exception {

		UUID uid = UUID.randomUUID();

		String newFileName = uid + "_" + fileName;
		String imgPath = uploadPath + ymdPath;

		File target = new File(imgPath, newFileName);
		FileCopyUtils.copy(fileData, target);

		String thumbFileName = "thum_" + newFileName;
		File image = new File(imgPath + File.separator + newFileName);

		File thumbnail = new File(imgPath + File.separator + "thum" + File.separator + thumbFileName);

		if (image.exists()) {
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(image).size(THUMB_WIDTH, THUMB_HEIGHT).toFile(thumbnail);
		}
		
		return newFileName;
	}

	public static String calcPath(String uploadPath, String usercode) {
		Calendar cal = Calendar.getInstance();
		String usercodePath =File.separator +usercode;
		String datePath = usercodePath+File.separator+ cal.get(Calendar.YEAR)
				+"_"+new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1)
				+"_"+ new DecimalFormat("00").format(cal.get(Calendar.DATE));
			
		makeDir(uploadPath, usercodePath, datePath);
		makeDir(uploadPath, usercodePath, datePath + "\\thum");

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(paths[paths.length - 1]).exists()) { return; }

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
}