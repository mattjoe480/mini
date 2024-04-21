package com.exam.server.controller;

import com.exam.server.entity.UserLoginInfo;
import com.exam.server.model.UserLoginGQL;
import com.exam.server.service.UserLoginService;

import lombok.extern.log4j.Log4j2;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;

@Log4j2
@Controller
public class LoginController {


    private final UserLoginService userLoginService;

    public LoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @QueryMapping
    public UserLoginInfo getUserLoginInfo(@Argument String userName) {
        return userLoginService.findByFirstName(userName);
    }
  @MutationMapping
  public Boolean addUser(@Argument UserLoginGQL userLoginInfo) {
    try {

        log.info("Saving student: {}", userLoginInfo);
        userLoginService.save(userLoginService.toUserLoginInfo(userLoginInfo));
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}

