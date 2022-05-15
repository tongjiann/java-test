package com.xiw.mode.factory.v4.store;

import com.xiw.mode.factory.v4.bean.*;
import com.xiw.mode.factory.v4.yuanliao.ZhejiangRouJiaMoYLFactory;

/**
 * @author xiwang
 * @since 2022-05-13 17:39
 */
public class ZhejiangRouJiaMoStore extends RouJiaMoStore {

    public ZhejiangRouJiaMoStore() {
        super(new ZhejiangRouJiaMoYLFactory());
    }

    @Override
    public RouJiaMo createRouJiaMo(String type) {
        RouJiaMo rouJiaMo = null;
        if (type.equals("Suan")) {
            rouJiaMo = new ZhejiangSuanRouJiaMo();
        } else if (type.equals("Tian")) {
            rouJiaMo = new ZhejiangTianRouJiaMo();
        } else if (type.equals("Ku")) {
            rouJiaMo = new ZhejiangKuRouJiaMo();
        }else if (type.equals("La")) {
            rouJiaMo = new ZhejiangLaRouJiaMo();
        }
        return rouJiaMo;
    }
}
