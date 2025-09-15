import java.util.*;

public class Day2 {

   static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static int longestSubStringWithoutRepeatingCharacter(String s){
        HashSet<Character> hs=new HashSet<>();
        int maxlen=0;
        int i=0,j=0;
        while(j<s.length()){
            char ch=s.charAt(j);
            if(!hs.contains(ch)){
                hs.add(ch);
                maxlen=Math.max(maxlen,j-i+1);
                j++;
            }
            else{
                hs.remove(s.charAt(i));
                i++;
            }
        }
        return maxlen;
    }

    public static boolean subArraywithSum(int arr[],int tar){
        int prefixsum[]=new int[arr.length+1];
        int sum=0;
        prefixsum[0]=0;
       for(int i=1;i<arr.length+1;i++){
         prefixsum[i]=prefixsum[i-1]+arr[i-1];
       }
       int i=0,j=1;
       while(j<prefixsum.length){
            if(prefixsum[j]-prefixsum[i]==tar){
                return true;
            }
            else if(prefixsum[j]-prefixsum[i]>tar){
                 i++;
            }
            else{
            j++;
            }
       }
       return false;
    }

    public static boolean subArrayWithSum2(int arr[],int tar){
        HashMap<Integer,Integer> hm=new HashMap<>();
         int sum=0;
         hm.put(0,-1);
        for(int i=0;i<arr.length;i++){
            sum+=arr[i];
            if(hm.containsKey(sum-tar)){
                return true;
            }
            else{
                hm.put(sum,i);
            }
        }
        return false;
    }

    public static Node removeNthnodeFromEnd(Node head,int n){
        Node curr=head;
        int cnt=0;
        while(curr!=null){
             curr=curr.next;
             cnt++;
        }
        if(n==cnt){
            return head.next;
        }
        curr=head;
        int val=cnt-n;
        for(int i=0;i<val-1;i++){
            curr=curr.next;
        }
        curr.next=curr.next.next;
        return head;
    }

    public static void traversell(Node curr){
        while(curr!=null){
            System.out.print(curr.data+"->");
            curr=curr.next;
        }
        System.out.println("null");
    }
    public static boolean isValidParenthesis(String s){
        int pairs=0;
        Stack<Character> st=new Stack<>();
        int i=0;
        while(i<s.length()){
            char ch=s.charAt(i);
            if(ch=='[' || ch=='(' || ch=='{'){
                st.push(ch);
                i++;
            }
            else{
                if(st.isEmpty()){
                    return false;
                }
                char sstop=st.pop();
               if((sstop=='(' && ch!=')')||  (sstop=='{' && ch!='}')||
               (sstop=='[' && ch!=']')){
                      return false;
               }
               pairs++;
               i++;
            }
        }
        return st.isEmpty() && pairs%2==0;
    }

    public static String minimumSubString(String s,String t){
        if(s.length()<t.length()) return "";
        HashMap<Character,Integer> have=new HashMap<>();
        for(char ch:t.toCharArray()){
            have.put(ch,have.getOrDefault(ch,0)+1);
        }

        HashMap<Character,Integer> need=new HashMap<>();
        int i=0,j=0;
        int si=-1;
        int formed=0;
        int minLen=Integer.MAX_VALUE;
        while(j<s.length()){
            char ch=s.charAt(j);
            need.put(ch,need.getOrDefault(ch,0)+1);
            if(have.containsKey(ch) && have.get(ch).intValue()==need.get(ch).intValue()){
                formed++;
            }
            while (i<=j && formed==have.size()) {
                 if(j-i+1<minLen){
                    minLen=j-i+1;
                    si=i;
                 }

                char left=s.charAt(i);
                need.put(left,need.get(left)-1);
                if(have.containsKey(left) && have.get(left)>need.get(left)){
                    formed--;
                }
                i++;
            }
            j++;
        }
        return si==-1?"":s.substring(si,si+minLen);
        
    }


    public static void main(String[] args) {
        // String s="abcdefabcd";
        // System.out.println(longestSubStringWithoutRepeatingCharacter(s));
        int arr[]={1, 4, 20, 3, 10, 5};
        int tar=33;
        // System.out.println(subArraywithSum(arr,tar));
         int arr2[] = {10, 2, -2, -20, 10};
    int tar1 = -10;
    //    System.out.println(subArrayWithSum2(arr2, tar1)); 
      Node head=new Node(5);
      head.next=new Node(6);
      head.next.next=new Node(7);
      head.next.next.next=new Node(8);
      head.next.next.next.next=new Node(9);
    //   traversell(head);
    //   Node ans=removeNthnodeFromEnd(head,3);
    //   traversell(ans);
      String str="()[]{}";
    //   System.out.println(isValidParenthesis(str));
    String s="ADOBECODEBANC";
    String t="ABC";
   System.out.println(minimumSubString(s,t));

   }
}
