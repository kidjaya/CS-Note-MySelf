package java基础面试题.basic.Enum;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

public class Test2 {
    public static void main(String[] args) {
        System.out.println(SeasonType.SPRING);
        System.out.println(SeasonType.SUMMER.getName());
    }
}

enum SeasonType{
    SPRING,SUMMER("summer"),AUTUMN,WINTER;
    private String name;

    SeasonType(String name) {
        this.name = name;
    }

    SeasonType() {
    }

    public String getName() {
        return name;
    }
}
