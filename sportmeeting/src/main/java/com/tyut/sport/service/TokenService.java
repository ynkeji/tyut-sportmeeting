package com.tyut.sport.service;

import com.tyut.sport.mapper.TokenMapper;
import com.tyut.sport.pojo.token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements TokenMapper {

    @Autowired
    private TokenMapper TokenMapper;
    @Override
    public Integer SetToken(String token) {
        return TokenMapper.SetToken(token);
    }

    @Override
    public Integer DelToken(String token) {
        return TokenMapper.DelToken(token);
    }

    @Override
    public token SelectToken(String token) {
        return TokenMapper.SelectToken(token);
    }
}
