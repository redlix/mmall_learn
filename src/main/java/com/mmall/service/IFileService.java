package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author rerdli
 * @time
 * @description
 */
public interface IFileService {

    /**
     * 文件上传
     *
     * @param file
     * @param path
     * @return
     */
    String upload(MultipartFile file, String path);
}
