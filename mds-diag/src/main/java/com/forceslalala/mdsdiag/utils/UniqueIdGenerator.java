package com.forceslalala.mdsdiag.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName UniqueIdGenerator
 * @Author: wangtao
 * @Date: 2024/3/11 19:18
 * @Describe: 生成唯一标识id
 */
public class UniqueIdGenerator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final AtomicLong counter = new AtomicLong(0);

    public static synchronized String generate() {
        long count = counter.incrementAndGet();
        return dateFormat.format(new Date()) + String.format("%04d", count);
    }

}
