package com.qtummatrix.security.service;

import com.qtummatrix.security.entity.User;

public interface LoginService {

    String login(User user);

    String logout();

}
