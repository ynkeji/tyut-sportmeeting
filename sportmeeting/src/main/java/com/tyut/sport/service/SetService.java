package com.tyut.sport.service;

import com.tyut.sport.mapper.SignMapper;
import com.tyut.sport.pojo.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetService {

    @Autowired
    private SignMapper signMapper;
    public void set(List<Sign> signs){
        for (Sign sign : signs) {
            signMapper.updateSign(sign);
        }
    }
}
