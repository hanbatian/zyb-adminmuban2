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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.Charset.*;

public class ZZTest {
    public static void main(String[] args)  {
        String result1 = StrFormatter.format("this is {} for {}", "a", "b");
       Console.log(result1);
    }
}
