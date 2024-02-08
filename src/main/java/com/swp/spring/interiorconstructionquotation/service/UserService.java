package com.swp.spring.interiorconstructionquotation.service;

import com.swp.spring.interiorconstructionquotation.dao.IUserRepository;
import com.swp.spring.interiorconstructionquotation.dao.IRoleRepository;
import com.swp.spring.interiorconstructionquotation.entity.Role;
import com.swp.spring.interiorconstructionquotation.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private IUserRepository iUserRepository;
    private IRoleRepository iRoleRepository;
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(IUserRepository iUserRepository, IRoleRepository iRoleRepository) {
        this.iUserRepository = iUserRepository;
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public User findByUsername(String username) {
        return iUserRepository.findByUsername(username);
    }


    @Override
    public void save(User user) {
        iUserRepository.save(user);
    }

    @Override
    public void updatepassword(String password, Long id) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    @Transactional
    public String changePassword(String currentPassword, String newPassword, String confirmPassword) {
        // Fetch the current user from the database (you may need to implement this method in UserRepository)
        User currentUser = iUserRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

        // Check if the current password matches the password stored in the database
        if (!passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
            return "Your current password is incorrect. Please try again.";
        }

        // Check if the new password matches the confirm password
        if (!newPassword.equals(confirmPassword)) {
            return "New password and confirm password do not match.";
        }

        // Update the password
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        iUserRepository.save(currentUser);

        return "Password updated successfully.";
    }



}
