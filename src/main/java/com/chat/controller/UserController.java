package com.chat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.service.UserService;
import com.chat.datamodel.dto.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/findall", method = RequestMethod.GET)
    public List<UserDTO> getAllUser() {
        List<UserDTO> alluser = userService.findAllUser();
        return alluser;
    }

}