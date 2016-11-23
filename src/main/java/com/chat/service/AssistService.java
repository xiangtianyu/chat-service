package com.chat.service;

/**
 * Created by xiangtianyu on 2016/11/21.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.datamodel.dao.WhiteListDao;
import com.chat.datamodel.dto.ResultDTO;
import com.chat.datamodel.dto.WhiteListDTO;
import com.chat.datamodel.domain.WhiteList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.util.Convert;
import com.chat.util.Constrain;

@Service
public class AssistService extends BaseService {

    @Autowired
    private WhiteListDao whiteListDao;

    public List<WhiteListDTO> getAllWL() {
        List<WhiteList> wl = whiteListDao.findAllByValid(1);

        List<WhiteListDTO> list = new ArrayList<>();

        for (WhiteList w : wl) {
            list.add(Convert.convertWhiteListFromDomain(w));
        }

        return list;
    }

    public ResultDTO addWL(String ip, int isAuto, String sTime, String eTime) {
        ResultDTO res = new ResultDTO();

        if (!Convert.checkTimeFormat(sTime)) {
            throw new IllegalArgumentException("sTime TimeFomat error");
        }
        if (!Convert.checkTimeFormat(eTime)) {
            throw new IllegalArgumentException("eTime TimeFomat error");
        }
        if (whiteListDao.findByIpAndValid(ip, 1) != null) {
            res.setResult(1);
            res.setMessage("该ip已存在");
            return res;
        }
        WhiteList wl = new WhiteList();

        wl.setIp(ip);
        wl.setValid(1);
        wl.setIsAuto(isAuto);
        wl.setsTime(sTime);
        wl.seteTime(eTime);
        whiteListDao.save(wl);

        res.setResult(0);
        res.setMessage("添加白名单成功");
        return res;
    }

    public ResultDTO deleteWL(int id) {
        ResultDTO res = new ResultDTO();

        WhiteList wl = whiteListDao.findByIdAndValid(id, 1);

        if (wl == null) {
            res.setResult(1);
            res.setMessage("该ip不存在");
            return res;
        }

        wl.setValid(0);
        whiteListDao.save(wl);

        res.setResult(0);
        res.setMessage("删除成功");
        return res;
    }

    public ResultDTO updateWL(int id, int isAuto, String sTime, String eTime) {
        ResultDTO res = new ResultDTO();

        if (!Convert.checkTimeFormat(sTime)) {
            throw new IllegalArgumentException("sTime TimeFomat error");
        }
        if (!Convert.checkTimeFormat(eTime)) {
            throw new IllegalArgumentException("eTime TimeFomat error");
        }

        WhiteList wl = whiteListDao.findByIdAndValid(id, 1);

        if (wl == null) {
            res.setResult(1);
            res.setMessage("该ip不存在");
            return res;
        }

        wl.setIsAuto(isAuto);
        wl.setsTime(sTime);
        wl.seteTime(eTime);
        whiteListDao.save(wl);

        res.setResult(0);
        res.setMessage("更新白名单成功");
        return res;
    }

}
