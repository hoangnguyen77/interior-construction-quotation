package com.swp.spring.interiorconstructionquotation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swp.spring.interiorconstructionquotation.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByPassword(String password);
    @Query("select x from User x where x.username = :username and x.password = :password ")
    User findUserByUsernameAndPassword(String username, String password);
}
