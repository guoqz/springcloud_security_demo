package com.qtummatrix.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/hello")
    @PreAuthorize("@my.hasAnyAuthority('system:user:list')")
    public String hello(){
        return "hello security";
    }

}
