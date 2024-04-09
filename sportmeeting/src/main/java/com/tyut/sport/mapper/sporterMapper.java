package com.tyut.sport.mapper;

import com.tyut.sport.pojo.sporter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface sporterMapper {
    public int addSporter(sporter sporter);

    public int modifySporter(sporter sporter);

    public int delSporter(Integer id);

    public List<sporter> getAllSporter();

    public sporter findSporter(Integer id);
}
