package com.project.credit.admin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Admin {
    @Id
    @GeneratedValue
    private Long adminId;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String phone;
    private String address;

    public Admin(String name, String email, String password, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public Admin() {

    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getName() {
        return name;
    }

    public void setName(String adminName) {
        this.name = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String adminEmail) {
        this.email = adminEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String adminPassword) {
        this.password = adminPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String adminPhone) {
        this.phone = adminPhone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adminAddress) {
        this.address = adminAddress;
    }
}
