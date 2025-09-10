// package LibraryManagementSystem;
import java.time.LocalDateTime;
import java.time.format.SignStyle;
import java.util.*;
import java.util.ArrayList;

public class LibraryManagementSystem {
    static class User{
        private String name;
        private String regdNo;
        private List<Book> borrowedBooks=new ArrayList<>();

        User(String name,String regdNo){
            this.name=name;
            this.regdNo=regdNo;
        }

        @Override
        public String toString(){
            return name+"with regd no "+ regdNo;
        }

        public String getName(){
            return name;
        }
        public String getRegdNo(){
            return regdNo;
        }

        public void getBorrowedBooks(){
           for(Book b:borrowedBooks){
            System.out.print(b+" ");
           }
        }
    }

    static class Book{
        private String bookName;
        private int bookId;
        private String dept;
        private int noOfBooks;
        Book(String bookName,int bookId,String dept,int noOfBooks){
            this.bookId=bookId;
            this.bookName=bookName;
            this.dept=dept;
            this.noOfBooks=noOfBooks;
        }

        @Override
        public String toString(){
            return bookName+"with bookid no "+ bookId;
        }

        public int getBookId(){
               return bookId;
        }
        public String getBookName(){
            return bookName;
        }
        public String getDept(){
            return dept;
        }

        public int noOfAvailableBooks(){
            return noOfBooks;
        }
        public boolean isBookAvailable(){
            if(noOfBooks>0){
                return true;
            }
            else return false;
        }

        public void incrementCnt(){
            noOfBooks++;
        }

        public void decrementCnt(){
            noOfBooks--;
        }

    }

    interface Observer{
        void notify(String msg);
    }
    static class notifyLibraryManager implements Observer{
        public void notify(String msg){
            System.err.println("Notification to LibraryManager "+ msg);
        }
    }

     static class notifyUser implements Observer{
        public void notify(String msg){
            System.err.println("Notification to User "+ msg);
        }
    }
    static class LibraryManager{
        private static LibraryManager instance;
        List<Transaction> transactions=new ArrayList<>();
        List<Observer> observers=new ArrayList<>();
        private LibraryManager(){}
        public static LibraryManager getInstance(){
            if(instance==null){
              instance=new LibraryManager();
            }
            return instance;
        }

        public void addObserver(Observer o){
            observers.add(o);
        }

        public void removeObserver(Observer o){
            observers.remove(o);
        }

         public void borrowBook(User u,Book b){
            if(!b.isBookAvailable()){
                System.out.println("Book not available");
                return;
            }
            b.decrementCnt();
            u.borrowedBooks.add(b);
            Transaction t1=new Transaction(u, b,status.BORROWED);
              for(Observer o:observers){
               o.notify("Boorrowed the book :"+ b+ "by user :"+ u);
               System.out.println();
              }
            transactions.add(t1);
        }
        public void returnBook(User u,Book b){
            if(!u.borrowedBooks.contains(b)){
                System.out.println("User didnt borrowed this book");
                return;
            }
            b.incrementCnt();
             Transaction t2=new Transaction(u, b,status.RETURNED);
             transactions.add(t2);
           for(Observer o:observers){
               o.notify("returned the book :"+ b+ "by user :"+ b);
               System.out.println();
              }
        }

        public void getAllTransactions(){
             for(Transaction t:transactions){
                System.out.print(t);
             }
        }


    }

    enum status{
        BORROWED,RETURNED;
    }

    static class Transaction{
       private String tid;
       User user;
       Book book;
      private status transStatus;
      private LocalDateTime borrowedDate;
      private LocalDateTime returnedDate;

      
      Transaction(User u,Book b,status transStatus){
        this.tid=UUID.randomUUID().toString();
        this.user=u;
        this.book=b;
        this.transStatus=transStatus;
        if(transStatus==status.BORROWED){
            borrowedDate=LocalDateTime.now();
        }
        else{
            returnedDate=LocalDateTime.now();
        }

    }
        @Override
         public String toString(){
           return " transcationid :"+ tid + "user :"+ user+ "book :"+ book;
        }

      public String gettid(){
        return tid;
      }
      public status getStatus(){
        return transStatus;
      }

      public LocalDateTime getBorrowedDate(){
        return borrowedDate;
      }

      public LocalDateTime getReturnedDate(){
        return returnedDate;
      }

    }

    static class Library{
        List<User> users=new ArrayList<>();
        List<Book> books=new ArrayList<>();
        public void addUser(User u){
            users.add(u);
        }

         public void removeUser(User u){
            users.remove(u);
        }

        public void addBook(Book b){
            books.add(b);
        }

        public void removeBook(Book b){
            books.remove(b);
        }

        public void getallBooks(){
            for(Book b:books){
                System.out.print(b+" ");
            }
        }

        public void getAllUsers(){
            for(User u:users){
                System.out.println(u+" ");
            }
        }
    }

    public static void main(String[] args) {
        User u1=new User("nikki", "22bcb7090");
        User u2=new User("cherry","22mis7101");
        Book b1=new Book("c",21,"cse",10);
         Book b2=new Book("cpp",15,"cse",11);
         Library vitap=new Library();
         
         LibraryManagementSystem lms=new LibraryManagementSystem();
         LibraryManager manager=LibraryManager.getInstance();
          Observer o1=new notifyLibraryManager();
     Observer o2=new notifyUser();
     manager.addObserver(o2);
     manager.addObserver(o1);
        vitap.addBook(b2);
        vitap.addBook(b1);
        vitap.addUser(u2);
        vitap.addUser(u1);
      System.out.println( b1.noOfAvailableBooks());
        manager.borrowBook(u2, b2);
        manager.borrowBook(u2, b1);
        manager.returnBook(u2, b2);
     manager.getAllTransactions();
    }

}
