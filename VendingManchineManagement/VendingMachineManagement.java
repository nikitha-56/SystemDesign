 package VendingManchineManagement;
import java.util.*;
public class VendingMachineManagement {
   static class Item{
    private String name;
    private int price;
    private int noOfItems;
    Item(String name,int price,int noOfItems){
        this.name=name;
        this.price=price;
        this.noOfItems=noOfItems;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getNoOfItems(){
        return noOfItems;
    }
    public boolean getisAvailable(){
        return noOfItems>0;
    }

    public void decrementCnt(){
        noOfItems--;
    }
    public void incrementCnt(){
        noOfItems++;
    }

   }

   static class User{
    private String name;
    private long phoneNo;
    User(String name,long phoneNo){
        this.name=name;
        this.phoneNo=phoneNo;
    }

    public String getName(){return name;}
    public long getPhoneNo(){return phoneNo;}

   }

   static class VendingMachineManager{
    private static VendingMachineManager instance;
     List<Observer> observers=new ArrayList<>();
     List<Cart> carts=new ArrayList<>();
   public  VendingMachineManager(){}
   public static VendingMachineManager getInstance(){
    if(instance==null){
        instance=new VendingMachineManager();
    }
    return instance;
   }

   public void addObserver(Observer o){
        observers.add(o);
    }

    public void removeObserver(Observer o){
        observers.remove(o);
    }

    public void payTheBill(Cart c,Payment p){
       int amt= c.calculateTotal();
       if(amt==0 || amt<0){
            System.out.println("payment cant be processed add something to cart");
            return;
       }
              p.pay(amt);
       notifyAllToObservers("Payment of Rs. "+ amt+ "done for cart "+ c.getCartId());
    }

    public  void notifyAllToObservers(String msg){
        for(Observer o:observers){
            o.notify(msg);
        }
    }

   }

   static class VendingMachine{
    List<Item> items=new ArrayList<>();
    List<User> users=new ArrayList<>();
    VendingMachine(){}

    public void addItem(Item i){
        items.add(i);
    }
    public void removeItem(Item i){
        items.remove(i);
    }

    public void addUser(User u){
        users.add(u);
    }
    public void removeUser(User u){
        users.remove(u);
    }

   }

   static class Cart{
    ArrayList<Item> items=new ArrayList<>();
    private String cartId;
    Cart(){
        cartId=UUID.randomUUID().toString();
    }

    public  String getCartId(){
        return cartId;
    }
    public void addToCart(Item i,User u){
        if(i.getisAvailable()){
                items.add(i);
         i.decrementCnt();
          return;
        }
        else{
            System.out.println("Out of Stock: "+ i.getName());
        }
    }

    public void removeFromCart(Item i,User u){
        if(items.contains(i)){
            items.remove(i);
            i.incrementCnt();
            System.out.println(i.getName()+" removed from cart");
        }
    }
    public int calculateTotal(){
        int price=0;
        for(Item item:items){
            price+=item.getPrice();
        }
        return price;
    }

    public void getAllItems(User u){
      System.out.println(u.getName()+ " cart contains :");
        for(Item item:items){
            System.out.println(item.getName() + " : "+ item.getPrice());
        }
    }


   }

   interface Payment{
    public void pay(int amt);
   }

   static class UpiPayment implements Payment{
    public void pay(int amt){
        System.out.println("Paid using Upi"+amt);
    }
   }

    static class CardPayment implements Payment{
    public void pay(int amt){
        System.out.println("Paid using card: "+amt);
    }
   }

    static class CashPayment implements Payment{
    public void pay(int amt){
        System.out.println("Paid using cash: "+amt);
    }
   }

    static class PaymentFactory{
      public   Payment createPayment(String type){
        if(type.equalsIgnoreCase("upi")){
            return new UpiPayment();
        }
        else if(type.equalsIgnoreCase("card")){
          return new CardPayment();
        }
        else if(type.equalsIgnoreCase("cash")){
            return new CashPayment();
        }
        else{
            return null;
        }
      }
   }

   interface Observer{
    void notify(String msg);
   }
    static class NotifyToVendingMachineManager implements Observer{
         public void notify(String msg){
            System.out.println("Notification to vmm :"+ msg);
         }
   }

    static class NotifyToUser implements Observer{
         public void notify(String msg){
            System.out.println("Notification to user :"+ msg);
         }
   }

   public static void main(String[] args) {
    User u1=new User("nikki", 9685746);
     User u2=new User("cherry", 968576);
     Item i1=new Item("blue lays", 20, 10);
     Item i2=new Item("red lays", 20, 10);
     VendingMachineManager manager=VendingMachineManager.getInstance();
    Observer o1=new NotifyToUser();  
    Observer o2=new NotifyToVendingMachineManager();
   manager.addObserver(o1);
   manager.addObserver(o2);
   VendingMachine machine=new VendingMachine();
   machine.addItem(i2);
   machine.addItem(i1);
   Cart c1=new Cart();
       c1.addToCart(i2, u2);
       c1.addToCart(i1, u2);
      PaymentFactory factory=new PaymentFactory();
     Payment p1= factory.createPayment("cash");
      if(p1==null) {
        System.out.println("Invalid payment method pls try again");
        return;
      }
       manager.payTheBill(c1,p1);
       c1.getAllItems(u2);
       c1.removeFromCart(i2,u2);
      System.out.println(i2.getNoOfItems());
       System.out.println();
        c1.getAllItems(u2);

   }
}
