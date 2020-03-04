package com.barbaro.subirarchivo.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudStorageService {
	
	void uploadFile(MultipartFile file);
	void uploadFileByUnirest(MultipartFile file);
	void uploadFileByNative(MultipartFile file);
}
