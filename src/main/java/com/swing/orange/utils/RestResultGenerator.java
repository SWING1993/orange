package com.swing.orange.utils;

import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RestResultGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResultGenerator.class);

    private static final SimpleDateFormat simpleDateFormat =  new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss" );

    /**
     * normal
     * @param success
     * @param data
     * @param message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genResult(boolean success, T data, String message, int code, String error) {
        RestResult result = RestResult.newInstance();
        result.setSuccess(success);
        result.setResult(data);
        result.setCode(code);
        result.setMessage(message);
        result.setError(error);
        result.setTimestamp(RestResultGenerator.simpleDateFormat.format(new Date()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("generate rest result:",result);
        }
        return result;
    }

    /**
     * success no message
     * @return
     */
    public static RestResult genSuccessResult() {
        return genResult(true, "", "处理成功", 10000, "");
    }

    /**
     * success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genSuccessResult(T data) {
        return genResult(true,data,"处理成功", 10000, "");
    }

    public static RestResult genSuccessResult(Page page) {
        Map map = new HashMap();
        map.put("pageSize", page.getPageSize());
        map.put("total", page.getTotal());
        map.put("pageNum", page.getPageNum());
        map.put("list", page);
        return genResult(true,map,"处理成功", 10000, "");
    }

    /**
     * error message
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(String message) {
        return genResult(false,null, message, 10001, "");
    }

    /**
     * error message
     * @param message error message
     * @param <T>
     * @return
     */
    public static <T> RestResult<T> genErrorResult(String message, int code, String error) {
        return genResult(false,null, message, code, error);
    }
}
