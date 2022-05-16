package com.xiw.mode.adapter;

import org.junit.Test;

public class MobileTest {
    @Test
    public void adapterTest(){
        Mobile mobile = new Mobile();
        V220Power v220Power = new V220Power();
        V5PowerAdapter v5PowerAdapter = new V5PowerAdapter(v220Power);
        mobile.inputPower(v5PowerAdapter);
    }
}