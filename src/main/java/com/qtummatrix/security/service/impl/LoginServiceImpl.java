package com.qtummatrix.security.service.impl;

import com.qtummatrix.security.entity.LoginUser;
import com.qtummatrix.security.entity.User;
import com.qtummatrix.security.service.LoginService;
import com.qtummatrix.security.utils.JsonUtil;
import com.qtummatrix.security.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        // 调用 authenticate() -> UserDetailsServiceImpl 实现类到数据库查询用户信息
        // 封装的 authentication 对象里面存储了用户的登录信息
        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        // 如果认证不通过，给出提示
        if (Objects.isNull(authentication)) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 认证通过，使用 userId 生成 jwt
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);

        HashMap<String, String> map = new HashMap<>();
        map.put("token", jwt);

        // 把完整的用户信息存入 redis, "login:"+userId 作为 key
        redisTemplate.opsForValue().set("login:" + userId, loginUser);

        return JsonUtil.JsonSerializer(map, 200, "登录成功");
    }

    @Override
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        Integer userId = loginUser.getUser().getId();
        redisTemplate.delete("login:" + userId);

        return JsonUtil.JsonSerializer(null, 200, "退出登录");
    }


}
