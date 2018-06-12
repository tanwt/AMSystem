package com.controller;

import com.alibaba.fastjson.JSONObject;
import com.bean.ClockCard;
import com.dao.ClockCardDao;
import com.mapper.ClockCardMapper;
import com.service.ClockCardService;
import com.utils.CheckNull;
import com.utils.CommonUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * create by tanwt
 * 2018/5/8 0008 18:06
 **/
@Controller
@RequestMapping("/clockCard")
public class ClockCardController {

    @Autowired
    private ClockCardService clockCardService;

    /**
     * 今日打卡
     * @param userId
     * @return
     *{"code":0,"data":null,"msg":"未传入值"}
     * {"code":1,"data":null,"msg":"成功"}
     * {"code":0,"data":null,"msg":"今日已打卡"}
     */
    @RequestMapping("/startWork")
    @ResponseBody
    public JSONObject startWork(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------今日打卡-------");
        System.out.println("请求的用户id："+userId);
        try {
            Integer result = -1;
            result = clockCardService.startWork(userId);
            System.out.println("操作返回信息:"+result);
            System.out.println("-------END-------");
            if (result == 1)
                return CommonUtil.success(null);
            else
                return CommonUtil.error("今日已打卡");
        } catch (NullPointerException e) {
            System.out.println("-------END-------");
            return CommonUtil.error("未传入值");
        } catch (Exception e) {
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 下班打卡
     * @param userId
     * @return
     * {"code":0,"data":null,"msg":"今日已打卡"}
     * {"code":1,"data":null,"msg":"成功"}
     *{"code":0,"data":null,"msg":"未传入值"}
     */
    @RequestMapping("/endWork")
    @ResponseBody
    public JSONObject endWork(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------下班打卡-------");
        System.out.println("请求的用户id："+userId);
        try {
            Integer result = -1;
            result = clockCardService.endWork(userId);
            System.out.println("操作返回信息:"+result);
            System.out.println("-------END-------");
            if (result == 1)
                return CommonUtil.success(null);
            else
                return CommonUtil.error("今日已打卡");
        } catch (NullPointerException e) {
            System.out.println("-------END-------");
            return CommonUtil.error("未传入值");
        } catch (Exception e) {
            System.out.println("-------END-------");
            e.printStackTrace();
            return CommonUtil.error(e);
        }
    }

    /**
     * 获取所有打卡信息
     * @return
     * {"code":1,"data":[{"date":"2018-05-08","endTime":"19:08:10","id":18,"startTime":"18:56:57","status":3,"userId":5},{"date":"2018-05-08","endTime":null,"id":16,"startTime":"18:14:32","status":1,"userId":11},{"date":"2018-05-08","endTime":"17:18:09","id":15,"startTime":"17:16:21","status":3,"userId":2},{"date":"2018-05-08","endTime":null,"id":14,"startTime":"17:14:47","status":1,"userId":4},{"date":"2018-05-05","endTime":"11:06:47","id":13,"startTime":"11:06:42","status":3,"userId":7},{"date":"2018-05-02","endTime":"16:16:16","id":12,"startTime":"16:16:10","status":3,"userId":2},{"date":"2018-04-30","endTime":"20:14:18","id":11,"startTime":"20:14:16","status":3,"userId":4},{"date":"2018-04-19","endTime":"22:29:14","id":10,"startTime":"22:29:05","status":1,"userId":4},{"date":"2018-04-18","endTime":"22:17:53","id":7,"startTime":"22:17:47","status":1,"userId":4},{"date":"2018-04-16","endTime":"13:17:56","id":6,"startTime":"13:14:03","status":3,"userId":4},{"date":"2018-04-15","endTime":"20:17:55","id":2,"startTime":"16:41:47","status":3,"userId":4}],"msg":"成功"}
     */
    @RequestMapping("/getAllClockInformation")
    @ResponseBody
    public JSONObject getAllClockInformation(){
        System.out.println("-------获取所有打卡信息-------");
        try {
            List list = clockCardService.getAllClockInformation();
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
     * 获取某部门打卡信息
     * @return
     * {"code":1,"data":[{"date":"2018-05-08","endTime":"19:08:10","id":18,"startTime":"18:56:57","status":3,"userId":5},{"date":"2018-05-08","endTime":"17:18:09","id":15,"startTime":"17:16:21","status":3,"userId":2},{"date":"2018-05-08","endTime":null,"id":14,"startTime":"17:14:47","status":1,"userId":4},{"date":"2018-05-05","endTime":"11:06:47","id":13,"startTime":"11:06:42","status":3,"userId":7},{"date":"2018-05-02","endTime":"16:16:16","id":12,"startTime":"16:16:10","status":3,"userId":2},{"date":"2018-04-30","endTime":"20:14:18","id":11,"startTime":"20:14:16","status":3,"userId":4},{"date":"2018-04-19","endTime":"22:29:14","id":10,"startTime":"22:29:05","status":1,"userId":4},{"date":"2018-04-18","endTime":"22:17:53","id":7,"startTime":"22:17:47","status":1,"userId":4},{"date":"2018-04-16","endTime":"13:17:56","id":6,"startTime":"13:14:03","status":3,"userId":4},{"date":"2018-04-15","endTime":"20:17:55","id":2,"startTime":"16:41:47","status":3,"userId":4}],"msg":"成功"}
     */
    @RequestMapping("/getOtherClockById")
    @ResponseBody
    public JSONObject getOtherClockById(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------获取部门打卡信息-------");
        System.out.println("请求的用户id："+userId);
        try {
            List list = clockCardService.getOtherClockById(userId);
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
     * 获取某用户打卡信息
     * @return
     *{"code":1,"data":[{"date":"2018-05-08","endTime":"17:18:09","id":15,"startTime":"17:16:21","status":3,"userId":2},{"date":"2018-05-02","endTime":"16:16:16","id":12,"startTime":"16:16:10","status":3,"userId":2}],"msg":"成功"}
     */
    @RequestMapping("/getAllClockById")
    @ResponseBody
    public JSONObject getAllClockById(@RequestParam(value = "userId",required = false) Integer userId){
        System.out.println("-------获取用户打卡信息-------");
        System.out.println("请求的用户id："+userId);
        try {
            List list = clockCardService.getAllClockById(userId);
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
