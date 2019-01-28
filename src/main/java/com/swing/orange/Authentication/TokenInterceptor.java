package com.swing.orange.Authentication;

import com.google.gson.Gson;
import com.swing.orange.utils.RestResult;
import com.swing.orange.utils.RestResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TokenInterceptor implements HandlerInterceptor {

//    @Autowired
//    private UserDao userRepository;

    private final ArrayList<String> urls = new ArrayList<String>(){{
        add("/");
        add("/user/login");
        add("/user/register");
    }};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;

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

        if (urls.contains(request.getRequestURI())) {
            return true;
        }
        // 然后验证token
        String token = request.getHeader("token");
        String uid = request.getHeader("uid");
        if (this.preAuthenticationHandle(response, token, uid) == false) {
            return false;
        }
        User user = this.userRepository.findById(Integer.valueOf(uid));
        if (JWTUtil.verify(token, uid, user.getPassword())) {
            return true;
        } else {
            System.out.println("token已失效");
            responseMessage(response, response.getWriter(),10002, "token已失效");
            return false;
        }
        */
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView model) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception arg3) throws Exception {

    }

    private boolean preAuthenticationHandle(HttpServletResponse response, String token, String uid) throws Exception {
        if (token == null) {
            responseMessage(response, response.getWriter(),10002, "token为null");
            return false;
        }

        if (uid == null) {
            responseMessage(response, response.getWriter(),10002, "uid为null");
            return false;
        }
        return true;
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
