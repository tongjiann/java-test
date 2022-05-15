package com.xiw.mode.factory.v3;

import com.xiw.mode.factory.v3.bean.RouJiaMo;

/**
 * @author xiwang
 * @since 2022-05-13 16:42
 * 肉夹馍总店
 */
public abstract class RouJiaMoStore {

    /**
     * 生成肉夹馍的方法
     *
     * @param type 肉夹馍的类型
     * @return 肉夹馍
     */
    public abstract RouJiaMo createRouJiaMo(String type);

    /**
     * 根据传入类型卖不同的肉夹馍
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = createRouJiaMo(type);
        assert rouJiaMo != null;
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        rouJiaMo.finish();
        return rouJiaMo;
    }

}
