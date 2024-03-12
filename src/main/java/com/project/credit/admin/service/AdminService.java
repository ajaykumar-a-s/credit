package com.project.credit.admin.service;

import com.project.credit.LoginDto;
import com.project.credit.admin.entity.Admin;
import com.project.credit.admin.exception.AdminException;

public interface AdminService {
    Admin loginAdmin(LoginDto loginDto) throws AdminException;
}
