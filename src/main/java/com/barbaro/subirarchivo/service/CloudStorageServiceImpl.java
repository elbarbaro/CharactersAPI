package com.barbaro.subirarchivo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CloudStorageServiceImpl implements CloudStorageService {

	private static String TOKEN = "6FUsm6dM3ucy8giBVLfaDS86tQsZwikq";
	
	String url = "https://upload.box.com/api/2.0/files/content";
	
	@Override
	public void uploadFile(MultipartFile file) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		headers.setBearerAuth(TOKEN);
		LinkedMultiValueMap<String, Object> body = 
				new LinkedMultiValueMap<>();
		
		String fileName = file.getName()+new Date().getTime()+".jpg";
		Map<String, Object> attributes = new HashMap<>();
		Map<String, Object> attriParent = new HashMap<>();
		attributes.put("name", fileName);
		attriParent.put("id", "0");
		attributes.put("parent", attriParent);
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			String jsonData = mapper.writeValueAsString(attributes);
			System.out.println(jsonData);
			
			// Write file in system
			File file2 = new File("/var/tmp/" + fileName);
			FileOutputStream fileOutputStream = new FileOutputStream(file2);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			//FileInputStream fileInputStream = new FileInputStream(file2);
			
			// Read file in system
			FileSystemResource fileSystemResource = new FileSystemResource(file2);
			
			body.add("attributes", attributes);
			System.out.println(mapper.writeValueAsString(body));
			body.add("file", fileSystemResource);
			
			HttpEntity<MultiValueMap<String, Object>> reEntity
				= new HttpEntity<>(body, headers);
				
			RestTemplate restTemplate = new RestTemplate();
			//ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.POST, reEntity, String.class);
			ResponseEntity<String> resEntity = restTemplate.postForEntity(url, reEntity, String.class);
			
			System.out.println(resEntity.getStatusCodeValue());
			System.out.println(resEntity.getBody());
			
			file2.delete();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void uploadFileByUnirest(MultipartFile file) {
		
	}
	
	@Override
	public void uploadFileByNative(MultipartFile file) {
		
	}
}
