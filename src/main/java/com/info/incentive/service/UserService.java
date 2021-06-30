package com.info.incentive.service;


import com.info.incentive.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getList()  throws Exception;
    User save(User user)  throws Exception;
    User findById(Long id)  throws Exception;
    Optional<User> findByUserName(String username) throws Exception;
    User update(Long id, User user) throws Exception;
}
