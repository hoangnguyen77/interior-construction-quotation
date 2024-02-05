package com.swp.spring.interiorconstructionquotation.service.user;

import com.swp.spring.interiorconstructionquotation.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface IUserService extends UserDetailsService {
    public User findByUsername(String username);
    public void save(User user);
}
