package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.Leave;
import com.service.LeaveService;
import com.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * create by tanwt
 * 2018/5/7 0007 18:23
 **/
@Controller
@RequestMapping("/leave")
public class LeaveControler {

    @Autowired
    private LeaveService leaveService;

    /**
     * 添加出勤记录
     * @param leave
     * @return
     * {"code":1,"data":null,"msg":"成功"}
     * {"code":0,"data":null,"msg":"未知异常"}
     */
    @RequestMapping("/addLeave")
    @ResponseBody
    public JSONObject addLeave(Leave leave){
        System.out.println("-------用户添加请假操作-------");
        System.out.println("添加的请假信息：" + leave);
        try {
            int result = leaveService.addLeave(leave);
            System.out.println("操作返回码:"+result);
            System.out.println("-------END-------");
            if (result == 1) {
                return CommonUtil.success(null);
            }
            return CommonUtil.error();
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 更新出勤记录
     * @param leave
     * @return
     * {"code":1,"data":null,"msg":"成功"}
     * {"code":0,"data":null,"msg":"操作失败"}
     */
    @RequestMapping("/updateLeave")
    @ResponseBody
    public JSONObject updateLeave(Leave leave){
        System.out.println("-------用户更新请假操作-------");
        System.out.println("请求更新的请假信息：" + leave);
        try {
            int result = leaveService.updateLeave(leave);
            System.out.println("操作返回码:"+result);
            System.out.println("-------END-------");
            if (result == 1)
                return CommonUtil.success(null);
            return CommonUtil.error();
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 删除出勤记录
     * @param id
     * @return
     * {"code":1,"data":null,"msg":"成功"}
     * {"code":0,"data":null,"msg":"操作失败"}
     */
    @RequestMapping("/deleteLeave")
    @ResponseBody
    public JSONObject updateLeave(@RequestParam(value = "id",required = false) Integer id){
        System.out.println("-------用户删除请假操作-------");
        System.out.println("需要删除的请假信息id: " + id);
        try {
            int result = leaveService.deleteLeave(id);
            System.out.println("操作返回码:"+result);
            System.out.println("-------END-------");
            if (result == 1)
                return CommonUtil.success(null);
            return CommonUtil.error();
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 得到所有出勤记录
     * @param
     * @return
     * {"code":1,"data":[{"auditorId":2,"date":"2018-4-16","end":"2018-6-01","id":1,"isExigency":1,"reason":"生病","rejection":"我拒绝","site":null,"start":"2018-5-01","status":2,"type":1,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-20","id":4,"isExigency":1,"reason":"生病","rejection":null,"site":null,"start":"2018-4-19","status":1,"type":0,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-20","id":5,"isExigency":1,"reason":"生病1","rejection":"不同意","site":null,"start":"2018-4-19","status":2,"type":0,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-23","id":6,"isExigency":0,"reason":"生病2","rejection":"...","site":null,"start":"2018-4-19","status":2,"type":0,"userId":4},{"auditorId":2,"date":"2018-4-16","end":"2018-6-01","id":10,"isExigency":1,"reason":"到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差","rejection":"会计师对比你傻不定期我可不","site":"上海","start":"2018-5-01","status":1,"type":2,"userId":4},{"auditorId":2,"date":"2018-05-02","end":"2018-05-03","id":11,"isExigency":0,"reason":"有事","rejection":"拒绝","site":null,"start":"2018-05-02","status":2,"type":0,"userId":2},{"auditorId":0,"date":"2018-05-05","end":"2018-08-08","id":12,"isExigency":1,"reason":"hahahahah","rejection":null,"site":null,"start":"2018-05-05","status":0,"type":0,"userId":7},{"auditorId":0,"date":"2018-05-05","end":"2018-05-11","id":13,"isExigency":0,"reason":"测试","rejection":null,"site":null,"start":"2018-05-05","status":0,"type":0,"userId":4},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":14,"isExigency":0,"reason":"回家","rejection":null,"site":null,"start":"2018-5-8","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":17,"isExigency":0,"reason":null,"rejection":null,"site":null,"start":"2018-5 80","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-30","id":18,"isExigency":0,"reason":"hhh","rejection":null,"site":null,"start":"2018-5 8","status":0,"type":0,"userId":2}],"msg":"成功"}
     */
    @RequestMapping("/getAllLeave")
    @ResponseBody
    public JSONObject getAllLeave(){
        try {
            List list = leaveService.getAllLeave();
            return CommonUtil.success(list);
        }catch (Exception e){
            return CommonUtil.error(e);
        }
    }

    /**
     * 获取某部门请假信息
     * @return
     * {"code":1,"data":[{"auditorId":2,"date":"2018-4-16","end":"2018-6-01","id":1,"isExigency":1,"reason":"生病","rejection":"我拒绝","site":null,"start":"2018-5-01","status":2,"type":1,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-20","id":4,"isExigency":1,"reason":"生病","rejection":null,"site":null,"start":"2018-4-19","status":1,"type":0,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-20","id":5,"isExigency":1,"reason":"生病1","rejection":"不同意","site":null,"start":"2018-4-19","status":2,"type":0,"userId":4},{"auditorId":2,"date":"2018-04-19","end":"2018-4-23","id":6,"isExigency":0,"reason":"生病2","rejection":"...","site":null,"start":"2018-4-19","status":2,"type":0,"userId":4},{"auditorId":2,"date":"2018-4-16","end":"2018-6-01","id":10,"isExigency":1,"reason":"到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差到上海出差","rejection":"会计师对比你傻不定期我可不","site":"上海","start":"2018-5-01","status":1,"type":2,"userId":4},{"auditorId":2,"date":"2018-05-02","end":"2018-05-03","id":11,"isExigency":0,"reason":"有事","rejection":"拒绝","site":null,"start":"2018-05-02","status":2,"type":0,"userId":2},{"auditorId":0,"date":"2018-05-05","end":"2018-08-08","id":12,"isExigency":1,"reason":"hahahahah","rejection":null,"site":null,"start":"2018-05-05","status":0,"type":0,"userId":7},{"auditorId":0,"date":"2018-05-05","end":"2018-05-11","id":13,"isExigency":0,"reason":"测试","rejection":null,"site":null,"start":"2018-05-05","status":0,"type":0,"userId":4},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":14,"isExigency":0,"reason":"回家","rejection":null,"site":null,"start":"2018-5-8","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":17,"isExigency":0,"reason":null,"rejection":null,"site":null,"start":"2018-5 80","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-30","id":18,"isExigency":0,"reason":"hhh","rejection":null,"site":null,"start":"2018-5 8","status":0,"type":0,"userId":2}],"msg":"成功"}
     */
    @RequestMapping("/getOtherLeaveById")
    @ResponseBody
    public JSONObject getOtherLeaveById(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------获取部门请假信息-------");
        System.out.println("请求的用户id："+userId);
        try {
            List list = leaveService.getOtherLeaveById(userId);
            System.out.println("操作返回信息:"+list);
            System.out.println("-------END-------");
            return CommonUtil.success(list);
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 获取某用户请假信息
     * @return
     * {"code":1,"data":[{"auditorId":2,"date":"2018-05-02","end":"2018-05-03","id":11,"isExigency":0,"reason":"有事","rejection":"拒绝","site":null,"start":"2018-05-02","status":2,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":14,"isExigency":0,"reason":"回家","rejection":null,"site":null,"start":"2018-5-8","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-9","id":17,"isExigency":0,"reason":null,"rejection":null,"site":null,"start":"2018-5 80","status":0,"type":0,"userId":2},{"auditorId":0,"date":"2018-5-8","end":"2018-5-30","id":18,"isExigency":0,"reason":"hhh","rejection":null,"site":null,"start":"2018-5 8","status":0,"type":0,"userId":2}],"msg":"成功"}
     */
    @RequestMapping("/getAllLeaveById")
    @ResponseBody
    public JSONObject getAllLeaveById(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------获取某用户请假信息-------");
        System.out.println("请求的用户id："+userId);
        try {
            List list = leaveService.getAllLeaveById(userId);
            System.out.println("操作返回信息:"+list);
            System.out.println("-------END-------");
            return CommonUtil.success(list);
        }catch (Exception e){
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }
}
