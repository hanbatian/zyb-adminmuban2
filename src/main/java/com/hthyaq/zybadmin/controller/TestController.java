package com.hthyaq.zybadmin.controller;


import com.hthyaq.zybadmin.model.entity.Test;
import com.hthyaq.zybadmin.service.TestService;
import io.lettuce.core.Value;
import io.lettuce.core.dynamic.annotation.Key;
import org.apache.commons.io.FileUtils;
import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping("/add")
    public void readFile() throws Exception {
//创建一个空list集合
        ArrayList<Test> list = new ArrayList<>();
//创建文件，找到文件，创建文件输入流，转换文件，创建文件读取流，将转换后的文件读取出来，创建缓存区，将读取后的文件放入缓冲区中
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("201906area.txt")), "UTF-8"));
//hutool工具类，读取文件
        BufferedReader br = new BufferedReader(new FileReader("201906area.txt"));
//        创建空字符串池
        String line = "";
//        从缓存区中循环读取每一条数据字符串，当读取的数据为空时，将不再读取
        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//            将读取的每一条数据字符串以空格进行分割，分割后成为2个字符串数组
            String[] ss = line.split("\\s+");
//           创建一个空对象，用来存储每一条数据
            Test test = new Test();
//            分别通过数组角标索引将数组中的字符串设置进上面创建好的空对象中
            test.setAllnub(ss[0]);
            test.setName(ss[1]);
//            最后将有值的空对象加入上面创建的空集合中
            list.add(test);
        }
        for (Test s : list) {
            System.out.println(s.getAllnub() + "........" + s.getName());
        }
//将有对象的集合保存到数据库中，形成一条数据
        testService.saveBatch(list);
    }
//        int i = list.size();
//        System.out.println(i);
}


