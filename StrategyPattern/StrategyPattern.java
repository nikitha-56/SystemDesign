// package StrategyPattern;

public class StrategyPattern {
    interface PaymentStrategy{
        void pay(int amt);
    }

    static class UpiPayment implements PaymentStrategy{
          public void pay(int amt){
            System.out.println("Upi Payment of "+ amt);
          }
    }

    static class CardPayment implements PaymentStrategy{
          public void pay(int amt){
            System.out.println("Card Payment of "+ amt);
          }
    }

    static class CashPayment implements PaymentStrategy{
          public void pay(int amt){
            System.out.println("Cash Payment of "+ amt);
          }
    }

    static class PaymentContext{
        private  PaymentStrategy strategy;
        public PaymentContext(PaymentStrategy strategy){
            this.strategy=strategy;
        }

        public void executePayment(int amt){
            strategy.pay(amt);
        }
    }
    public static void main(String[] args) {
        StrategyPattern sp=new StrategyPattern();
        PaymentContext pc=new PaymentContext(new CardPayment());
         pc.executePayment(500);
         PaymentContext pc2=new PaymentContext(new CashPayment());
         pc2.executePayment(2000);
        
    }
}
