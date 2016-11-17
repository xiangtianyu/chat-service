package com.chat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.datamodel.dao.UserDao;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.domain.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.util.Convert;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserDao                 userDao;


    public List<UserDTO> findAllUser() {
        List<User> user = userDao.findAll();

        List<UserDTO> alluser = new ArrayList<UserDTO>();

        for (User u : user) {
            alluser.add(Convert.convertUserFromDomain(u));
        }

        return alluser;
    }
}