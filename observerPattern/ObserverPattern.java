package observerPattern;
import java.util.*;
public class ObserverPattern {
 
    interface Observer{
         void notify(String msg);  
    }

    static class EmailNotification implements Observer{
        public void notify(String msg){
            System.out.println("Email notification :"+ msg);
        }
    }

    static class MobileNotification implements Observer{
        public void notify(String msg){
            System.out.println("Mobile notification :"+ msg);
        }
    }

    static class NotificationService{
         private List<Observer> observers=new ArrayList<>();
         public void addObserver(Observer o){
            observers.add(o);
         }
         public void removeObserver(Observer o){
            observers.remove(o);
         }

         public void notifyAll(String msg){
            for(Observer o:observers){
            o.notify(msg);
            }
         }

    }

    
    public static void main(String[] args) {
        ObserverPattern op=new ObserverPattern();
       NotificationService ns=new NotificationService();
       ns.addObserver(new EmailNotification());
       ns.addObserver(new MobileNotification());
       ns.notifyAll("New Video uploaded");
    }
}
