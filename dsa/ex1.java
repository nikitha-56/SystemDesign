import java.util.*;
public class ex1 {
    public static int helper(String arr[]){
        int ans=0;
        for(int i=0;i<arr.length;i++){
            String st1=arr[i];
            for(int j=i+1;j<arr.length;j++){
                String st2=arr[j];
                if(st2.indexOf(st1)==0 || st1.indexOf(st2)==0){
                    ans++;
                }
            }
        }
        return ans;
    }

    public static String[][] fixBlocks(String[][] arr){
        for(int i=0;i<arr.length;i+=4){
            for(int j=0;j<arr[0].length;j+=4){
                solve(arr,i,j);
            }
        }
        return arr;
    }

    public static String[][] solve(String [][] arr,int Srow,int Scol){
        int rows=-1;
        int ncol=-1;
        Set<Integer> hs=new HashSet<>();
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                String val=arr[Srow+i][Scol+j];
                if(val.equals("?")){
                   rows=Srow+i;
                   ncol=Scol+j;
                }
                else{
                    hs.add(Integer.parseInt(val));
                }
            }
        }
        for(int i=1;i<=16;i++){
            if(!hs.contains(i)){
                arr[rows][ncol]=Integer.toString(i);
                break;
            }
        }
        return arr;
    }
    public static void main(String[] args) {
        // String arr[]={"wall","wallpaper","wallate","physcology","physco","abc"};
        // System.out.println(helper(arr));
        String[][] arr ={
    {"1","2","3","4","1","2","3","4"},
    {"5","6","7","8","5","6","7","8"},
    {"9","10","11","?", "9","10","11","?"},
    {"13","14","15","16","13","14","15","16"}
};
        String ans[][]=fixBlocks(arr);
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
        
    }
}
