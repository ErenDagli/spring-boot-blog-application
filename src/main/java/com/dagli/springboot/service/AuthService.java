package com.dagli.springboot.service;

import com.dagli.springboot.dto.LoginDto;
import com.dagli.springboot.dto.RegisterDto;

public interface AuthService {
    String login (LoginDto loginDto);

    String register(RegisterDto registerDto);
}
