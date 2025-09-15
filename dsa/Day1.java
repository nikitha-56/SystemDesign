import java.util.*;
public class Day1 {
    static class Node{
        int data;
        Node next;
        Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    static class Nodet{
        int data;
        Nodet left;
        Nodet right;
        Nodet(int data){
            this.left=null;
            this.data=data;
            this.right=null;
        }
    }
    public static List<List<String>> groupAnagrams(String arr[]){
        HashMap<String,List<String>> ans=new HashMap<>();
        for(String str:arr){
            char s[]=str.toCharArray();
            Arrays.sort(s);
            String key=new String(s);
            ans.computeIfAbsent(key,k->new ArrayList<>()).add(str);
        }
        return new ArrayList<>(ans.values());
    }

    public static Node mergeTwoLinkedLists(Node head1,Node head2){
        Node curr1=head1;
        Node curr2=head2;
        Node temp=new Node(-1);
        Node curr=temp;
        while(curr1!=null && curr2!=null){
            if(curr1.data<=curr2.data){
                curr.next=curr1;
                curr1=curr1.next;
            }
            else{
                curr.next=curr2;
                curr2=curr2.next;
            }
            curr=curr.next;
        }
        if(curr1!=null){
            curr.next=curr1;
        }
        if(curr2!=null){
            curr.next=curr2;
        }
        return temp.next;
    }

    public static void traverseLinkedList(Node head){
        Node curr=head;
        while(curr!=null){
            System.out.print(curr.data+"->");
            curr=curr.next;
        }
        System.out.println("null");
    }

    public static int maximumProductSubArray(int arr[]){
        int maxprod=Integer.MIN_VALUE;
        int prod=1;
        for(int i=0;i<arr.length;i++){
             prod*=arr[i];
             if(prod<0){
                prod=0;
             }
             maxprod=Math.max(maxprod,prod);
             
        }
        return maxprod;
    }

    public static int[] nextGreaterElement(int arr[]){
        int res[]=new int[arr.length];
        Stack<Integer> s=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            while(!s.isEmpty() && arr[s.peek()]<arr[i]){
                s.pop();
            }
            if(!s.isEmpty()){
                res[i]=arr[s.peek()];
            }
            else{
                res[i]=-1;
            }
            s.push(i);
        }
        return res;
    }

    public static int[] nextGreaterElementCircular(int arr[]){
        int res[]=new int[arr.length];
        int n=arr.length;
        Stack<Integer> s=new Stack<>();
        for(int i=2*n-1;i>=0;i--){
            int idx=i%n;
            while(!s.isEmpty() && arr[s.peek()]<=arr[idx]){
                s.pop();
            }
            if(i<n){
                if(!s.isEmpty()){
                    res[i]=arr[s.peek()];
                }
            }
            s.push(idx);
        }
        return res;
    }

    public static boolean detectCycle(Node head){
        Node slowp=head;
        Node fastp=head;
        while(fastp!=null && fastp.next!=null){
            slowp=slowp.next;
            fastp=fastp.next.next;
            if(slowp==fastp){
                return true;
            }
        }
        return false;
    }

    public static void push(Stack<Integer> s,Stack<Integer> minstack,int val){
       s.push(val);
       if(minstack.isEmpty() || minstack.peek()>=val){
        minstack.push(val);
       }
    }

    public static void pop(Stack<Integer> s,Stack<Integer> minstack){
        if(s.peek()==minstack.peek()){
               s.pop();
            minstack.pop();
        }
        else{
            s.pop();
        }
    }

    public static int getmin(Stack<Integer> s,Stack<Integer> minstack){
        return minstack.peek();
    }

    public static int getTop(Stack<Integer> s,Stack<Integer> minstack){
        return s.peek();
    }

    public static int trappingRainWater(int height[]){
        int l=0,r=height.length-1;
        int lmax=Integer.MIN_VALUE;
        int rmax=Integer.MIN_VALUE;
        int trappedwater=0;
        while(l<=r){
           if(height[l]<=height[r]){
            if(height[l]>=lmax){
            lmax=height[l];
            }
            else{
             trappedwater+=lmax-height[l];
            }
             l++;
        }
        else{
            if(height[r]>=rmax){
              rmax=height[r];
            }
          else{
              trappedwater+=rmax-height[r];
          }
           r--;
        }
    }
        return trappedwater;
    }

