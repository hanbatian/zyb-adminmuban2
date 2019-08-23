package com.hthyaq.zybadmin.controller;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.hthyaq.zybadmin.model.entity.Msg;
import com.hthyaq.zybadmin.model.entity.Test;
import com.hthyaq.zybadmin.service.MsgService;
import com.hthyaq.zybadmin.service.TestService;
import org.apache.commons.collections4.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-13
 */
@RestController
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    MsgService msgService;
    @Autowired
    TestService testService;

    @GetMapping("/get")
    public List<Msg> get() {
//        定义一个常量
        int code = 10000;
//        将该常量放入下面网址中
        String s = HttpUtil.get("https://restapi.amap.com/v3/config/district?keywords=" + code + "&subdistrict=0&key=eadd710d0a0898b211ab62cee2e21b80");
//        将json数据转换成json对象
        JSONObject jo = JSONObject.parseObject(s);
//        通过json对象获取districts的数组
        JSONArray ja = jo.getJSONArray("districts");
//        for (int i=0;i<ja.size();i++){
//            JSONObject jsonObject = ja.getJSONObject(i);
//        }
//        获取数组中0角标元素的json对象
        JSONObject jt = ja.getJSONObject(0);
//        将json对象转换成json字符串，再将json转换成Javabean对象
        Msg msg1 = jt.toJavaObject(Msg.class);
//        Msg msg = JSONObject.parseObject(String.valueOf(jt), Msg.class);
        msgService.save(msg1);
        return null;
    }

    @GetMapping("/get1")
    public List<Msg> get1() {
        List<Test> list1 = testService.list();
//       List<Test> list = new ArrayList<>();
//        Test test1 = new Test();
//        test1.setAllnub("110106");
//        list.add(test1);
//        Test test3 = new Test();
//        test3.setAllnub("110107");
//        list.add(test3);
        List<Msg> list2 = new ArrayList<>();
        for (Test test : list1) {
            String allnub = test.getAllnub();
            int code = Integer.parseInt(allnub);
//        将该常量放入下面网址中
            String s = HttpUtil.get("https://restapi.amap.com/v3/config/district?keywords=" + code + "&subdistrict=0&key=eadd710d0a0898b211ab62cee2e21b80");
//        将json数据转换成对象
            JSONObject jo = JSONObject.parseObject(s);
//        通过json对象获取数组的districts
            JSONArray ja = jo.getJSONArray("districts");
//        获取数组中角标索引的json对象
            JSONObject jt = ja.getJSONObject(0);
//        将json对象转换成java对象
            Msg msg = JSONObject.parseObject(String.valueOf(jt), Msg.class);
            list2.add(msg);
            System.out.println("json字符串转简单java对象:"+msg);
        }
        msgService.saveBatch(list2);
        return null;
    }

}
