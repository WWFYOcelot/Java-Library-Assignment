package Assignment1;

import java.util.List;

public class Child extends Patron{

	// fields
	private String id;
	private String name;
	private int age;
	private List<Book> borrows;
	private Patron parent;
	private float fees = 0;
	
	// constructor
	public Child(String id, String name, int age, List<Book> borrows, Patron parent) {
		super(id, name, age, borrows);
		this.parent = parent;
	}
	
	// minimal info constructor for convenience; only used for testing
	public Child(String name) {
		super(name);
	}
	
	// default constructor
	public Child() {
		super();
	}

	@Override
	// function ensures that a Patron of type Child can't be assigned a book list with more than 10 items
	public void setBorrows(List<Book> borrows) {
		if (borrows.size() > 10) {
			System.out.println("A child cannot borrow more than 10 books at a time.\nPlease give them a shorter list of books to borrow.");
			return;
		}
		else
			this.borrows = borrows;
	}
	
	public Patron getParent() {
		return parent;
	}

	public void setParent(Patron parent) {
		this.parent = parent;
	}

	
	@Override
	// function ensures that a Patron of type Child can't borrow more than 10 books at a time
	public int borrowBook(Book book) {
		if (this.getBorrows().size() == 10) {
			System.out.println("A child cannot borrow more than 10 books.\nPlease return books to borrow more.");
			return -1;
		}
		return super.borrowBook(book);
	}
	
	@Override
	// because children live life on free trial mode, they shouldn't have to pay library fees
	// as such, if fees are added to a child's account by the returnBook method of Patron, this override method will instead add the fees to the Child's parent's account
	public int returnBook(Book book) {
		int returnVal = super.returnBook(book);
		if (this.fees > 0) {
			this.getParent().setFees(this.getParent().getFees() + this.fees);
			this.fees = 0;
			System.out.println("Fees added to parent account.");
		}
		return returnVal;
	}
	
	@Override
	// prints the Child's parent name & ID along with other info about the Child
	public void displayPatronData() {
		System.out.format("Child ID: \t%s\n\tName:\t%s\n\tAge:\t%d\n\tParent:\t%s - %s\n", this.getId(), this.getName(), this.getAge(), this.getParent().getName(), 
				this.getParent().getId());
		this.printBooks();
		
	}

}