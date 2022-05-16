package com.xiw.pattern.factory;

import com.xiw.pattern.factory.v3.RouJiaMoStore;
import com.xiw.pattern.factory.v3.XianRouJiaMoStore;
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
