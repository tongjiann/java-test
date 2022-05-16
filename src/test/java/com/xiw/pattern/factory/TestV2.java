package com.xiw.pattern.factory;

import com.xiw.pattern.factory.v2.RouJiaMoStore;
import com.xiw.pattern.factory.v2.SimpleRouJiaMoFactory;
import org.junit.Test;

/**
 * @author xiwang
 * @since 2022-05-13 16:53
 */
public class TestV2 {
    @Test
    public void test(){
        RouJiaMoStore rouJiaMoStore = new RouJiaMoStore();
        rouJiaMoStore.setFactory(new SimpleRouJiaMoFactory());
        rouJiaMoStore.sellRouJiaMo("Suan");
    }
}
