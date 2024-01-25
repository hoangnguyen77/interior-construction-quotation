package com.swp.spring.interiorconstructionquotation.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.swp.spring.interiorconstructionquotation.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
