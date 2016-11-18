package com.chat.datamodel.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.chat.datamodel.domain.User;

public interface UserDao extends CrudRepository<User, Long> {
    public List<User> findAll();

    public User findUserByUserName(String userName);

}
