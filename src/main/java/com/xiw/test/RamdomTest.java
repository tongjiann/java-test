package com.xiw.test;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

/**
 * @author xiwang
 * @date 2022-03-21 11:39
 */
public class RamdomTest {
    @Test
    public void testRandom(){
        for (int i = 0; i < 1000; i++) {
            if(10==RandomUtil.randomInt(0,10)){
                System.out.println(1);
            }
        }
    }
}
