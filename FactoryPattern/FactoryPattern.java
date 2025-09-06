
public class FactoryPattern {

    interface Payment{
        void pay(int amt);
    }

    static class UpiPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using upi of amt :"+ amt);
        }
    }
    static class CardPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using card of amt :"+ amt);
        }
    }

    static class CashPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using cash of amt :"+ amt);
        }
    }

    public static class PaymentFactory{
        public static Payment createPayment(String type){
              if(type.equalsIgnoreCase("upi")){
                return new UpiPayment();
              }
              else if(type.equalsIgnoreCase("card")){
                return new CardPayment();
              }
              else{
                return new CashPayment();
              }
        }
    }
    public static void main(String[] args) {
        PaymentFactory pf=new PaymentFactory();
         Payment p1= pf.createPayment("upi");
          p1.pay(500);
    }
}
