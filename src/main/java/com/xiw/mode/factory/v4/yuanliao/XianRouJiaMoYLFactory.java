package com.xiw.mode.factory.v4.yuanliao;

import com.xiw.mode.factory.v4.yuanliao.bean.FreshMeat;
import com.xiw.mode.factory.v4.yuanliao.bean.Meat;
import com.xiw.mode.factory.v4.yuanliao.bean.XianTeSeYuanLiao;
import com.xiw.mode.factory.v4.yuanliao.bean.YuanLiao;

/**
 * @author xiwang
 * @since 2022-05-13 17:40
 */
public class XianRouJiaMoYLFactory implements RouJiaMoYLFactory{
    @Override
    public Meat createMeat() {
        return new FreshMeat();
    }

    @Override
    public YuanLiao createYuanLiao() {
        return new XianTeSeYuanLiao();
    }
}
