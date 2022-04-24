package com.db.nace.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class NaceUtils {
	
	
		
		 public static String saveFile(String uploadDir, String fileName,
		            MultipartFile multipartFile) throws IOException {
		        Path uploadPath = Paths.get(uploadDir);
		        Path filePath = null; 
		         
		        if (!Files.exists(uploadPath)) {
		            Files.createDirectories(uploadPath);
		        }
		         
		        try (InputStream inputStream = multipartFile.getInputStream()) {
		            filePath = uploadPath.resolve(fileName);
		            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		        } catch (IOException ioe) {        
		            throw new IOException("Could not save image file: " + fileName, ioe);
		        }
				return filePath != null ? filePath.toString() : null;      
		    
	}

}
