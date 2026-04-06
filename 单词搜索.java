import com.sun.source.tree.Tree;

public class 单词搜索 {
    int[][] dirs={
            {1,0},
            {-1,0},
            {0,1},
            {0,-1}
    };
   public boolean exist(char[][] board,String word){
       int row=board.length;
       int column=board[0].length;
       boolean[][] visited=new boolean[row][column];
       for(int i=0;i<row;i++){
           for(int j=0;j<column;j++){
               if(dfs(board,word,i,j,0,visited)){
                   return true;
               }
           }
       }
       return false;
   }

   //k表示匹配到第几个字符了，dfs函数表示以当前
    //dfs(i,j,k)表示从网格的（i，j）位置出发，
   // 当前要匹配word的第k个字符，能不能把剩下的全部找到
   public boolean dfs(char[][] board,String word,int i,int j,
                      int k,boolean[][] visited){
       if(k==word.length()){
           return true;
       }
       if(i<0 || i>=board.length || j<0 || j>=board[0].length){
           return false;
       }
       if(visited[i][j]){ //表示被访问过了
           return false;
       }
       if(board[i][j]!=word.charAt(k)){
           return false;
       }
       visited[i][j]=true;
       for(int[] dir : dirs){
           int newRow=i+dir[0];
           int newColumn=j+dir[1];
           if(dfs(board,word,newRow,newColumn,k+1,visited)){
               return true;
           }
       }
       visited[i][j]=false;
       return false;


   }
}
