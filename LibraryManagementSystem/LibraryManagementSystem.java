package LibraryManagementSystem;
import java.time.LocalDateTime;
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

        public void borrowBook(Book b){
            b.decrementCnt();
            System.out.println("Borrowed book :"+ b);
        }
        public void returnBook(Book b){
            b.incrementCnt();
            System.out.println("Returned book :"+b);
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

        public int getBookId(int id){
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

    static class LibraryManager{
        private static LibraryManager instance;
        List<Transaction> transactions=new ArrayList<>();
        private LibraryManager(){}
        public static LibraryManager getInstance(){
            if(instance==null){
              instance=new LibraryManager();
            }
            return instance;
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
        this.user=u;
        this.book=b;
        this.transStatus=transStatus;
        if(transStatus.equals("borrowed")){
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
        vitap.addBook(b2);
        vitap.addBook(b1);
        vitap.addUser(u2);
        vitap.addUser(u1);
       u1.borrowBook(b2);
       u1.borrowBook(b1);
      System.out.println( b1.noOfAvailableBooks());
       Transaction t1=new Transaction(u1, b2, status.BORROWED);
       System.out.println(t1.getBorrowedDate());
      u1.returnBook(b2);
     Transaction t2=new Transaction(u1, b2, status.RETURNED);
      System.out.println(t1.getReturnedDate());
     manager.getAllTransactions();

    }

}
