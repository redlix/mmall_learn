package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author rerdli
 * @time
 * @description
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
