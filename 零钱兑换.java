public class 零钱兑换 {
    public int coinChange(int[] coins,int amount){
        int max=amount+1;

        //所以amount+1一定是一个不可能达到的最大值
        //dp[i]表示凑出金额i所需要的最小硬币数
        int[] dp=new int[amount+1];

        //把dp的元素全部复制为max
        for(int i=0;i<dp.length;i++){
            dp[i]=max;
        }

        dp[0]=0;

        for(int i=1;i<=amount;i++){
            for(int j=0;j<coins.length;j++){
                if(coins[j]<=i){
                    dp[i]=Math.min(dp[i],1+dp[i-coins[j]]);
                }
            }
        }
        return dp[amount] >amount ? -1:dp[amount];

    }
}
