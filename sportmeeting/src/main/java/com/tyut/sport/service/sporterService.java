package com.tyut.sport.service;

import com.tyut.sport.mapper.sporterMapper;
import com.tyut.sport.pojo.sporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class sporterService{
    @Autowired
    private sporterMapper sporterMapper;
    //添加运动员
    public int addSporter(sporter sporter) {
        return sporterMapper.addSporter(sporter);
    }
    //运动员信息修改
    public int modifySporter(sporter sporter) {
        return sporterMapper.modifySporter(sporter);
    }
    //删除运动员信息
    public int delSporter(Integer id) {return sporterMapper.delSporter(id);}

    public List<sporter> getAllSporter(){return sporterMapper.getAllSporter();}

    //寻找运动员
    public sporter findSporter(Integer id){return sporterMapper.findSporter(id);}
}
