import java.util.*;

import javax.print.DocFlavor.READER;
public class ParkingLotSystem {

    static class User{
        private String name;
        private long phoneNo;
        User(String name,long phoneNo){
            this.name=name;
            this.phoneNo=phoneNo;
        }

        public String getName(){
            return name;
        }
        public long getPhoneNo(){
            return phoneNo;
        }
    }

    static class ParkingLotSystemManager{
        private static ParkingLotSystemManager instance;
        List<Vehicle> vehicles=new ArrayList<>();
       public  List<Floor> floors=new ArrayList<>();
        ParkingLotSystemManager(){
        }
        public static  ParkingLotSystemManager getInstance(){
            if(instance==null){
                instance=new ParkingLotSystemManager();
            }
            return instance;
        }

        public void addVehicle(Vehicle v){
            vehicles.add(v);
        }
        public void removeVehicle(Vehicle v){
            vehicles.remove(v);
        }

        public void OccupySlot(Floor f,Slot s,Vehicle v){
                if(s!=null && s.getIsAvailable()){
                    s.markUnavailable();
                    s.assignvehicle(v);
                    System.out.println("Assigned vehicle "+ v+" to floor "+ f+" and slot"+ s);
                }
                else{
                    System.out.println("slot is occupied");
                }
        
        }

        public void markSlotUnoccupied(Floor f,Slot s){
                s.markAvailable(); 
                System.out.println(" floor and slot freed "+ f +s );
            
        }

        public  void PayforRent(VehicleType type,int hrs,Payment payment){
            int amt=type.getValue()*hrs;
            payment.pay(amt);
        }


    }

    static class Slot{
         private int slotNo;
         private Vehicle vehicleAssigned;
         private boolean isAvailable;
         Slot(int slotNo){
            this.slotNo=slotNo;
            this.isAvailable=true;
         }

         public int getSlotNo(){
            return slotNo;
         }

         public void assignvehicle(Vehicle v){
            vehicleAssigned=v;
         }

         public void markUnavailable(){
            this.isAvailable=false;
         }

         public void markAvailable(){
            this.isAvailable=true;
            vehicleAssigned=null;
         }

         public boolean getIsAvailable(){
         return isAvailable;
         }

         public Vehicle getAssignedVehicle(){
              return vehicleAssigned;
         }

         @Override
        public String toString(){
            return  ""+ slotNo;
        }

         
    }

    static class Floor{
        ArrayList<Slot> slots=new ArrayList<>();
        private int floorNo;
        private int noOfSlots;
        Floor(int floorNo,int noOfSlots){
            this.floorNo=floorNo;
            this.noOfSlots=noOfSlots;
          for(int i=0;i<noOfSlots;i++){
            slots.add(new Slot(i));
          }
        }

        public Slot getFirstAvailableSlot(){
            for(Slot s:slots){
                 if(s.getIsAvailable()){
                    return s;
                 }
            }
            System.out.println("No slots are empty rught now");
            return null;
        }
        
        public int getNoOfSlots(){
            return noOfSlots;
        }
        public int getfloorNo(){
            return floorNo;
        }

        @Override
        public String toString(){
            return "floorNo "+ floorNo;
        }
    }

  static  class ParkingLot{
         ArrayList<Floor> floors=new ArrayList<>();
        private int noOfFloors;
        ParkingLot(){
        }
        public int getNoOfFloors(){
            return noOfFloors;
        }

        public void addFloor(Floor f){
            floors.add(f);
        }

        public void removeFloor(Floor f){
            floors.remove(f);
        }

    }

    interface Payment{
          void pay(int amt);
    }

     static class UpiPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using upi:"+amt);
        }
    }

     static class CreditCardPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using CreditCard:"+amt);
        }
    }

   static class CashPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using Cash:"+amt);
        }
    }

    static class PaymentFactory{
        public Payment choosePayment(String paymentname){
            if(paymentname.equalsIgnoreCase("upi")){
                return new UpiPayment();
            }
           else if(paymentname.equalsIgnoreCase("creditcard")){
            return new CreditCardPayment();
           }
           else{
                return new CashPayment();
            }

        }
    }

   static  class Vehicle{
       private VehicleType vehicletype;
       private String numberPlate;
        Vehicle(VehicleType vehicletype,String numberPlate){
            this.vehicletype=vehicletype;
            this.numberPlate=numberPlate;
        }
        public VehicleType getVehicleType(){
             return vehicletype;
          }

        public String getNumberPlate(){
            return numberPlate;
        }

        @Override
        public String toString(){
            return vehicletype+"["+ numberPlate+"]";
        }
    }

    enum VehicleType{
        Car(50),Scooty(30),Bike(35),Other(40);
        private int val;
         VehicleType(int val){
            this.val=val;
        }

        public int getValue(){
            return val;
        }
    }
    public static void main(String[] args) {
       ParkingLotSystemManager manager=ParkingLotSystemManager.getInstance();
       Vehicle v1=new Vehicle(VehicleType.Car, "AP2123");
       Vehicle v2=new Vehicle(VehicleType.Scooty, "AP2139");
       ParkingLot p1=new ParkingLot();
       Floor f1=new Floor(2, 21);
       Floor f2=new Floor(1, 21);
       p1.addFloor(f2);
       p1.addFloor(f1);
    Slot s1=f1.getFirstAvailableSlot();
      manager.OccupySlot(f1,s1,v1);
     Slot s2=f1.getFirstAvailableSlot();
     PaymentFactory ptype=new PaymentFactory();
     Payment payment=ptype.choosePayment("upi");
     manager.PayforRent(v1.getVehicleType(), 3,payment);
     manager.OccupySlot(f1,s2,v2);
       
    }
}
