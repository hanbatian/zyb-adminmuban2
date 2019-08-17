package com.hthyaq.zybadmin.controller;


import com.hthyaq.zybadmin.model.bean.GlobalResult;
import com.hthyaq.zybadmin.model.entity.Diqu;
import com.hthyaq.zybadmin.service.DiquService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.Document;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hk
 * @since 2019-08-12
 */
@RestController
@RequestMapping("/diqu")
public class DiquController {
    @Autowired
    DiquService diquService;

//    @GetMapping("/cx")
//    public Diqu cx(){
//        Document document = null;
//        ArrayList<Object> list = new ArrayList<>();
//        Document doc = Jsoup.connect("http://www.mca.gov.cn/article/sj/xzqh/2019/201901-06/201908050812.html\t\n");

//    }

}
