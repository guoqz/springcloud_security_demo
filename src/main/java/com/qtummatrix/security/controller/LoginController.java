package com.qtummatrix.security.controller;

import com.qtummatrix.security.entity.User;
import com.qtummatrix.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


    /**
     * @param user 登录用户：tom
     *             登录密码：123     $2a$10$ep/klcO7zBqwp.swFhM09eP94u/lwJKjYQQh1jVMgjfhJX6BEeStO
     * @return
     */
    @PostMapping(value = "/user/login", produces = "text/json;charset=utf-8")
    public String login(@RequestBody User user) {
        return loginService.login(user);
    }


    @PostMapping(value = "/user/logout", produces = "text/json;charset=utf-8")
    public String logout() {
        return loginService.logout();
    }

}
