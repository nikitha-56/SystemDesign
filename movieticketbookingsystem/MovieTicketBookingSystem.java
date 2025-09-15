// package movieticketbookingsystem;

import java.time.*;
import java.util.*;

public class MovieTicketBookingSystem {
  static  class Ticket{
        private int ticketId;
        private String movieName;
        private float price;
        private LocalDateTime entryTime;
        private LocalDateTime expiryTime;
        Ticket(int ticketId,String movieName,int price,LocalDateTime entryTime,LocalDateTime expiryTime){
            this.ticketId=ticketId;
            this.movieName=movieName;
            this.price=price;
            this.entryTime=entryTime;
            this.expiryTime=expiryTime;
        }
        public int getTicketId(){
            return ticketId;
        }
        public String getMovieName(){
            return movieName;
        }
        public float getPrice(){
            return price;
        }
        public LocalDateTime getEntryTime(){
            return entryTime;
        }
        public LocalDateTime getExpiryTime(){
            return expiryTime;
        }
    }

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

    static class Theatre{
         List<Screen> screens=new ArrayList<>();
        private String theatreName;
        private int noOfScreens;
        private boolean available;
        Theatre(String theatreName){
            this.theatreName=theatreName;
            this.available=true;
            System.out.println("New theatre "+ theatreName+"is created");
        }

        public boolean isAvailable(){
            return available;
        }

        public void addScreen(Screen s){
            screens.add(s);
        }
        public void removeScreen(Screen s){
            screens.remove(s);
        } 

        public int getNoOfScreens(){
            return noOfScreens;
        }
        public String getTheatreName(){
            return theatreName;
        }
        public void updateScreenCnt(){
            noOfScreens++;
        }

        public void decrementScreenCnt(){
            noOfScreens--;
        }
        public void getAllScreens(){
            for(Screen s:screens){
                System.out.println(s.getScreenId());
            }
        }
    }

   static class Screen{
        List<Seat> seats=new ArrayList<>();
        private int screeId;
        private boolean occupied;
        private int noOfSeats;
        private Movie currMoviePlaying;
        Screen(int screeId,int noOfSeats){
            this.screeId=screeId;
            occupied=false;
            this.noOfSeats=noOfSeats;
            System.out.println("New screen "+screeId+" is created");
            for(int i=1;i<=noOfSeats;i++){
                System.out.println("new seat with seat no :"+i+"created");
                Seat s=new Seat(i,seatStatus.AVAILABLE);
                seats.add(s);
            }
        }

        public Movie getcurrMoviePlaying(){
            return currMoviePlaying;
        }
        

        public void getCurrMoviePlaying(){
            System.out.println(currMoviePlaying);
        }

        public void updateCurrMoviePlaying(Movie newName){
            currMoviePlaying=newName;
        }

         public int getPrice(){
            return currMoviePlaying.getPriceOfMovie();
        }
        public void allocateMoiveToScreen(Movie m){
               currMoviePlaying=m;
        }

        public int getNoOfSeats(){
            return noOfSeats;
        }
        public int getScreenId(){
            return screeId;
        }


        public boolean isOccupied(){
              return occupied;
        }

        public void updateStatus(){
            occupied=true;
        }
        public Seat getSeat(int sno){
            for(Seat s:seats){
                if(s.seatNo==sno){
                    return s;
                }
            }
            return null;
        }
    }

    static class Movie{
        private String movieName;
        private int priceOfTicket;
        Movie(String movieName,int priceOfTicket){
            this.movieName=movieName;
            this.priceOfTicket=priceOfTicket;
        }

        public String getmovieName(){
            return movieName;
        }
        public int getPriceOfMovie(){
            return priceOfTicket;
        }
    }

    enum seatStatus{
        AVAILABLE,BOOKED,BLOCKED
    }

   static class Seat{
        private int seatNo;
        private seatStatus status;
        Seat(int seatNo,seatStatus status){
            this.seatNo=seatNo;
            this.status=status;
        }
        public int getSeatNo(){
            return seatNo;
        }
        public seatStatus getSeatStatus(){
                 return status;
        }

