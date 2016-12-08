package com.chat.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.datamodel.dao.UserDao;
import com.chat.datamodel.dao.UserRelationDao;
import com.chat.datamodel.domain.UserRelation;
import com.chat.datamodel.dto.UserDTO;
import com.chat.datamodel.dto.ResultDTO;
import com.chat.datamodel.domain.User;

import com.chat.datamodel.dto.UserInfoDTO;
import com.chat.util.RedisUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.chat.util.Convert;
import com.chat.util.Constrain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserService extends BaseService {

    @Autowired
    private UserDao                 userDao;

    @Autowired
    private UserRelationDao         userRelationDao;

    @Autowired
    private RedisUtils              redisUtils;


    public List<UserDTO> findAllUser() {
        List<User> user = userDao.findAllByValid(1);

        List<UserDTO> alluser = new ArrayList<UserDTO>();

        for (User u : user) {
            alluser.add(Convert.convertUserFromDomain(u));
        }

        return alluser;
    }

    public ResultDTO uRegister(String username, String password, String createTime) throws NoSuchAlgorithmException{
        ResultDTO res = new ResultDTO();
        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        byte[] rSalt = Convert.createRandomSalt();
        String sSalt = Convert.byteToHexString(rSalt);
        String pw = password + Constrain.Salt + sSalt;

        byte[] sha256Encode = sha256Digest.digest(pw.getBytes());

        String shaPW = Convert.byteToHexString(sha256Encode);

        if (!Convert.checkDateFormat(createTime)) {
            throw new IllegalArgumentException("DateFomat error");
        }
        if (userDao.findUserByUserNameAndValid(username, 1) != null) {
            res.setResult(1);
            res.setMessage("用户已存在");
            res.setUid(-1);
            return res;
        }
        User user = new User();
        user.setUserName(username);
        user.setPassWord(shaPW);
        user.setCreateTime(createTime);
        user.setSalt(sSalt);
        user.setValid(1);
        userDao.save(user);

        res.setResult(0);
        res.setMessage("用户创建成功");
        res.setUid(userDao.findUserByUserNameAndValid(username, 1).getUserId());
        return res;
    }

    public ResultDTO uUpdate(int uid, String password) throws NoSuchAlgorithmException{
        ResultDTO res = new ResultDTO();

        User user = userDao.findUserByUserIdAndValid(uid, 1);

        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        byte[] rSalt = Convert.createRandomSalt();
        String sSalt = Convert.byteToHexString(rSalt);
        String pw = password + Constrain.Salt + sSalt;

        byte[] sha256Encode = sha256Digest.digest(pw.getBytes());

        String shaPW = Convert.byteToHexString(sha256Encode);

        if (user == null) {
            res.setResult(1);
            res.setMessage("用户不存在");
            res.setUid(-1);
            return res;
        }

        user.setPassWord(shaPW);
        user.setSalt(sSalt);
        user.setValid(1);
        userDao.save(user);

        res.setResult(0);
        res.setMessage("用户更新成功");
        res.setUid(uid);
        return res;
    }

    public ResultDTO uLogin(String username, String password, HttpServletRequest request) throws NoSuchAlgorithmException{
        ResultDTO res = new ResultDTO();
        User user = userDao.findUserByUserNameAndValid(username, 1);

        if (user == null) {
            res.setResult(1);
            res.setMessage("用户不存在");
            res.setUid(-1);
            return res;
        }

        MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");
        String sSalt = user.getSalt();
        String pw = password + Constrain.Salt + sSalt;

        byte[] sha256Encode = sha256Digest.digest(pw.getBytes());

        String shaPW = Convert.byteToHexString(sha256Encode);

        if (!shaPW.equals(user.getPassWord())) {
            res.setResult(2);
            res.setMessage("用户名密码错误");
            res.setUid(-1);
            return res;
        }


        String uid = Integer.toString(user.getUserId());

        HttpSession session = request.getSession();
        String sessionId = session.getId();
        if (redisUtils.exists(uid) && !redisUtils.get(uid).toString().equals(sessionId)) {
            res.setResult(3);
            res.setMessage("该帐号已经登录");
            res.setUid(user.getUserId());
        }
        else {
            Date now = new Date();
            String ip = Convert.getIpAddr(request);
            session.setAttribute("uid", user.getUserId());
            session.setAttribute("username", username);
            session.setAttribute("loginTime", now);
            session.setAttribute("ip", ip);
            session.setMaxInactiveInterval(3600*24*7);
            redisUtils.setDay(uid, sessionId, 7);

            res.setResult(0);
            res.setMessage("登录成功");
            res.setUid(user.getUserId());
        }

        return res;
    }

    public ResultDTO uLogout(int uid, HttpServletRequest request) {
        ResultDTO resultDTO = new ResultDTO();
        String id = Integer.toString(uid);
        HttpSession session = request.getSession();
        if (redisUtils.exists(id) && redisUtils.get(id).toString().equals(session.getId())) {
            redisUtils.remove(Integer.toString(uid));
            resultDTO.setResult(0);
            resultDTO.setMessage("logout success");
        }
        else {
            resultDTO.setResult(1);
            resultDTO.setMessage("logout failure");
        }

        return resultDTO;
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



    public ResultDTO addFriend(int uid, int fid, String createTime) {
        ResultDTO res = new ResultDTO();

        if (!Convert.checkDateFormat(createTime)) {
            throw new IllegalArgumentException("DateFomat error");
        }
        if (uid == fid) {
            res.setResult(1);
            res.setMessage("不能添加自己为好友");
            res.setUid(-1);
            return res;
        }
        else {
            if (userDao.findUserByUserIdAndValid(uid, 1) == null
                    || userDao.findUserByUserIdAndValid(fid, 1) == null) {
                res.setResult(2);
                res.setMessage("用户不存在");
                res.setUid(-1);
                return res;
            }
            else if (userRelationDao.findByUidAndFidAndValid(uid, fid, 1) != null
                    || userRelationDao.findByUidAndFidAndValid(fid, uid, 1) != null) {
                res.setResult(3);
                res.setMessage("好友关系已经存在");
                res.setUid(-1);
                return res;
            }
        }


        UserRelation userRelation = new UserRelation();
        userRelation.setUid(uid);
        userRelation.setFid(fid);
        userRelation.setValid(1);
        userRelation.setCreateTime(createTime);
        userRelationDao.save(userRelation);

        res.setResult(0);
        res.setMessage("好友添加成功");
        res.setUid(uid);
        return res;
    }

    public ResultDTO deleteFriend(int uid, int fid) {
        ResultDTO res = new ResultDTO();

        UserRelation userRelation = userRelationDao.findByUidAndFidAndValid(uid, fid, 1);
        UserRelation userRelation2 = userRelationDao.findByUidAndFidAndValid(fid, uid, 1);

        if (userRelation == null && userRelation2 == null) {
            res.setResult(1);
            res.setMessage("好友关系不存在");
            res.setUid(-1);
            return res;
        }

        if (userRelation != null) {
            userRelation.setValid(0);
            userRelationDao.save(userRelation);
        }
        else {
            userRelation2.setValid(0);
            userRelationDao.save(userRelation2);
        }

        res.setResult(0);
        res.setMessage("好友关系删除成功");
        res.setUid(uid);
        return res;
    }

    public List<UserInfoDTO> getFriends(int uid) {
        List<UserInfoDTO> friends = new ArrayList<>();

        List<UserRelation> r1 = userRelationDao.findByFidAndValid(uid, 1);
        List<UserRelation> r2 = userRelationDao.findByUidAndValid(uid, 1);
        int len1 = r1.size();

        for (int i = 0; i < len1; i++) {
            UserInfoDTO  user = new UserInfoDTO();
            User u = userDao.findUserByUserIdAndValid(r1.get(i).getUid(), 1);
            user.setUid(u.getUserId());
            user.setUsername(u.getUserName());
            friends.add(user);
        }

        int len2 = r2.size();

        for (int i = 0; i < len2; i++) {
            UserInfoDTO  user = new UserInfoDTO();
            User u = userDao.findUserByUserIdAndValid(r2.get(i).getFid(), 1);
            user.setUid(u.getUserId());
            user.setUsername(u.getUserName());
            friends.add(user);
        }

        return friends;
    }
}