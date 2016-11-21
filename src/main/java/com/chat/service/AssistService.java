package com.chat.service;

/**
 * Created by xiangtianyu on 2016/11/21.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import com.chat.datamodel.dao.WhiteListDao;
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

}
