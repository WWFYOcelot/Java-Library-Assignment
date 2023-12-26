package Assignment1;

import java.util.*;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Patron {
	
	// fields
	private String id;
	private String name;
	private int age;
	private List<Book> borrows;
	private float fees = 0;
	
	// constructor
	public Patron(String id, String name, int age, List<Book> borrows) {
		this.age = age;
		this.name = name;
		this.id = id;
		if (borrows == null) {
			this.borrows = new ArrayList<Book>();
		}
		else this.borrows = borrows;
	}
	
	// minimal info constructor for convenience; only used for testing
	public Patron(String name) {
		this.name = name;
		this.borrows = new ArrayList<Book>();
	}
	
	// default constructor
	public Patron() {
		this.id = null;
		this.name = null;
		this.age = -1;
		this.borrows = new ArrayList<Book>();
	}

	// getters and setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Book> getBorrows() {
		return borrows;
	}

	public void setBorrows(List<Book> borrows) {
		this.borrows = borrows;
	}
	
	public float getFees() {
		return fees;
	}

	public void setFees(float fees) {
		this.fees = fees;
	}

	// borrow a book
	public int borrowBook(Book book) {
		// creates an empty ArrayList to store books if the patron doesn't already have a borrows list
		if (this.getBorrows() == null) {
			ArrayList<Book> books = new ArrayList<Book>();
			this.setBorrows(books);
		}
		
		// checks if book is available
		if (book.getAvail() == true) {
			// create LocalDate object, Scanner
			LocalDate borrowDate = null;
			// get date borrowed
			// try/catch ensures that the date is entered in the correct format
			while (true) {
				System.out.println("Enter the borrow date (YYYY-MM-DD):");
				String borrow = Main.getString();
				try {
					// uses parse method to turn the correctly formatted string into a LocalDate object
					borrowDate = LocalDate.parse(borrow);
				}
				// if the date is invalid, a message is printed, and the loop continues, allowing the user to try again
				catch (DateTimeParseException e) {
					System.out.println("Invalid date. Please re-enter the date.");
					continue;
				}
				// when a valid input is entered, the loop is broken
				break;
			}
			// the book's borrow and return dates are set, with the return date being 2 weeks after the borrow date
			// return date is found using a LocalDate method
			book.setBorrowDate(borrowDate);
			book.setDueDate(borrowDate.plusWeeks(2));
			// availability is set to false
			book.setAvail(false);
			// book is added to patron's list of borrowed books
			this.borrows.add(book);
			// return value is used to identify successful execution, i.e. book added to borrows list
			return 0;
		}
		// if the book is unavailable
		else {
			// message printed
			System.out.format("\n%s is not available.\n", book.getTitle());
			// checks to see if the book has a return date
			if (book.getDueDate() != null) {
				// if the book has a return date, this date is printed to the user
				System.out.format("%s has a return date of %s.\n", book.getTitle(), book.getDueDate().toString());
			}
			// return value is used to identify an unsuccessful execution i.e. book not added to borrows list
			return -1;
		}
	}
	
	public int returnBook(Book book) {
		// checks whether or not patron's borrowed book list contains a book
		if (this.getBorrows().contains(book)) {
			// create LocateDate, Scanner objects
			LocalDate returnDate = null;
			// prompts user for a return date, ensures a valid input is entered
			// same method is used as in borrowBook() to get a date
			while (true) {
				System.out.println("Enter the return date (YYYY-MM-DD):");
				String returnStr = Main.getString();
				try {
					returnDate = LocalDate.parse(returnStr);
				}
				catch (DateTimeParseException e) {
					System.out.println("Invalid date. Please re-enter the date.");
					continue;
				}
				if (returnDate.isBefore(book.getBorrowDate())) {
					System.out.println("A book can't be returned before it is borrowed. Please re-enter the return date.");
					continue;
				}
				break;
			}
			// if the book is overdue when returned, a message is printed
			if (returnDate.isAfter(book.getDueDate())) {
				System.out.println("Book is overdue!");
				// uses ChronoUnit object method to find the difference in days between the date the book is returned and the due date 
				long daysOverDue = ChronoUnit.DAYS.between(book.getDueDate(), returnDate);
				// the number of days is multiplied by 0.25, i.e. a fee of $0.25/day
				float currFee = (float) (daysOverDue * 0.25);
				// fee is printed to the user
				System.out.format("You owe $%.2f in overdue fees on this return.\n", currFee);
				// gives user option to pay fees immediately or later
				// if the user has pending fees in their account and chooses to pay now, they are also given the option to pay those fees immediately, 
				//	or leave them on their account
				while (true) {
					System.out.println("Would you like to pay your fees now, or add them to your account?");
					System.out.println("1. Pay now");
					System.out.println("2. Pay later");
					int feeSelect = Main.getInt();
					if (feeSelect == 1) {
						if (this.getFees() > currFee) {
							System.out.println("You have other fees in your account. Would you like to pay them now as well or later?");
							while (true) {
								System.out.println("1. Pay other fees now");
								System.out.println("2. Pay other fees later");
								int otherFeeSelect = Main.getInt();
								if (otherFeeSelect == 1) {
									System.out.println("Fees paid.");
									this.setFees(0.00f);
									break;
								}
								else if (otherFeeSelect == 2) {
									System.out.format("\nFees paid.\nFees remaining in your account: $%.2f\n", this.fees);
									break;
								}
								else {
									System.out.println("Invalid selection. Please try again.");
									continue;
								}
							}
						}
						else {
							System.out.println("Fees paid.");
						}
						break;
					}
					else if (feeSelect == 2) {
						this.fees += currFee;
						System.out.format("$%.2f added to your account. Your account now owes: $%.2f", currFee, this.fees);
						break;
					}
					else {
						System.out.println("Invalid selection. Please try again.");
						continue;
					}
				}
			}
			// book is set to available, borrow/return dates are set to null
			book.setAvail(true);
			book.setBorrowDate(null);
			book.setDueDate(null);
			// book is removed from borrows list
			this.borrows.remove(book);
			// return value is used to identify successful execution, i.e. book was returned to library
			return 0;
		}
		// if the book is not in the book list, a message is printed to the user
		// the function also returns a failure
		else {
			System.out.format("\nYou do not have %s.\n", book.getTitle());
			return -1;
		}
	}
	
	// iterates through borrowed books list, prints each one's name & due date
	public void printBooks() {
		System.out.format("%s's Borrowed Books: \n", this.getName());
		for (int i = 0; i < this.borrows.size(); i++) {
			System.out.format("%d. %s - %s\n", i+1, borrows.get(i).getTitle(), borrows.get(i).getDueDate().toString());
		}
		System.out.println();
	}
	
	// display data
	public void displayPatronData() {
		System.out.format("Patron ID: \t%s\n\tName:\t%s\n\tAge:\t%d\n", this.getId(), this.getName(), this.getAge());
		// if a patron has fees on their account, this data is also displayed
		if (this.fees > 0) {
			System.out.format("\tFees owed:\t$%.2f\n", this.getFees()); 
		}
		this.printBooks();
	}
}
