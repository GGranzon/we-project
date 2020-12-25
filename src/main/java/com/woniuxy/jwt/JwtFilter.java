package com.woniuxy.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.woniuxy.component.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    @Resource
    private RedisClient redis;

    /**
     * 如果带有 token ，则对 token 进行检查，否则直接通过
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("自定义过滤器开始验证token。。。。。。。");
        //判断请求的请求头是否带上了 "Token"
        if(isLoginAttempt(request,response)){
            //如果存在，则进入 executeLogin 方法执行登入，检查 token 是否正确
            try{
                executeLogin(request,response);
                return true;
            }catch (Exception e){
                //token 错误
                log.info("token验证未通过..........");
                return false;
            }
        }
        //请求头未附带 token ，则可能是执行登录操作，或者是游客状态访问，无需检查 token ，直接返回 false
        log.info("当前请求中未附带 token ............");
        return false;
    }

    /**
     * 判断用户是否想要登入 ， 检查 header 里面是否包含 Token字段
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        //从请求头中获取 Token
        String token = req.getHeader("Token");
        return token != null;
    }

    /**
     * 执行登录操作
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Token");
        //创建自定义令牌
        JwtToken jwtToken = new JwtToken(token);
        //提交给 realm 进行登入，如果错误 会抛出异常并被捕获
        getSubject(request,response).login(jwtToken);
        //如果没有抛出异常，则代表登入成功，返回 true
        return true;
    }

    /**
     * 对跨域提供支持
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

//    protected boolean refreshToken(ServletRequest request,ServletResponse response){
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        //拿到当前Header中的token
//        String token = httpServletRequest.getHeader("Token");
//        //获取当前Token的用户信息
//        String username = JwtUtil.getUsername(token);
//        //判断Redis中RefreshToken是否存在
//        if(redis.hasKey(username)){
//            //Redis中RefreshToken还存在，获取其时间戳
//            String currentTimeMillisRedis = redis.get(username).toString();
//            //获取当前token的时间戳，与RefreshToken的时间戳对比，如果当前时间戳一致，刷新token
//            String currentTimeMillis = JWT.decode(token).getClaim("currentTimeMillis").toString();
//            if(currentTimeMillis.equals(currentTimeMillisRedis)){
//                //获取当前最新时间戳
//                String currentTimeMillis1 = String.valueOf(System.currentTimeMillis());
//                //设置RefreshToken中的时间戳为当前最新时间戳
//                redis.set(username,currentTimeMillis1);
//            }
//        }
//    }
}
