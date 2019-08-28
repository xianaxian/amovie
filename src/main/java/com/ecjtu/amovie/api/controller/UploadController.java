package com.ecjtu.amovie.api.controller;

import com.ecjtu.amovie.utils.result.JsonResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Image{
        private String url;
    }

    private static final String UPLOAD_DIR = "D:/upload/";

    @ResponseBody
    @RequestMapping("/upload")
    public JsonResult upload(@NotNull MultipartFile file)  {
        try {
            String newFileName = newFileName(file);
            checkDirExists(UPLOAD_DIR);
            File newFile = new File(UPLOAD_DIR + newFileName);
            file.transferTo(newFile);
            Image image=new Image("/file/" + newFileName);
            return JsonResult.success("上传成功", image);
        } catch (IOException e) {
            throw new RuntimeException("上传文件失败");
        }
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
