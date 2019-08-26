package com.ecjtu.amovie.config;


import com.ecjtu.amovie.utils.result.JsonResult;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xianaixan
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exception(Exception e) {
        e.printStackTrace();
        log.error(e.getLocalizedMessage());
        String template = "服务器错误:[ %s ]";
        return JsonResult.error(500, String.format(template, e.getLocalizedMessage()));
    }

}
