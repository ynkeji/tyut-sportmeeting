package com.tyut.sport.mapper;


import com.tyut.sport.pojo.Project;
import com.tyut.sport.pojo.ProjectName;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    public List<Project> SelectAll();

    public Integer Insert(Project project);

    public Integer modify(Project project);

    public  Project GetOne(Integer ID);

    public  Project GetOneByName(String name);
    public  List<Project> getByparam(String type,String name);

    public Integer Delete(Integer ID);

    public Integer updateProject(Project project);

    public List<ProjectName> GetProjectNameAll();

    public List<ProjectName> GetProjectTypeAll();

}
