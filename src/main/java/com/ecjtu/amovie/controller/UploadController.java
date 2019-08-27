package com.ecjtu.amovie.controller;

import com.ecjtu.amovie.entity.Scene;
import com.ecjtu.amovie.utils.result.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.DispatcherServlet;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @author xianaxian  2019-08-26 21:51
 */
@CrossOrigin
@Validated
@Controller
@RequestMapping("/api")
public class UploadController {
//    DispatcherServlet

    private static final String UPLOAD_DIR = "D:/upload/";

    @ResponseBody
    @RequestMapping("/upload")
    public JsonResult<String> upload(@NotNull MultipartFile file) throws IOException {
        String newFileName = newFileName(file);
        checkDirExists(UPLOAD_DIR);
        File newFile = new File(UPLOAD_DIR + newFileName);
        file.transferTo(newFile);
        return JsonResult.success("上传成功", "/static/file/" + newFileName);
    }


    @SuppressWarnings("ALL")
    private static void checkDirExists(String uploadDir) {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    private String newFileName(MultipartFile file) {
        int i = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
        return UUID.randomUUID().toString() + file.getOriginalFilename().substring(i);
    }

}
