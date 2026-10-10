import java.util.*;

enum BookStatus {
    AVAILABLE, RESERVED, BORROWED
}

class Member {
    private String memberId;
    private String name;
    private int activeReservationsCount = 0;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public int getActiveReservationsCount() { return activeReservationsCount; }

    public void incrementReservations() { activeReservationsCount++; }
    public void decrementReservations() { activeReservationsCount--; }
}

class Book {
    private String isbn;
    private String title;
    private BookStatus status;
    private Queue<Member> reservationQueue = new LinkedList<>();

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.status = BookStatus.AVAILABLE;
    }

    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public BookStatus getStatus() { return status; }

    public boolean reserve(Member member) {
        if (member.getActiveReservationsCount() >= 3) {
            System.out.println("Reservation failed: Member " + member.getName() + " has reached max limit of 3 active reservations.");
            return false;
        }

        if (reservationQueue.contains(member)) {
            System.out.println("Reservation failed: Member " + member.getName() + " is already in the queue for '" + title + "'.");
            return false;
        }

        reservationQueue.add(member);
        member.incrementReservations();
        status = BookStatus.RESERVED;
        System.out.println("Book '" + title + "' reserved for " + member.getName() + " (Position: " + reservationQueue.size() + ").");
        return true;
    }

    public void checkout(Member member) {
        if (status == BookStatus.AVAILABLE) {
            status = BookStatus.BORROWED;
            System.out.println("Book '" + title + "' checked out to " + member.getName() + ".");
        } else if (status == BookStatus.RESERVED) {
            if (!reservationQueue.isEmpty() && reservationQueue.peek().equals(member)) {
                reservationQueue.poll();
                member.decrementReservations();
                status = BookStatus.BORROWED;
                System.out.println("Book '" + title + "' checked out to reserved member " + member.getName() + ".");
            } else {
                System.out.println("Checkout failed: '" + title + "' is reserved for another member.");
            }
        } else {
            System.out.println("Checkout failed: '" + title + "' is currently BORROWED.");
        }
    }

    public void returnBook() {
        if (reservationQueue.isEmpty()) {
            status = BookStatus.AVAILABLE;
            System.out.println("Book '" + title + "' returned and is now AVAILABLE.");
        } else {
            status = BookStatus.RESERVED;
            Member nextInLine = reservationQueue.peek();
            System.out.println("Book '" + title + "' returned and is now RESERVED for next member: " + nextInLine.getName() + ".");
        }
    }
}

public class Question5Main {
    public static void main(String[] args) {
        Book b1 = new Book("978-0134685991", "Effective Java");

        Member m1 = new Member("M101", "Alice");
        Member m2 = new Member("M102", "Bob");

        b1.reserve(m1);
        b1.reserve(m2);

        b1.checkout(m2);
        b1.checkout(m1);

        b1.returnBook();
        b1.checkout(m2);
    }
}
