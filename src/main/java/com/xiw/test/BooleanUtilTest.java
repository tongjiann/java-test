package com.xiw.test;

import org.apache.commons.lang3.BooleanUtils;
import org.junit.Test;

/**
 * @author xiwang
 * @date 2022-03-21 17:16
 */
public class BooleanUtilTest {
    @Test
    public void testBooleanUtil(){
        System.out.println(BooleanUtils.toBoolean((String) null));
    }
}
