package com.xiw.mode.factory.v2;

import com.xiw.mode.factory.v2.bean.LaRouJiaMo;
import com.xiw.mode.factory.v2.bean.RouJiaMo;
import com.xiw.mode.factory.v2.bean.SuanRouJiaMo;
import com.xiw.mode.factory.v2.bean.TianRouJiaMo;

/**
 * @author xiwang
 * @since 2022-05-13 17:02
 */
public class SimpleRouJiaMoFactory {
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new SuanRouJiaMo();

        } else if (type.equals("Tian")) {
            rouJiaMo = new TianRouJiaMo();
        } else if (type.equals("La")) {
            rouJiaMo = new LaRouJiaMo();
        }
        return rouJiaMo;
    }

}
