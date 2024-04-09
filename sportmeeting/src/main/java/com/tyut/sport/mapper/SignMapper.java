package com.tyut.sport.mapper;

import com.tyut.sport.pojo.Sign;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SignMapper {

    //根据学生学号删除
    public Integer delSignByStu(Integer id);

    //根据项目ID删除
    public Integer delSignByPro(Integer id);

    //退赛
    public Integer del(Integer uuid,Integer ppid);

    //添加项
    public Integer addItem(Sign sign);

    public Sign selectByStu(Integer id);
    public Integer updateSign(Sign sign);
    public List<Sign> selectByPro(Integer id);
    public List<Sign> selectByparam(String name,String type);
    public List<Sign> selectAll();

    public Integer selectByAll(Integer uuid,Integer ppid);
}
