package com.project.credit.admin.controller;

import com.project.credit.LoginDto;
import com.project.credit.admin.entity.Admin;
import com.project.credit.admin.exception.AdminException;
import com.project.credit.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/admin-login")
    public Admin loginAdmin(@RequestBody LoginDto loginDto) throws AdminException {
        return adminService.loginAdmin(loginDto);
    }
}
