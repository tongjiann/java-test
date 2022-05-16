package com.xiw.pattern.factory.v3;

import com.xiw.pattern.factory.v3.bean.XianLaRouJiaMo;
import com.xiw.pattern.factory.v3.bean.RouJiaMo;
import com.xiw.pattern.factory.v3.bean.XianSuanRouJiaMo;
import com.xiw.pattern.factory.v3.bean.XianTianRouJiaMo;

/**
 * @author xiwang
 * @since 2022-05-13 17:08
 * 西安肉夹馍店
 */
public class XianRouJiaMoStore extends RouJiaMoStore {
    @Override
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new XianSuanRouJiaMo();
        } else if (type.equals("Tian")) {
            rouJiaMo = new XianTianRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new XianLaRouJiaMo();
        }
        return rouJiaMo;


    }
}
