package com.tyut.sport.service;

import com.tyut.sport.mapper.ProjectMapper;
import com.tyut.sport.pojo.Project;
import com.tyut.sport.pojo.ProjectName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> SelectAll() {return projectMapper.SelectAll();}
    public List<ProjectName> GetProjectNameAll() {return projectMapper.GetProjectNameAll();}

    public List<ProjectName> GetProjectTypeAll() {return projectMapper.GetProjectTypeAll();}

    public Integer Insert(Project project) {return projectMapper.Insert(project);}


    public  Project GetOne(Integer ID){return projectMapper.GetOne(ID);}
    public  List<Project> getByparam(String type,String name) {return projectMapper.getByparam(type,name);}

    public  Project GetOneByName(String name){
        return projectMapper.GetOneByName(name);
    }
    public Integer Delete(Integer ID){return projectMapper.Delete(ID);}

    public Integer updateProject(Project project){return projectMapper.updateProject(project);}
}
