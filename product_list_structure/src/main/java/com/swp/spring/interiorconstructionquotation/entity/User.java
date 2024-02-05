package com.swp.spring.interiorconstructionquotation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.sql.Blob;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "username")
    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min=1, message = "Min Length : 1")
    private String username;
    @Column(name = "password", length = 256)
    @NotBlank(message = "Thông tin bắt buộc")
    @Size(min=8, message = "Mật khẩu cần ít nhất 8 kí tự")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).*$",
            message = "Mật khẩu cần chứa ít nhất một chữ số và một kí tự đặc biệt")
    private String password;
    @Column(name = "first_name")
    @NotBlank(message = "Thông tin bắt buộc")
    private String firstName;
    @Column(name = "last_name")
    @NotBlank(message = "Thông tin bắt buộc")
    private String lastName;
    @Column(name = "email")
    @NotBlank(message = "Thông tin bắt buộc")
    private String email;
    @Column(name = "phonenumber")
    @NotBlank(message = "Thông tin bắt buộc")
    @Pattern(regexp = "^(0|\\+84)\\d{9,10}$",
            message = "Số điện thoại không hợp lệ")
    private String phonenumber;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Collection<Role> roles;

    public User() {
    }

    public User(Long id, String username, String password, String firstName, String lastName, String email, String phonenumber, Collection<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.roles = roles;
    }

    public User(String username, String password, String firstName, String lastName, String email, String phonenumber, Collection<Role> roles) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
        this.roles = roles;
    }

    public User(String username, String password, String firstName, String lastName, String email, String phonenumber) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
