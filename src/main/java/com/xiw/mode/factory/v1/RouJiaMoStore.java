package com.xiw.mode.factory.v1;

import com.xiw.mode.factory.v1.bean.*;

/**
 * @author xiwang
 * @since 2022-05-13 16:42
 */
public class RouJiaMoStore {
    /**
     * 根据传入类型卖不同的肉夹馍
     *
     * @param type
     * @return
     */
    public RouJiaMo sellRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;

        if (type.equals("Suan")) {
            rouJiaMo = new SuanRouJiaMo();
        } else if (type.equals("Tian")) {
            rouJiaMo = new TianRouJiaMo();
        } else if (type.equals("Ku")) {
            rouJiaMo = new KuRouJiaMo();
        }else if (type.equals("La")) {
            rouJiaMo = new LaRouJiaMo();
        }
        assert rouJiaMo != null;
        rouJiaMo.prepare();
        rouJiaMo.fire();
        rouJiaMo.pack();
        return rouJiaMo;
    }

}
