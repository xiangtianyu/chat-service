package com.chat.datamodel.dao;

import com.chat.datamodel.domain.DianPing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by xiangtianyu on 2017/1/14.
 */
@Repository
public interface DianPingDao extends CrudRepository<DianPing, Long> {

}
