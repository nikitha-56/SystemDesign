// package BuilderPattern;

public class BuilderPattern {

   static class Pizza{
        private String sauce;
        private String toppings;
        private String base;

        Pizza(PizzaBuilder builder){
            this.sauce=builder.sauce;
            this.toppings=builder.toppings;
            this.base=builder.base;
        }

    public String toString(){
        return "Pizza with "+ sauce +" ,"+ toppings+" , "+base;
    }
    static class PizzaBuilder{
         private String sauce;
        private String toppings;
        private String base;

        public PizzaBuilder setSauce(String sauce){
            this.sauce=sauce;
            return this;
        }

        public PizzaBuilder setToppings(String toppings){
            this.toppings=toppings;
            return this;
        }

        public PizzaBuilder setBase(String base){
            this.base=base;
            return this;
        }

        public Pizza build(){
            return new Pizza(this);
        }
    }
    }
    public static void main(String[] args) {
       Pizza pizza=new Pizza.PizzaBuilder()
                .setBase("thin crust")
                .setSauce("tomoto")
                .setToppings("cheese")
                .build();
                  System.out.println(pizza);
    }
}
