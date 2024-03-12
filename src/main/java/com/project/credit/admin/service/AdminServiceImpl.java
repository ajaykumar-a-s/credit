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
        Admin admin = adminRepository.findByEmail(loginDto.getEmail());
        if(admin == null){
            throw new AdminException("Admin not found");
        }
        if (!admin.getPassword().equals(loginDto.getPassword())){
            throw new AdminException("Invalid password");
        }
        return admin;
    }
}
