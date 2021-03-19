package java基础面试题.basic.synchronizedDemo;

public interface UpperService {

    public void upperTaskAfterCallBottomService(String upperParam);

    public String callBottomService(final String param);

}

