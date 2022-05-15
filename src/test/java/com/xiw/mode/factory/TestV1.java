package com.xiw.mode.factory;

import com.xiw.mode.factory.v1.RouJiaMoStore;
import org.junit.Test;

/**
 * @author xiwang
 * @since 2022-05-13 16:53
 */
public class TestV1 {
    @Test
    public void test(){
        RouJiaMoStore rouJiaMoStore = new RouJiaMoStore();
        rouJiaMoStore.sellRouJiaMo("Suan");
    }
}
