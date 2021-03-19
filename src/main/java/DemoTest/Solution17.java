package DemoTest;

import java.util.Stack;

/**
 * @author kidjaya
 */
public class Solution17 {
    int nine = 0,start,n;
    StringBuilder res;
    char[] num,loop = {'0','1','2','3','4','5','6','7','8','9'};
    public String printNumber(int n){
        res = new StringBuilder();
        num = new char[n];
        this.n = n;
        start = n-1;
        dfs(0);
        res.deleteCharAt(res.length() - 1);
        return res.toString();
    }

    public void dfs(int x){
        if(x == n){
            String temp = String.valueOf(num).substring(start);
            if(!"0".equals(temp)) {
                res.append(temp+",");
            }
            //难点，需要明白各个参数的意思
            if(n - start == nine){
                start--;
            }
            return;
        }
        for(char c : loop){
            if(c == '9') {
                //对应还原
                nine++;
            }
            num[x] = c;
            dfs(x+1);
        }
        //还原
        nine--;
    }

    public static void main(String[] args) {
        Solution17 solution17 = new Solution17();
        System.out.println(solution17.printNumber(2));
    }
}
