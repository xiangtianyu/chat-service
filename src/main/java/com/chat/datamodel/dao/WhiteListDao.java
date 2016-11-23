package com.chat.datamodel.dao;

/**
 * Created by xiangtianyu on 2016/11/21.
 */

import java.util.List;

import com.chat.datamodel.domain.WhiteList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WhiteListDao extends CrudRepository<WhiteList, Long>{
    List<WhiteList> findAllByValid(int valid);

    WhiteList findByIpAndValid(String ip, int valid);

    WhiteList findByIdAndValid(int id, int valid);
}
