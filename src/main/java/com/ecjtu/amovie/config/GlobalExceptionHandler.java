package com.ecjtu.amovie.config;


import com.ecjtu.amovie.utils.result.JsonResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//@Component
@ControllerAdvice
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(Exception.class)
    public JsonResult<String> exception(RuntimeException e) {
        String template = "服务器错误:[ %s ]";
        return JsonResult.error(500, String.format(template, e.getLocalizedMessage()));
    }


}
