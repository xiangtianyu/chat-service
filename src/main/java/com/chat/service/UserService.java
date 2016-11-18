package com.chat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.datamodel.dao.UserDao;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.dto.ResultDTO;
import com.chat.datamodel.domain.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.util.Convert;
import com.chat.util.Constrain;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserDao                 userDao;


    public List<UserDTO> findAllUser() {
        List<User> user = userDao.findAllByValid(1);

        List<UserDTO> alluser = new ArrayList<UserDTO>();

        for (User u : user) {
            alluser.add(Convert.convertUserFromDomain(u));
        }

        return alluser;
    }

    public ResultDTO uRegister(String username, String password, String createTime) {
        ResultDTO res = new ResultDTO();
        if (!checkDateFormat(createTime)) {
            throw new IllegalArgumentException("timeFomat error");
        }
        if (userDao.findUserByUserNameAndValid(username, 1) != null) {
            res.setResult(1);
            res.setMessage("用户已存在");
            res.setUid(-1);
            return res;
        }
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        user.setCreateTime(createTime);
        user.setValid(1);
        userDao.save(user);

        res.setResult(0);
        res.setMessage("用户创建成功");
        res.setUid(userDao.findUserByUserNameAndValid(username, 1).getUserId());
        return res;
    }

    public ResultDTO uLogin(String username, String password) {
        ResultDTO res = new ResultDTO();
        User user = userDao.findUserByUserNameAndValid(username, 1);
        if (user == null) {
            res.setResult(1);
            res.setMessage("用户不存在");
            res.setUid(-1);
            return res;
        }
        if (!password.equals(user.getPassWord())) {
            res.setResult(2);
            res.setMessage("用户名密码错误");
            res.setUid(-1);
            return res;
        }

        res.setResult(0);
        res.setMessage("登录成功");
        res.setUid(user.getUserId());
        return res;
    }

    public ResultDTO uDelete(int uid) {
        ResultDTO res = new ResultDTO();
        User user = userDao.findUserByUserIdAndValid(uid, 1);
        if (user == null) {
            res.setResult(1);
            res.setMessage("用户不存在");
            res.setUid(-1);
            return res;
        }
        user.setValid(0);
        userDao.save(user);

        res.setResult(0);
        res.setMessage("用户删除成功");
        res.setUid(uid);
        return res;
    }

    private boolean checkTimeFormat(String date) {
        if (date.length() != Constrain.TimeFormat.length())
            return false;
        for (int i = 0; i < date.length(); i++) {
            switch (i) {
                case 4:
                case 7:
                    if (date.toCharArray()[i] != '-')
                        return false;
                    break;
                case 10:
                    if (date.toCharArray()[i] != ' ')
                        return false;
                    break;
                case 13:
                case 16:
                    if (date.toCharArray()[i] != ':')
                        return false;
                    break;
                default:
                    if (!Character.isDigit(date.toCharArray()[i]))
                        return false;
                    break;
            }
        }
        return true;
    }

    private boolean checkDateFormat(String date) {
        if (date.length() != Constrain.DateFormat.length())
            return false;
        for (int i = 0; i < date.length(); i++) {
            switch (i) {
                case 4:
                case 7:
                    if (date.toCharArray()[i] != '-')
                        return false;
                    break;
                default:
                    if (!Character.isDigit(date.toCharArray()[i]))
                        return false;
                    break;
            }
        }
        return true;
    }
}