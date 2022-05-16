package com.xiw.pattern.factory.v4.yuanliao;

import com.xiw.pattern.factory.v4.yuanliao.bean.*;

/**
 * @author xiwang
 * @since 2022-05-13 17:40
 */
public class ZhejiangRouJiaMoYLFactory implements RouJiaMoYLFactory{
    @Override
    public Meat createMeat() {
        return new WuhuaMeat();
    }

    @Override
    public YuanLiao createYuanLiao() {
        return new ZhejiangTeSeYuanLiao();
    }
}
