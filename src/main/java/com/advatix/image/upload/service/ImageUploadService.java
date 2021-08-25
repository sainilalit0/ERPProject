package com.advatix.image.upload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadService {

	public final String UPLOAD_DIR ="D:\\unit\\testing\\BookAppSpringBoot2\\src\\main\\resources\\static\\image";
	//public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

	public ImageUploadService() throws IOException {

	}

	public boolean uploadImage(MultipartFile multipartFile) {
		boolean file = false;
		try {
			/*
			 * InputStream inputStream=multipartFile.getInputStream(); byte data[]=new
			 * byte[inputStream.available()]; inputStream.read(); // write data
			 * FileOutputStream fileOutputStream=new FileOutputStream(UPLOAD_DIR+File.
			 * separator+"D:\\unit testing\\BookAppSpringBoot2\\src\\main\\resources\\static\\image"
			 * ); fileOutputStream.write(data); fileOutputStream.flush();
			 * fileOutputStream.close();
			 */
			// Other approach
			Files.copy(multipartFile.getInputStream(),
					Paths.get(UPLOAD_DIR + File.separator + multipartFile.getOriginalFilename()),
					StandardCopyOption.REPLACE_EXISTING);
			file = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;

	}

}
