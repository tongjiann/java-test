package com.xiw.mode.factory;

import com.xiw.mode.factory.v3.RouJiaMoStore;
import com.xiw.mode.factory.v3.XianRouJiaMoStore;
import org.junit.Test;

/**
 * @author xiwang
 * @since 2022-05-13 16:53
 */
public class TestV3 {
    @Test
    public void test(){
        RouJiaMoStore rouJiaMoStore = new XianRouJiaMoStore();
        rouJiaMoStore.sellRouJiaMo("Suan");
    }
}
