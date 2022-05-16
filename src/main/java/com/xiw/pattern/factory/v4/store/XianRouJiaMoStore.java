package com.xiw.pattern.factory.v4.store;

import com.xiw.pattern.factory.v4.bean.*;
import com.xiw.pattern.factory.v4.yuanliao.XianRouJiaMoYLFactory;

/**
 * @author xiwang
 * @since 2022-05-13 17:39
 */
public class XianRouJiaMoStore extends RouJiaMoStore {

    public XianRouJiaMoStore() {
        super(new XianRouJiaMoYLFactory());
        super.setName("西安店");
    }

    @Override
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        try {
            Class<?> clazz = Class.forName("com.xiw.mode.factory.v4.bean.Xian" + type + "RouJiaMo");
            rouJiaMo = (RouJiaMo) clazz.newInstance();
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            throw new RuntimeException("西安店不生产这种口味店肉夹馍");
        }
        return rouJiaMo;
    }
}
