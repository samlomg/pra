package com.dglbc.login.repository;

import com.dglbc.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by gdlbc on 2016/12/24.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    User findByEmail(String email);
    User findById(Long id);
}
