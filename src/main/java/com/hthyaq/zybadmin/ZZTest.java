package com.hthyaq.zybadmin;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Editor;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.hthyaq.zybadmin.model.entity.CategoryOfDic;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.Charset.*;

public class ZZTest {
    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            int i1 = random.nextInt(50) + 1;
            list1.add(i1);
            ArrayList<Integer> list2 = getList2(list1);
            System.out.println(list2.size());
            for (int i2 = 0; i2 < list2.size(); i2++) {

                System.out.println(list2.get(i2));
            }
        }
    }

    public static ArrayList<Integer> getList2(ArrayList<Integer> list1) {
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i)%2 == 0) {
                list2.add(list1.get(i));
            }
        }
        return list2;
    }
}