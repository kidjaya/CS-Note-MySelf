package DemoTest;

/**
 * @author kidjaya
 */
public class KMP {
    private int[][] dp;
    private String pat;

    public KMP(String pat){
        this.pat = pat;
        int M = pat.length();
        dp = new int[M][256];
        dp[0][pat.charAt(0)] = 1;
        int X = 0;
        for(int j = 1;j<M;j++){
            for(int c = 0;c<256;c++) {
                //j=1 X=0 相当于获取前一个状态
                dp[j][c] = dp[X][c];
            }
            //匹配正确状态
            dp[j][pat.charAt(j)] = j+1;
            //更新前一个操作状态
            X = dp[X][pat.charAt(j)];
        }
    }

}
