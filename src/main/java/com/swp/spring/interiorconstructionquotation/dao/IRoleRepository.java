package com.swp.spring.interiorconstructionquotation.dao;

import com.swp.spring.interiorconstructionquotation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(String name);

    interface ForgotPasswordRepo {
    }
}