        public void updateStatus(seatStatus nstatus){
            status=nstatus;
        }


    }
    interface Observer{
         void notify(String msg);
    }

    static class notificationToUser implements Observer{
        public void notify(String msg){
            System.out.println("Notification to User :"+ msg);
        }
    }

     static class notificationToMtmimplements implements Observer{
        public void notify(String msg){
            System.out.println("Notification to movie ticket manager :"+ msg);
        }
    }

  static  class MovieTicketManager{
        private static MovieTicketManager instance;
        List<Theatre> theatres=new ArrayList<>();
        List<Observer> observers=new ArrayList<>();
        MovieTicketManager(){}
        public static  MovieTicketManager getInstance(){
            if(instance==null){
            instance=new MovieTicketManager();
            }
            return instance;
        }

        public void addTheatre(Theatre t){
            theatres.add(t);
        }

        public void removeTheatre(Theatre t){
            theatres.remove(t);
        }

        public void addScreen(Theatre t,Screen s){
            t.addScreen(s);
        }

        public void removeScreen(Theatre t,Screen s){
            t.removeScreen(s);
        }

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

        public void allocateMovieToScreen(Movie m,Screen s){
            s.updateCurrMoviePlaying(m);
            s.updateStatus();
        }
    }
    interface Payment{
        void pay(int amt);
    }

    static class UpiPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using upi: "+amt);
        }
    }

    static class CreditCardPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using CreditCard: "+amt);
        }
    }

    static class CashPayment implements Payment{
        public void pay(int amt){
            System.out.println("Paid using cash: "+amt);
        }
    }

    static class PaymentFactory{
        public  Payment createPayment(String type){
            if(type.equalsIgnoreCase("upi")){
                return new UpiPayment();
            }
            else if(type.equalsIgnoreCase("creditcard")){
                return new CreditCardPayment();
            }
            else if(type.equalsIgnoreCase("cash")){
                return new CashPayment();
            }
            else{
                return null;
            }
        }
    }

   static class Booking{
    static int ticketCounter=1;
    Booking(){}

        public int bookTicket(Theatre t,Screen s,Seat sno){
              if(t.isAvailable() && s.isOccupied() && sno.getSeatStatus()==seatStatus.AVAILABLE){
                int amt=s.getPrice();
                sno.updateStatus(seatStatus.BLOCKED);
                     Ticket ticket=new Ticket(ticketCounter++, s.getcurrMoviePlaying().getmovieName(),s.getPrice(), LocalDateTime.now(),LocalDateTime.now().plusHours(4));
                 return amt; 
              }
              else{
                System.out.println("Theatre,screen or seat not available");
                return -1;
              }
        }

        public String updateDetails(Theatre t,Screen s,Seat sno){
            sno.updateStatus(seatStatus.BOOKED);
            return "Ticket booked Successfully ,Theatre "+t.getTheatreName()+"screeen "+s.getScreenId()+" seat : "+sno.getSeatNo();
        }
        
    }

    public static void main(String[] args) {
        MovieTicketManager manager=MovieTicketManager.getInstance();
         Theatre t1=new Theatre("sandhya");
        Theatre t2=new Theatre("rtcx roads");
        Screen s1=new Screen(1, 21);
        Screen s2=new Screen(2, 25);
        Movie og=new Movie("og", 300);
         Movie geethagovindam=new Movie("geethagovindam", 300);
        t1.addScreen(s2);
        t1.addScreen(s1);
          Observer o1=new notificationToMtmimplements();
     Observer o2=new notificationToUser();
     manager.addObserver(o2);
     manager.addObserver(o1);
     manager.allocateMovieToScreen(og, s2);
     manager.allocateMovieToScreen(geethagovindam, s1);
     t1.getAllScreens();
        Booking booking=new Booking();
        Seat sno1=s2.getSeat(16);
     int amt=booking.bookTicket(t1, s2, sno1);
     PaymentFactory payment=new PaymentFactory();
     if(amt!=-1){
Payment p1=payment.createPayment("cash");
if(p1!=null){
         p1.pay(amt);
       String msg= booking.updateDetails(t1,s2,sno1);
       manager.notifyAll(msg);
     }

    }
}
}
