package com.hthyaq.zybadmin;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.Charset.*;

public class ZZTest {
    public static void main(String[] args) {
        double a = 67556.32;

//结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(a);
        System.out.println(digitUppercase);
    }
}
