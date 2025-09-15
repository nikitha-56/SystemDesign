// package ElevatorManagementSystem;
import java.util.*;
public class ElevatorManagementSystem {
    static class Building{
        private int noOfLifts;
        private int noOfFloors;
       private List<Lift>lifts=new ArrayList<>();
        private List<Floor>floors=new ArrayList<>();
       Building(int noOfFloors,int noOfLifts){
           this.noOfFloors=noOfFloors;
           this.noOfLifts=noOfLifts;
           for(int i=0;i<=noOfFloors;i++){
            floors.add(new Floor(i));
           }
       }

       public List<Floor> getallFloors(){
        return floors;
       }
       public int getNoOfFloors(){
        return noOfFloors;
       }
    public int getNoOfLifts(){
        return noOfLifts;
    }
    public List<Lift> getAllLifts(){
        return lifts;
    }

    public void addLift(Lift l){
        lifts.add(l);
    }
    public void removeLift(Lift l){
        lifts.remove(l);
    }

    }

    static class Floor{
        private int floorNo;
       private Button button;
       private boolean present;
       Floor(int floorNo){
        this.floorNo=floorNo;
        this.button=Button.NOTHINGPRESSED;
        present=true;
       }

       public int getFloorNo(){
        return floorNo;
       }
       public Button getButtonStatus(){
        return button;
       }

       public boolean isFloorAvailable(){
        return present;
       }

       public void updateStatus(Button status){
        button=status;
       }
    }
    enum LiftStatus{
        RUNNING,IDLE,MAINTANCE;
    }

    enum DoorStatus{
        CLOSED,OPEN,STRUCK;
    }
    static class Lift{
        List<Floor> floors=new ArrayList<>();
        private int id;
        private int totalCapacity;
        private int currCapacity;
        DoorStatus dstatus;
        LiftStatus lstatus;
        private int noOfFloors;
        private int currLiftisAt;
        Lift(int id,int capacity,int noOfFloors){
            this.id=id;
            this.totalCapacity=capacity;
            this.noOfFloors=noOfFloors;
            this.currLiftisAt=0;
            this.lstatus=LiftStatus.IDLE;
        }

        public int getCurrLiftisAt(){return currLiftisAt;}
        public int fetTotalCapacity(){return totalCapacity;}
        public int currCapacity(){return currCapacity;}
         public int getId(){return id;}
         public LiftStatus getStatus() { return lstatus; }
    public DoorStatus getDoorStatus() { return dstatus; }
        public void openDoor(){
            dstatus=DoorStatus.OPEN;
            System.out.println("lift "+id + "opened the door at floor "+currLiftisAt);
        }
        public void closeDoor(){
            dstatus=DoorStatus.CLOSED;
            System.out.println("DOOR CLOSED");
        }
        public void moveToFloor(int floor){
            System.out.println("Lifting is moving from "+currLiftisAt+" to "+ floor);
            lstatus=LiftStatus.RUNNING;
             currLiftisAt=floor;
             lstatus=LiftStatus.IDLE;
             System.out.println("Lift arrived at floor "+ floor );
             openDoor();
        }

        // public void notifyWhenUserPressed(int f){
        //     for(int i=1;i<=noOfFloors;i++){
        //         if(f==i){
        //             System.out.println(f+" floor ");
        //             dstatus=DoorStatus.OPEN;
        //             lstatus=lstatus.RUNNING;
        //             System.out.println("door status :"+ dstatus);
        //         }
        //     }
        // }

      public boolean canEnter(User u){
        return totalCapacity>=u.wt+currCapacity;
      }
      public void enter(User u){
        if(canEnter(u)) currCapacity+=u.getWt();
        else System.out.println("Lift Overloaded");
      }

      public void exit(User u){
        currCapacity-=u.getWt();
      }

        // public void updateDoorStatus(DoorStatus s){
        //     dstatus=s;
        //     System.out.println("door status: "+ dstatus);
        // }

        // public void updateLift(Button status){
        //     if(status==Button.UP){
        //         System.out.println("Lift is going up |");
        //     }
        //     else if(status==Button.DOWN){
        //         System.out.println("Lift is going down ");
        //     }
        // }

    }
     static class User{
        private int wt;
        User(int wt){
            this.wt=wt;
        }
        public int getWt(){
            return wt;
        }

    }
    static class LiftInternalOperations{
        int noOfFloors;
     
      LiftInternalOperations(int noOfFloors){
        this.noOfFloors=noOfFloors;
      }
      public void chooseFloor(Lift l,int f){
        if(f<0 || f>noOfFloors){
            System.out.println("Floor "+ f+" doesnt exist");
            return;
        }

        l.closeDoor();
        l.moveToFloor(f);
      }

      public void letUserEnter(Lift lift,User user){
        lift.enter(user);

      }

      public void letUserExit(Lift lift,User user){
        lift.exit(user);

      }

    }

     enum Button{
        UP,DOWN,NOTHINGPRESSED;
    }
    static class LiftExternalOpearations{
           LiftScheduler scheduler;
        LiftExternalOpearations(LiftScheduler scheduler){
            this.scheduler=scheduler;
        }

        public void pressButton(Button s,int f){
         System.out.println("Button pressed at floor"+f +"for "+ s);
         Lift lift=scheduler.assignLiftToUser(f);
         if(lift!=null){
            System.out.println("Lift "+lift.getId()+"will serve the request");
         }
            }
        }

    static public class LiftScheduler{
       List<Lift> lifts;
       LiftScheduler(){
        this.lifts=new ArrayList<>();
       }

        public void addLift(Lift l) {
        lifts.add(l);
    }

       public  Lift assignLiftToUser(int f){
        int min=Integer.MAX_VALUE;
        Lift selectedLift=null;
        for(Lift l:lifts){
            int diff=Math.abs(l.getCurrLiftisAt()-f);
           if(diff<min){
            min=diff;
            System.out.println("Assigned lift"+l.getId());
            selectedLift=l;
           } 
        }

        if(selectedLift!=null){
            System.out.println("Assinged lift "+selectedLift.getId()+ "to floor"+ f);
            selectedLift.moveToFloor(f);
            selectedLift.openDoor();
        }
        else{
            System.out.println("No idle lift available at this moemnt");
        }
        return selectedLift;
        
       }
    }

    public static void main(String[] args) {
        Building b1=new Building(20, 5);
        Lift l1=new Lift(1, 100,3);
         Lift l2=new Lift(2, 45,3);
        LiftInternalOperations interop=new LiftInternalOperations(5);
          LiftScheduler ls=new LiftScheduler();
         ls.addLift(l2);
         ls.addLift(l1);
        LiftExternalOpearations exterop=new LiftExternalOpearations(ls);
         User u1=new User(67);
         User u2=new User(30);
        b1.addLift(l1);
        b1.addLift(l2);
        exterop.pressButton(Button.UP, 2);
       

    }
}

