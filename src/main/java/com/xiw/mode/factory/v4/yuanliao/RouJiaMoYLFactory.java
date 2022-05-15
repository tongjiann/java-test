package com.xiw.mode.factory.v4.yuanliao;

import com.xiw.mode.factory.v4.yuanliao.bean.Meat;
import com.xiw.mode.factory.v4.yuanliao.bean.YuanLiao;

/**
 * @author xiwang
 * @since 2022-05-13 17:34
 */
public interface RouJiaMoYLFactory {
    Meat createMeat();

    YuanLiao createYuanLiao();

}
