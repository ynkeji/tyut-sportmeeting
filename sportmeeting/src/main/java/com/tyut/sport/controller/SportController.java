package com.tyut.sport.controller;

import com.tyut.sport.config.TokenUtil;
import com.tyut.sport.entity.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tyut.sport.pojo.sporter;
import com.tyut.sport.service.TokenService;
import java.util.Map;
import com.tyut.sport.service.sporterService;
@RestController
public class SportController {

    @Autowired
    private sporterService sporterService;
    @Autowired
    private TokenService TokenService;

    @ResponseBody
    @CrossOrigin
    @RequestMapping(value = "/addSporter",method = RequestMethod.POST)
    public DataResult addPlayer(@RequestBody sporter sporter,@RequestHeader Map<String, String> headers){
        DataResult dataResult = new DataResult();
        String token = headers.get("token");
        if (check(headers)){
            if (!sporter.check()){
                dataResult.setStatus(-1);
                dataResult.setData("添加失败，您输入的内容不完整");
                dataResult.setMsg("error");
                return dataResult;
            }
            if (sporterService.findSporter(sporter.getId()) == null){
                sporterService.addSporter(sporter);
                dataResult.setStatus(1);
                dataResult.setMsg("success");
                dataResult.setData("添加成功！");
                return dataResult;
            }
            else {
                dataResult.setStatus(0);
                dataResult.setMsg("error");
                dataResult.setData("添加失败，已存在对应的学号！！");
                return dataResult;
            }
        }
        dataResult.setStatus(-2);
        dataResult.setMsg("error");
        dataResult.setData("登录过期，请重新登录");
        return dataResult;
    }

    @RequestMapping(value = "/delsporter",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult delPlayer(@RequestParam("id") Integer id,@RequestHeader Map<String, String> headers) throws SecurityException{
        DataResult dataResult = new DataResult();
        Integer ID;
        try {
//            ID =Integer.getInteger(id);
            ID = id;
            System.out.println(ID);
        }catch (SecurityException e){
            dataResult.setStatus(-1);
            dataResult.setMsg("error");
            dataResult.setData("输入信息有误，请检查！");
            return dataResult;
        }
        if (check(headers)){
            if (sporterService.findSporter(ID) == null){
                dataResult.setStatus(-1);
                dataResult.setMsg("error");
                dataResult.setData("未找到该运动员信息");
                return dataResult;
            }else {
                sporterService.delSporter(ID);
                dataResult.setStatus(1);
                dataResult.setMsg("success");
                dataResult.setData("删除成功");
                return dataResult;
            }
        }else{
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录身份失效，请重新登录");
            return dataResult;
        }
    }

    @RequestMapping(value = "/modifysporter",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult modifySporter(@RequestBody sporter sporter,@RequestHeader Map<String,String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            if (sporter.check()){
                if (sporterService.findSporter(sporter.getId()) == null){
                    dataResult.setStatus(-1);
                    dataResult.setMsg("error");
                    dataResult.setData("未找到相关运动员");
                    return dataResult;
                }else {
                    sporterService.modifySporter(sporter);
                    dataResult.setStatus(1);
                    dataResult.setMsg("success");
                    dataResult.setData("修改成功");
                    return dataResult;
                }
            }else {
                dataResult.setStatus(-1);
                dataResult.setMsg("error");
                dataResult.setData("你输入的信息有误，请检查后再进行提交！");
                return dataResult;
            }
        }
        else{
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录信息失效，请重新登录！");
            return dataResult;
        }
    }

    @RequestMapping(value = "/getAllSporter",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult getAllSporter(@RequestHeader  Map<String,String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            dataResult.setStatus(1);
            dataResult.setMsg("success");
            dataResult.setData(sporterService.getAllSporter());
        }else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录过期，请重新登录");
        }
        return dataResult;
    }

    @RequestMapping(value = "/SelectById",method = RequestMethod.GET)
    @ResponseBody
    @CrossOrigin
    public DataResult SelectById(@RequestParam("id") Integer id,@RequestHeader Map<String,String> headers){
        DataResult dataResult = new DataResult();
        if (check(headers)){
            sporter sporter = sporterService.findSporter(id);
            if (sporter != null){
                dataResult.setStatus(1);
                dataResult.setMsg("success");
                dataResult.setData(sporter);
            }else{
                dataResult.setStatus(-1);
                dataResult.setMsg("error");
                dataResult.setData("未找到该运动员，请核对学生学号");
            }
        }
        else {
            dataResult.setStatus(-2);
            dataResult.setMsg("error");
            dataResult.setData("登录失效，请重新登录");
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
