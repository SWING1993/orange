package com.swing.orange.controller;

import com.swing.orange.Authentication.JWTUtil;
import com.swing.orange.utils.DingChatBot;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

    private static final Logger loger = LoggerFactory.getLogger(JWTUtil.class);

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestResult<String> globalException(HttpServletRequest request, Throwable ex) throws Exception {
        String error = this.getStatus(request)+ "  " + ex.getLocalizedMessage();
        System.out.println("Error: " + error);
        loger.error("Error: " + error, ex);
        DingChatBot.sendMsg(error);
        return RestResultGenerator.genErrorResult(error);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
