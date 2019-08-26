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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.Charset.*;

public class ZZTest {
    public static void main(String[] args) {
        int[] arrary = {323, 43, 433, 2, 5, 32, 45};
        int max = getmax(arrary);
        System.out.println(max);
    }

    public static int getmax(int[] arrary) {
        int max = arrary[0];
        for (int i = 0; i < arrary.length; i++) {
            if (arrary[i] > max) {
                max = arrary[i];
            }
        }
        return max;
    }
}