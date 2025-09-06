
import java.util.*;
public class Singleton {

    
    public static class Logger{
        private static Logger instance;
        Logger(){
            System.out.println("Logger is created");
        }

        public static Logger getInstance(){
            if(instance==null){
                instance=new Logger();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        Logger l1=Logger.getInstance();
         Logger l2=Logger.getInstance();
         System.out.println(l1==l2);
    }
    
}


