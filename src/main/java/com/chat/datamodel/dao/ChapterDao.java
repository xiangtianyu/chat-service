package com.chat.datamodel.dao;

import com.chat.datamodel.domain.Chapter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by xiangtianyu on 2016/12/15.
 */
public interface ChapterDao extends CrudRepository<Chapter, Long> {
    List<Chapter> findAll();
}
