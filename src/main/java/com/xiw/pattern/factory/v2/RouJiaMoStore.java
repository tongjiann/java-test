package com.xiw.pattern.factory.v2;

import com.xiw.pattern.factory.v2.bean.*;

/**
 * @author xiwang
 * @since 2022-05-13 16:42
 */
public class RouJiaMoStore {

    private SimpleRouJiaMoFactory factory;
    /**
     * 根据传入类型卖不同的肉夹馍
     *
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = factory.createRouJiaMo(type);
        assert rouJiaMo != null;
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }

    public SimpleRouJiaMoFactory getFactory() {
        return factory;
    }

    public void setFactory(SimpleRouJiaMoFactory factory) {
        this.factory = factory;
    }
}
