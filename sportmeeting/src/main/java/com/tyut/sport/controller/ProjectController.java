package com.tyut.sport.controller;


import com.tyut.sport.config.TokenUtil;
import com.tyut.sport.entity.DataResult;
import com.tyut.sport.pojo.Project;
import com.tyut.sport.service.ProjectService;
import com.tyut.sport.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class ProjectController {
    @Autowired
    private TokenService TokenService;

    @Autowired
    private ProjectService projectService;



    //获取所有项目
    @RequestMapping(value = "/getAllproject",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult SelectAll(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
          if (check(headers)){
              dataResult.setStatus(1);
              dataResult.setMsg("success");
              dataResult.setData(projectService.SelectAll());
          }
          else {
              dataResult.setStatus(-2);
              dataResult.setMsg("error");
              dataResult.setData("登录失效，请重新登录！");
          }
          return dataResult;
    }
    @RequestMapping(value = "/addProject", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public DataResult addProject(@RequestBody Project project, @RequestHeader Map<String, String> headers) {
        DataResult dataResult = new DataResult();
        if (check(headers)) {
            projectService.Insert(project);
            dataResult.setStatus(1);
            dataResult.setMsg("Project added successfully");
        } else {
            dataResult.setStatus(-2);
            dataResult.setMsg("Login expired, please log in again");
        }
        return dataResult;
    }

    @RequestMapping(value = "/deleteProject", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult deleteProject(@RequestParam int id, @RequestHeader Map<String, String> headers) {
        DataResult dataResult = new DataResult();
        if (check(headers)) {
            projectService.Delete(id);
            dataResult.setStatus(1);
            dataResult.setMsg("Project deleted successfully");
        } else {
            dataResult.setStatus(-2);
            dataResult.setMsg("Login expired, please log in again");
        }
        return dataResult;
    }

    @RequestMapping(value = "/updateProject", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin
    public DataResult updateProject(@RequestBody Project project, @RequestHeader Map<String, String> headers) {
        DataResult dataResult = new DataResult();
        if (check(headers)) {
            projectService.updateProject(project);
            dataResult.setStatus(1);
            dataResult.setMsg("Project updated successfully");
        } else {
            dataResult.setStatus(-2);
            dataResult.setMsg("Login expired, please log in again");
        }
        return dataResult;
    }
    @RequestMapping(value = "/GetProjectNameAll", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public  DataResult GetProjectNameAll(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers))
        {
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(projectService.GetProjectNameAll());
        }else{
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登录！");
        }
        return dataResult;
    }



    @RequestMapping(value = "/GetProjectTypeAll", method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public  DataResult GetProjectTypeAll(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers))
        {
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(projectService.GetProjectTypeAll());
        }else{
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登录！");
        }
        return dataResult;
    }

    @RequestMapping(value = "/getByparam",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult getByparam(@RequestHeader Map<String, String> headers,
                                @RequestParam(value = "type", required = false) String type,
                                @RequestParam(value = "name", required = false) String name){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            if ((type == null || type.isEmpty()) && (name == null || name.isEmpty())) {
                dataResult.setStatus(-3);
                dataResult.setMsg("error");
                dataResult.setData("请求参数不全，type和name不能同时为空！");
            } else {
                dataResult.setStatus(1);
                dataResult.setMsg("success");
                dataResult.setData(projectService.getByparam(type, name));
            }
        }
        else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登录！");
        }
        return dataResult;
    }

    private Boolean check(Map<String,String> headers){
        String token = headers.get("token");
        if (token == null) return false;
        if(TokenUtil.verify(token)) {
            if (TokenService.SelectToken(token)== null) {
                System.out.println("身份验证失败");
                return false;
            } else {
                System.out.println("身份验证成功");
                return true;
            }
        }
        return false;
    }

}
