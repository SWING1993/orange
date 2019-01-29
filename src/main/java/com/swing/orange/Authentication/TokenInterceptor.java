package com.swing.orange.Authentication;

import com.google.gson.Gson;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TokenInterceptor implements HandlerInterceptor {

    private final ArrayList<String> urls = new ArrayList<String>(){{
        add("/user/authCode");
        add("/user/register");
        add("/user/login");
    }};

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

        if (urls.contains(request.getRequestURI())) {
            return true;
        }
        // 验证token
        String access_token = request.getHeader("access_token");
        if (access_token == null) {
            responseMessage(response, response.getWriter(),10002, "access_token不能为空");
            return false;
        }

        String uid = request.getHeader("uid");
        if (uid == null) {
            responseMessage(response, response.getWriter(),10002, "uid为不能为空");
            return false;
        }

        if (!JWTUtil.getUid(access_token).equals(uid)) {
            responseMessage(response, response.getWriter(),10002, "access_token错误");
            return false;
        }

        if (!JWTUtil.verify(access_token, uid, "token")) {
            responseMessage(response, response.getWriter(),10002, "access_token已失效");
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
