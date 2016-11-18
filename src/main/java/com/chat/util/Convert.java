package com.chat.util;

import java.util.ArrayList;
import java.util.List;

import com.chat.datamodel.domain.User;
import com.chat.datamodel.dto.UserDTO;

public class Convert {
    public static UserDTO convertUserFromDomain (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassWord(user.getPassWord());
        userDTO.setCreateTime(user.getCreateTime());
        return userDTO;
    }
}