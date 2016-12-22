package com.chat.datamodel.dao;

import com.chat.datamodel.domain.Chapter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiangtianyu on 2016/12/15.
 */
@Repository
public interface ChapterDao extends CrudRepository<Chapter, Long> {
    List<Chapter> findAll();

    @Query("select c from Chapter c order by c.cIndex")
    List<Chapter> findChapter();
}
