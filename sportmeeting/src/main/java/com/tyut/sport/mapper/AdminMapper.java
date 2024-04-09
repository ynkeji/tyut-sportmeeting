package com.tyut.sport.mapper;

import com.tyut.sport.entity.DataResult;
import com.tyut.sport.pojo.admin;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

@Mapper
public interface AdminMapper {

    admin QueryAdmin(String username, String password);

    admin QueryAdminInfor(String username);

}
