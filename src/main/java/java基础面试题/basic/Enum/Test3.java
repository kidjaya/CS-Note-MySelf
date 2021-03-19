package java基础面试题.basic.Enum;

public class Test3 {
    public static void main(String[] args) {
        SeasonType2 seasonType2 = SeasonType2.valueOfKey(2);
        System.out.println(seasonType2.getName());
        System.out.println(SeasonType2.SUMMER.getOrder());
        System.out.println(SeasonType2.WINTER.getName());
        System.out.println(SeasonType2.AUTUMN);
        System.out.println(SeasonType2.valueOf("WINTER").getOrder());

    }
}

enum SeasonType2{
    SPRING,SUMMER(2,"夏天"),AUTUMN,WINTER(4,"冬天");
    private int order;
    private String name;
    SeasonType2() {
    }

    SeasonType2(int order, String name) {
        this.order = order;
        this.name = name;
    }

    public int getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public static SeasonType2 valueOfKey(Integer key){
        for (SeasonType2 seasonType2 : SeasonType2.values()){
            if (seasonType2.order == key){
                return seasonType2;
            }
        }
        throw new IllegalArgumentException("No element matches "+key);
    }
}
