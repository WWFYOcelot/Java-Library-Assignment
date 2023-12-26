package Assignment1;

import java.time.LocalDate;

public class Book {
	
	// fields
	private String isbn;
	private String title;
	private String author;
	private Boolean avail;
	private LocalDate borrowDate = null;
	private LocalDate dueDate = null;
	
	// constructor
	public Book(String isbn, String title, String author, Boolean avail) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.avail = avail;
	}
	
	// minimal info constructor for convenience; only used for testing
	public Book(String title) {
		this.title = title;
		this.avail = true;
	}
	
	// default constructor
	public Book() {
		this.isbn = null;
		this.title = null;
		this.author = null;
		this.avail = false;
	}
	
	// getters and setters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Boolean getAvail() {
		return avail;
	}
	public void setAvail(Boolean avail) {
		this.avail = avail;
	}
	
	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	
	// returns a string with the availability of the book;
	// book is either 'Available' or 'Unavailable' with its due date listed
	public String printAvail() {
		if (this.getAvail() == false) 
			return "Unavailable; Return date is " + this.getDueDate().toString();
		else return "Available";
	}	
	
	// Displays book's data
	public void displayDetails() {
		System.out.format("%s\n\tAuthor:\t%s\n\tISBN:\t%s\n\tAvailability:\t%s\n\n", 
				this.getTitle(), this.getAuthor(), this.getIsbn(), this.printAvail());
	}

}
