import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class 电话号码的字母组合 {
    private String[] MAP={
            "",
            "",
            "abc",   //2
            "def",   //3
            "ghi",   //4
            "jkl",   //5
            "mno",   //6
            "pqrs",  //7
            "tuv",   //8
            "wxyz"   //9
    };
    public List<String> lettersCombination(String s){

        List<String> result=new ArrayList<>();
        if(s==null || s.length()==0) return result;
        StringBuilder path=new StringBuilder();
        backtrack(result,path,s,0);
        return result;


    }

    //index表示处理到digits的第index位数字
    public void backtrack(List<String> result,StringBuilder path,
                          String s,int index){
        if(path.length()==s.length()){
            result.add(path.toString());
            return ;
        }
        char ch=s.charAt(index);
        int num=ch-'0';
        String currString=MAP[num];
        for(int i=0;i<currString.length();i++){
            path.append(currString.charAt(i));
            backtrack(result,path,s,index+1);
            path.deleteCharAt(path.length()-1);
        }
    }

}
