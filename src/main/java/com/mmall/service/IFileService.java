package com.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by redLi on 2018/1/3.
 */
public interface IFileService {

    String upload(MultipartFile file, String path);
}
