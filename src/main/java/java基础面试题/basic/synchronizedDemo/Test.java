package java基础面试题.basic.synchronizedDemo;

import java.util.Date;

/**

* Created by lance on 2017/1/19.

*/

public class Test {

    public static void main(String[] args) {

        BottomService bottomService = new BottomService();

        UpperService upperService = new UpperServiceImpl(bottomService);

        System.out.println("=============== callBottomService start ==================:" + new Date());

        String result = upperService.callBottomService("callBottomService start --> ");

        //upperTaskAfterCallBottomService执行必须等待callBottomService()调用BottomService.bottom()方法返回后才能够执行

        upperService.upperTaskAfterCallBottomService(result);

        System.out.println("=============== callBottomService end ====================:" + new Date());

    }

}

