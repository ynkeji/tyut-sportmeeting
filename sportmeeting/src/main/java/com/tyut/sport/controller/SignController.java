package com.tyut.sport.controller;

import com.tyut.sport.config.TokenUtil;
import com.tyut.sport.entity.DataResult;
import com.tyut.sport.pojo.Sign;
import com.tyut.sport.pojo.grade;
import com.tyut.sport.pojo.login;
import com.tyut.sport.pojo.sporter;
import com.tyut.sport.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.tyut.sport.config.group.groupAndAssign;

@RestController
public class SignController {

    @Autowired
    private signService signService;
    @Autowired
    private TokenService TokenService;

    @Autowired
    private sporterService sporterService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private SetService service;


    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/setByOne",method = RequestMethod.GET)
    public DataResult setByOne(@RequestHeader Map<String, String> headers,
                               @RequestParam(value = "ppid") Integer ppid){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            List<Sign> signs =  signService.selectByPro(ppid);
            signs = groupAndAssign(signs);
            service.set(signs);
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData("编排成功");
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;

    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/addSign",method = RequestMethod.POST)
    public DataResult sign(@RequestBody Sign sign,@RequestHeader Map<String, String> headers)
    {
        DataResult dataResult = new DataResult();
        if (check(headers)){
           // System.out.println(signService.selectByAll(sign.getSporterId(),sign.getProjectId()));
            Integer id = projectService.GetOneByName(sign.getProjectName()).getId();
            String type = projectService.GetOneByName(sign.getProjectName()).getType();
            System.out.println(id);
            System.out.println(type);
            sign.setProjectId(id);
            sign.setProjecttype(type);
            if (signService.selectByAll(sign.getSporterId(),sign.getProjectId()) >= 1){
                dataResult.setStatus(-1);
                dataResult.setMsg("error");
                dataResult.setData("重复报名，请检查！");
                return dataResult;
            }

            String colloge = sporterService.findSporter(sign.getSporterId()).getCollege();
            sign.setColloge(colloge);
            signService.addItem(sign);
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData("添加成功！");

        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/getSignAll",method = RequestMethod.GET)
    public DataResult getSignAll(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(signService.selectAll());
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/del",method = RequestMethod.GET)
    public DataResult del(@RequestHeader Map<String, String> headers,
                          @RequestParam(value = "uuid") Integer uuid,
                          @RequestParam(value = "ppid") Integer ppid)
    {
        DataResult dataResult = new DataResult();
        if (check(headers)){
            if (uuid == null || ppid == null){
                dataResult.setStatus(-1);
                dataResult.setMsg("error");
                dataResult.setData("参数错误");
                return dataResult;
            }

            System.out.println(uuid);
            System.out.println(ppid);
            System.out.println(signService.del(uuid,ppid));
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData("退赛成功！");
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/selectByparam",method = RequestMethod.GET)
    public DataResult selectByparam(@RequestHeader Map<String, String> headers,
                                    @RequestParam(value = "name") String  name,
                                    @RequestParam(value = "type") String type){
        DataResult dataResult = new DataResult();

        if (name == null && type == null){
            dataResult.setStatus(-1);
            dataResult.setMsg("error");
            dataResult.setData("参数出错！");
            return dataResult;
        }
        if (check(headers)){
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(signService.selectByparam(name,type));
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;

    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/setGrade",method = RequestMethod.POST)
    public DataResult setGrade(@RequestBody grade grade, @RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        if(check(headers)){
            Sign sign = new Sign();
            sign.setGrade(grade.getStu_grade());
            sign.setScore(grade.getPro_grade());
            sign.setSporterId(grade.getStu_id());
            sign.setProjectId(grade.getPro_id());
            signService.updateSign(sign);
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData("录入成功！");
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/SignUpList",method = RequestMethod.GET)
    public DataResult SignUpList(@RequestHeader Map<String, String> headers,
                                 @RequestParam(value = "ppid") Integer ppid){

        DataResult dataResult = new DataResult();
        if (check(headers)){
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(signService.selectByPro(ppid));
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
        }
        return dataResult;
    }

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/score",method = RequestMethod.GET)
    public DataResult score(@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(signService.selectAll());
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登陆！");
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