    public static String longestPalindromicSubString(String s){
        String ans="";
        int maxlen=0;
    for(int i=0;i<s.length();i++){
        String olp=isPalindrome(i, i, s);
        if(olp.length()>maxlen){
            maxlen=olp.length();
            ans=olp;
        }
        String elp=isPalindrome(i, i+1, s);
          if(elp.length()>maxlen){
            maxlen=elp.length();
            ans=elp;
          }

    }
        return ans;
    }

    public static String isPalindrome(int i,int j,String s){
        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            i--;
            j++;
        }
        return s.substring(i+1,j);
    }

    public static Nodet insert(Nodet root,int val){
        Nodet newnode=new Nodet(val);
        if(root==null){
            return newnode;
        }
        Queue<Nodet> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Nodet curr=q.remove();
            if(curr.left==null){
                curr.left=newnode;
                break;
            }
            else{
                q.add(curr.left);
            }
            if(curr.right==null){
                curr.right=newnode;
                break;
            }
            else{
                q.add(curr.right);
            }
        }
        return root;
    }

    static int diameter=0;
    public static int findDiameter(Nodet root){
         height(root);
         return diameter;
    }

    public static int height(Nodet root){
        if(root==null){
            return 0;
        }
        int lmax=height(root.left);
        int rmax=height(root.right);
        diameter=Math.max(diameter,lmax+rmax);
        return Math.max(lmax,rmax)+1;
    }

    public static void preorder(Nodet root){
        if(root==null){
            return;
        }
        System.out.println(root.data);
        preorder(root.left);
        preorder(root.right);
    }

    public static Nodet lowestCommonAncestor(Nodet root,int p,int q){
        if(root==null || root.data==p || root.data==q){
            return root;
        }
        Nodet leftLca=lowestCommonAncestor(root.left, p, q);
        Nodet rightLca=lowestCommonAncestor(root.right, p, q);
        if(leftLca==null) return rightLca;
        if(rightLca==null) return leftLca;
        return root;
    }

    public static void main(String[] args) {
    //     String arr[]={"eat","tea","tan","ate","nat","bat"};
    //    List<List<String>> ans= groupAnagrams(arr);
    //    for(List<String> val:ans){
    //     System.out.println(val+" ");
    //    }
    Node head1=new Node(2);
    head1.next=new Node(6);
    head1.next.next=new Node(13);
    head1.next.next.next=new Node(131);
    head1.next.next.next.next=head1;
    // System.out.println(detectCycle(head1));
        

    // Node head2=new Node(7);
    // head2.next=new Node(9);
    // head2.next.next=new Node(31);

    // Node newhead=mergeTwoLinkedLists(head1,head2);
    // traverseLinkedList(newhead);

    // int arr[]={2,3,-2,4};
    // System.out.println(maximumProductSubArray(arr));
    int arr[]={4,5,2,25,13};
//    int ans[]= nextGreaterElementCircular(arr);
    // for(int num:ans){
    //     System.out.print(num+" ");
    // }

//     Stack<Integer> s=new Stack<>();
//     Stack<Integer> ms=new Stack<>();
//     push(s,ms,-2);
//     push(s,ms,0);
//     push(s,ms,-3);
//    System.out.println(getmin(s, ms));
//   pop(s, ms);
//     System.out.println(getTop(s,ms));
//     System.out.println(getmin(s,ms));
int height[]={4,2,0,3,2,5};
// System.out.println(trappingRainWater(height));
String lps="babad";
// System.out.println(longestPalindromicSubString(lps));
int arr3[]={1,2,3,5,6};
Nodet root=null;
   for(int i=0;i<arr3.length;i++){
    root=insert(root,arr3[i]);
   }
//    preorder(root);
// System.out.println(findDiameter(root));
int p=2,q=1;
Nodet ans=lowestCommonAncestor(root,p,q);
System.out.println(ans.data);


    }
}
