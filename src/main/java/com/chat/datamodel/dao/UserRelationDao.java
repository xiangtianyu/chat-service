package com.chat.datamodel.dao;

import java.util.List;
import com.chat.datamodel.domain.UserRelation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by xiangtianyu on 2016/11/18.
 */
public interface UserRelationDao extends CrudRepository<UserRelation, Long> {
    List<UserRelation> findByUidAndValid(int uid, int valid);

    List<UserRelation> findByFidAndValid(int fid, int valid);

    UserRelation findByUidAndFidAndValid(int uid, int fid, int valid);
}
