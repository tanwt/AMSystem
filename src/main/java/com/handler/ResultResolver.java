package com.handler;

import com.alibaba.fastjson.JSONObject;
import com.exception.ResultException;
import com.utils.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ResultResolver{

    private Logger logger = LoggerFactory.getLogger(ResultResolver.class);

    @ExceptionHandler(value = ResultException.class)
    @ResponseBody
    public JSONObject handle(Exception e){
        if (e instanceof ResultException){
            ResultException resultException = (ResultException) e;
            return CommonUtil.constructResponse(resultException.getCode(),resultException.getMessage(),null);
        }else {
            logger.error("[系统异常]{}",e);
            return CommonUtil.constructResponse(-1,"系统异常",null);
        }
    }
}
