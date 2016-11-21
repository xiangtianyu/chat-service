package com.chat.util;

import java.util.ArrayList;
import java.util.List;

import com.chat.datamodel.domain.User;
import com.chat.datamodel.domain.WhiteList;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.dto.WhiteListDTO;

public class Convert {
    public static UserDTO convertUserFromDomain (User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUserId());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassWord(user.getPassWord());
        userDTO.setCreateTime(user.getCreateTime());
        return userDTO;
    }

    public static WhiteListDTO convertWhiteListFromDomain (WhiteList wl) {
        WhiteListDTO w = new WhiteListDTO();
        w.setId(wl.getId());
        w.setIp(wl.getIp());
        w.setIsAuto(wl.getIsAuto());
        w.setsTime(wl.getsTime());
        w.seteTime(wl.geteTime());
        w.setValid(wl.getValid());
        return w;
    }
}