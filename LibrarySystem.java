package displayoutput;
import java.util.*;

public class LibrarySystem {

    static Scanner sc = new Scanner(System.in);

    static String[] titles = new String[100];
    static String[] authors = new String[100];
    static String[] isbns = new String[100];
    static int[] quantities = new int[100];
    static double[] prices = new double[100];
    static int booksCount = 0;

    static String[] memberNames = new String[100];
    static String[] memberIds = new String[100];
    static String[] phones = new String[100];
    static int membersCount = 0;

    static String[] recordIsbn = new String[100];
    static String[] recordMember = new String[100];
    static boolean[] returned = new boolean[100];
    static int recordsCount = 0;

    static double totalValue(int n) {
        if (n == 0) return 0;
        return (prices[n - 1] * quantities[n - 1]) + totalValue(n - 1);
    }

    static void addBook() {
        sc.nextLine();
        System.out.print("Title: ");
        titles[booksCount] = sc.nextLine();
        System.out.print("Author: ");
        authors[booksCount] = sc.nextLine();
        System.out.print("ISBN: ");
        isbns[booksCount] = sc.nextLine();
        System.out.print("Quantity: ");
        quantities[booksCount] = sc.nextInt();
        System.out.print("Price: ");
        prices[booksCount] = sc.nextDouble();
        booksCount++;
    }

    static void addMember() {
        sc.nextLine();
        System.out.print("Name: ");
        memberNames[membersCount] = sc.nextLine();
        System.out.print("ID: ");
        memberIds[membersCount] = sc.nextLine();
        System.out.print("Phone: ");
        phones[membersCount] = sc.nextLine();
        membersCount++;
    }

    static void borrowBook() {
        System.out.print("Member ID: ");
        String mid = sc.next();

        boolean memberFound = false;
        for (int i = 0; i < membersCount; i++)
            if (memberIds[i].equals(mid))
                memberFound = true;

        if (!memberFound) {
            System.out.println("Member not found");
            return;
        }

        System.out.print("ISBN: ");
        String isbn = sc.next();

        int index = -1;
        for (int i = 0; i < booksCount; i++)
            if (isbns[i].equals(isbn))
                index = i;

        if (index == -1 || quantities[index] <= 0) {
            System.out.println("Book unavailable");
            return;
        }

        recordIsbn[recordsCount] = isbn;
        recordMember[recordsCount] = mid;
        returned[recordsCount] = false;
        recordsCount++;

        quantities[index]--;
    }

    static void returnBook() {
        System.out.print("ISBN: ");
        String isbn = sc.next();

        for (int i = 0; i < recordsCount; i++) {
            if (recordIsbn[i].equals(isbn) && !returned[i]) {
                returned[i] = true;
                for (int j = 0; j < booksCount; j++)
                    if (isbns[j].equals(isbn))
                        quantities[j]++;
                return;
            }
        }
        System.out.println("Record not found");
    }

    static void searchBook() {
        sc.nextLine();
        System.out.print("Keyword: ");
        String key = sc.nextLine();

        for (int i =0; i<booksCount;i++){
            if (titles[i].equals(key)|| authors[i].equals(key)|| isbns[i].equals(key)){
                System.out.println(titles[i]+"|"+authors[i]+"| Qty:"+quantities[i]);
                return;
            }
        }
        System.out.println("Not found");
    }

    static void showBooks() {
        for (int i = 0; i < booksCount; i++)
            System.out.println(titles[i] + " | " + authors[i] + " | Qty: " + quantities[i]);
    }

    static void showBorrowed() {
        for (int i = 0; i < recordsCount; i++)
            if (!returned[i])
                System.out.println("ISBN: " + recordIsbn[i] + " | Member: " + recordMember[i]);
    }

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n1 Add Book");
            System.out.println("2 Add Member");
            System.out.println("3 Borrow");
            System.out.println("4 Return");
            System.out.println("5 Search");
            System.out.println("6 Show Books");
            System.out.println("7 Show Borrowed");
            System.out.println("8 Total Value");
            System.out.println("0 Exit");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    addMember();
                    break;

                case 3:
                    borrowBook();
                    break;

                case 4:
                    returnBook();
                    break;

                case 5:
                    searchBook();
                    break;

                case 6:
                    showBooks();
                    break;

                case 7:
                    showBorrowed();
                    break;

                case 8:
                    System.out.println("Total = " + totalValue(booksCount));
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);
    }
}