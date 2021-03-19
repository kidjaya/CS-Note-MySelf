package java基础面试题.basic.synchronizedDemo;

/**

* Created by lance on 2017/1/19.

*/

public class UpperServiceImpl implements UpperService {

    private BottomService bottomService;

    @Override

    public void upperTaskAfterCallBottomService(String upperParam) {

        System.out.println(upperParam + " upperTaskAfterCallBottomService() execute.");

    }

    public UpperServiceImpl(BottomService bottomService) {

        this.bottomService = bottomService;

    }

    @Override

    public String callBottomService(final String param) {

        return bottomService.bottom(param + " callBottomService.bottom() execute --> ");

    }

}

