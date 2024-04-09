package com.tyut.sport.mapper;

import com.tyut.sport.pojo.token;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenMapper {
    public Integer SetToken(String token);

    public Integer DelToken(String token);

    public token SelectToken(String tokoen);

}
