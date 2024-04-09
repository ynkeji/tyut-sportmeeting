package com.tyut.sport.service;

import com.tyut.sport.mapper.SignMapper;
import com.tyut.sport.pojo.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class signService {


    @Autowired
    private SignMapper signMapper;

    public Integer delSignByStu(Integer id) {
        return signMapper.delSignByStu(id);
    }


    public Integer delSignByPro(Integer id) {
        return signMapper.delSignByPro(id);
    }


    public Integer del(Integer uuid, Integer ppid) {
        return signMapper.del(uuid,ppid);
    }


    public Integer addItem(Sign sign) {
        return signMapper.addItem(sign);
    }


    public Sign selectByStu(Integer id) {
        return signMapper.selectByStu(id);
    }


    public List<Sign> selectByPro(Integer id) {
        return signMapper.selectByPro(id);
    }

    public List<Sign> selectByparam(String name,String type){return signMapper.selectByparam(name,type);}

    public Integer updateSign(Sign sign){return signMapper.updateSign(sign);}
    public Integer selectByAll(Integer uuid,Integer ppid){
        return signMapper.selectByAll(uuid,ppid);
    }

    public List<Sign> selectAll() {
        return signMapper.selectAll();
    }
}
