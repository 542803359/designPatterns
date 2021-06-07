package com.xiaowenxing.demofactory.factoryMethod.service;

import com.xiaowenxing.demofactory.simple.service.TeaProduction;

/**
 * 生产绿茶
 *
 * @author XWX
 * @date 2021/5/20 15:03
 * @since 3.2
 **/
public class GreenTea extends DrinkProduction {

    @Override
    public void createDrink() {

        System.out.println("生产绿茶");
        super.name = "绿茶";
        super.pickingDrink();
        super.bakeDrink();

    }

}
