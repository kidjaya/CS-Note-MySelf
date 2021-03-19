package 剑指offer.打砖块;

class Solution {
    int col;
    int row;
    final static int[][] DIRECTION = {{0,1},{1,0},{-1,0},{0,-1}};
    public int[] hitBricks(int[][] grid, int[][] hits) {
        //初始化
        this.col = grid[0].length;
        this.row = grid.length;
        int[][] tempGrid = new int[row][col];
        for(int i = 0;i<row;i++){
            System.arraycopy(grid[i], 0, tempGrid[i], 0, col);
        }

        for(int[] hit : hits){
             int x = hit[0];
             int y = hit[1];
             tempGrid[x][y] = 0;
        }

        //graph
        int size = col * row + 1;
        UnionFind union = new UnionFind(size);

        //Roof
        for(int j = 0;j<col;j++){
            union.union(j,size);
        }

        //other
        for(int i = 1;i<row;i++){
            for(int j = 0;j<col;j++){
                if(tempGrid[i][j] == 1){
                    if(tempGrid[i-1][j] == 1){
                        union.union(getIndex(i-1,j),getIndex(i,j));
                    }
                    if(j>0 && tempGrid[i][j-1] == 1){
                        union.union(getIndex(i,j-1),getIndex(i,j));
                    }
                }
            }
        }
        //逆序补充
        int hitLen = hits.length;
        int[] res = new int[hitLen];
        for(int i = hitLen - 1;i>=0;i--){
            int x = hits[i][0];
            int y = hits[i][1];

            if(grid[x][y] == 0){
                continue;
            }

            int origin = union.getSize(size);
            if(x==0){
                union.union(y,size);
            }

            for(int[] direction : DIRECTION){
                int newX = x+direction[0];
                int newY = x+direction[1];

                if(inArea(newX,newY) && tempGrid[newX][newY]==1){
                    union.union(getIndex(x,y),getIndex(newX,newY));
                }
            }

            int current = union.getSize(size);
            res[i] = Math.max(0,current - origin - 1);
            tempGrid[x][y] = 1;
        }
        return res;
    }

    public boolean inArea(int x,int y){
        return x>=0 && x<row && y>=0 && y<col;
    }

    public int getIndex(int x,int y){
        return x*col+y;
    }

    static class UnionFind{
        private int[] size;
        private int[] parent;
        private int count;

        public UnionFind(int n){
            parent = new int[n];
            size = new int[n];
            this.count = n;
            for(int i = 0;i<n;i++){
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if(x != parent[x]){
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int p,int q){
            int rootP = find(p);
            int rootQ = find(q);
            if(rootP == rootQ){
                return;
            }

            if(size[rootP]>size[rootQ]){
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP]; 
            }else{
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ]; 
            }
            count--;   
        }

        public int count(){
            return this.count;
        }

        public int getSize(int x){
            int root = find(x);
            return size[root];
        }
    }
}