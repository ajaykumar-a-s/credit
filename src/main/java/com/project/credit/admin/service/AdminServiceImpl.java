package com.project.credit.admin.service;

import com.project.credit.LoginDto;
import com.project.credit.admin.entity.Admin;
import com.project.credit.admin.exception.AdminException;
import com.project.credit.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public Admin loginAdmin(LoginDto loginDto) throws AdminException {
        if (loginDto.getEmail() == null || loginDto.getEmail().isEmpty()){
            throw new AdminException("Email cannot be null");
        }
        if(loginDto.getPassword() == null || loginDto.getPassword().isEmpty()){
            throw new AdminException("Password cannot be null");
        }
        if(!loginDto.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            throw new AdminException("Invalid email format");
        }
        if (!loginDto.getPassword().matches(".*\\d.*") || loginDto.getPassword().length() < 8 || loginDto.getPassword().equals(loginDto.getPassword().toLowerCase()) || loginDto.getPassword().equals(loginDto.getPassword().toUpperCase()) || loginDto.getPassword().matches("[a-zA-Z0-9 ]*")){
            throw new AdminException("Invalid password format");
        }
        Admin admin = adminRepository.findByEmail(loginDto.getEmail());
        if(admin == null){
            throw new AdminException("Admin with email " + loginDto.getEmail() + " not found");
        }
        if (!admin.getPassword().equals(loginDto.getPassword())){
            throw new AdminException("Invalid password");
        }
        return admin;
    }
}
