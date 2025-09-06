package abstractFactory;

public class AbstractFactory {

    interface Starter{
        void serveStarter();
    }

    interface MainCourse{
        void serveMainCourse();
    }

    class VegStarter implements Starter{
        public void serveStarter(){
            System.out.println("Serving veg starter");
        }
    }

    class VegMainCourse implements MainCourse{   
        public void serveMainCourse(){
            System.out.println("Serving veg main course");
        }
    }

    class NonvegStarter implements Starter{
        public void serveStarter(){
            System.out.println("Serving non veg starter");
        }
    }

    class NonVegMainCourse implements MainCourse{   
        public void serveMainCourse(){
            System.out.println("Serving non veg main course");
        }
    }


    interface FoodFactory{
        Starter createStarter();
        MainCourse createMainCourse();
    }

    class VegFactory implements FoodFactory{
        public Starter createStarter(){
           return new VegStarter();
        }

        public MainCourse createMainCourse(){
            return new VegMainCourse();
        }
    }

     class NonvegFactory implements FoodFactory{
        public Starter createStarter(){
           return new NonvegStarter();
        }

        public MainCourse createMainCourse(){
            return new NonVegMainCourse();
        }

        
    }


    public static void main(String[] args) {
          AbstractFactory af=new AbstractFactory();
          FoodFactory vegf=af.new VegFactory();
         Starter st= vegf.createStarter();
         MainCourse mc= vegf.createMainCourse();
         st.serveStarter();
         mc.serveMainCourse();

           FoodFactory nvegf=af.new NonvegFactory();
         Starter stn= nvegf.createStarter();
         MainCourse mcn= nvegf.createMainCourse();
         stn.serveStarter();
         mcn.serveMainCourse();
    }
}
