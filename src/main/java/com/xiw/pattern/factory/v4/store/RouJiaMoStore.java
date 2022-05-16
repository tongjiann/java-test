package com.xiw.pattern.factory.v4.store;


import com.xiw.pattern.factory.v4.bean.RouJiaMo;
import com.xiw.pattern.factory.v4.yuanliao.RouJiaMoYLFactory;

/**
 * @author xiwang
 * @since 2022-05-13 17:33
 */
public abstract class RouJiaMoStore {
    private String name;
    private RouJiaMoYLFactory factory;

    public RouJiaMoStore(RouJiaMoYLFactory factory) {
        this.factory = factory;
        this.name="unknown";
    }

    public abstract RouJiaMo createRouJiaMo(String type);

    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = createRouJiaMo(type);
        rouJiaMo.prepare(factory);
        rouJiaMo.fire();
        rouJiaMo.pack();
        rouJiaMo.finish();
        return rouJiaMo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
