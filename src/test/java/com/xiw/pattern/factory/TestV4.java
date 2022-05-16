package com.xiw.pattern.factory;

import com.xiw.pattern.factory.v4.store.RouJiaMoStore;
import com.xiw.pattern.factory.v4.store.XianRouJiaMoStore;
import com.xiw.pattern.factory.v4.store.ZhejiangRouJiaMoStore;
import org.junit.Test;

/**
 * @author xiwang
 * @since 2022-05-13 16:53
 */
public class TestV4 {
    @Test
    public void test(){
        RouJiaMoStore rouJiaMoStore = new XianRouJiaMoStore();
        rouJiaMoStore.sellRouJiaMo("Suan");
        System.out.println("........................");
        RouJiaMoStore zhejiangRouJiaMoStore = new ZhejiangRouJiaMoStore();
        zhejiangRouJiaMoStore.sellRouJiaMo("Tian");
    }
}
