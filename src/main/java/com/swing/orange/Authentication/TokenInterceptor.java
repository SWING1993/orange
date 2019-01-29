package com.swing.orange.Authentication;

import com.google.gson.Gson;
import com.swing.orange.utils.DingChatBot;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*
        // 先验证签名
        Enumeration<?> pNames = request.getParameterNames();
        Map<String, Object> params = new HashMap<String, Object>();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            Object pValue = request.getParameter(pName);
            params.put(pName, pValue);
        }
        if (!Signature.verificationSign(request.getHeader(Signature.SIGN_KEY), params)) {
            System.out.println("签名错误");
            responseMessage(response, response.getWriter(),10002, "签名错误");
            return false;
        }
        */

        // 验证token
        String token = request.getHeader("token");
        if (token == null) {
            responseMessage(response, response.getWriter(),10002, "token不能为空");
            return false;
        }

        String uid = request.getHeader("uid");
        if (uid == null) {
            responseMessage(response, response.getWriter(),10002, "uid为不能为空");
            return false;
        }

        int verify = JWTUtil.verify(token, uid, "token");

        if (verify == 1) {
            responseMessage(response, response.getWriter(),10003, "token已过期，需要刷新");
            return false;
        }

        if (verify > 1) {
            responseMessage(response, response.getWriter(),10003, "token无效");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {

    }

    private void responseMessage(HttpServletResponse response, PrintWriter out, int code, String error) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=utf-8");
        RestResult result = RestResultGenerator.genErrorResult("403，认证不通过", code,error);
        Gson gson = new Gson();
        out.print(gson.toJson(result));
        out.flush();
        out.close();
    }

}
