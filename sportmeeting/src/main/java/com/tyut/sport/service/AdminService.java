package com.tyut.sport.service;

import com.tyut.sport.entity.DataResult;
import com.tyut.sport.mapper.AdminMapper;
import com.tyut.sport.pojo.admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Service
public class AdminService {

    @Autowired
    private AdminMapper AdminMapper;

    public admin QueryAdmin(String username, String password) {
            return AdminMapper.QueryAdmin(username, password);
    }

    public admin QueryAdminInfor(String username){return AdminMapper.QueryAdminInfor(username);}
}
