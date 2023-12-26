package Assignment1;

import java.util.*;

public class Library {
	
	// fields
	private List<Book> books;
	private List<Patron> patrons;
	
	// parameterized constructor
	public Library(List<Book> books, List<Patron> patrons) {
		this.books = books;
		this.patrons = patrons;
	}
	
	// default constructor
	public Library() {
		this.books = new ArrayList<Book>();
		this.patrons = new ArrayList<Patron>();
	}
	
	
	// getters and setters
	public List<Book> getBooks() {
		return books;
	}
	
	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Patron> getPatrons() {
		return patrons;
	}

	public void setPatrons(List<Patron> patrons) {
		this.patrons = patrons;
	}
	
	// methods
	
	// adds book to library's book list using ArrayList methods
	public void addBook(Book book) {
		this.getBooks().add(book);
	}
	
	// removes book from library's book list using ArrayList methods
	public void removeBook(Book book) {
		// checks if book is in library
		if (this.getBooks().contains(book)) {
			this.getBooks().remove(book);
		}
		// if book is not in library, prints a message to the user
		else {
			System.out.format("\nThe libary does not contain %s.", book.getTitle());
		}
	}
	
	// can add an ArrayList of books
	public void addCollection(ArrayList<Book> books) {
		for (Book book: books) {
			this.addBook(book);
		}
	}
	
	// adds a patron to the library
	public void addPatron(Patron patron) {
		this.getPatrons().add(patron);
	}
	
	// searchBook function
	public Book findBook() {
		// creates empty object
		Book empty= new Book();
		while (true) {
			System.out.println("Search by:\n1. Title\n2. ISBN");
			int select = Main.getInt();
			if (select == 1) {
				System.out.println("Enter the book's name:");
				String bookTitle = Main.getString();
				// iterates through patrons
				for (Book book: books) {
					// if patron with correct name is found, empty patron is reassigned to that object
					if (book.getTitle() != null) {
						if (book.getTitle().equals(bookTitle)) {
							empty = book;
							break;
						}
					}
				}
				break;
			}
			else if (select == 2) {
				System.out.println("Enter the book's ISBN:");
				String bookISBN = Main.getString();
				// iterates through patrons
				for (Book book : books) {
					// if patron with correct name is found, empty patron is reassigned to that object
					if (book.getIsbn() != null) {
						if (book.getIsbn().equals(bookISBN)) {
							empty = book;
							break;
						}
					}
				}
				break;
			}
			else {
				System.out.println("Invalid selection. Please try again.");
				continue;
			}
		}
		// returns patron object, either empty or the object that was searched for
		return empty;
	}
	
	// searchPatron function
	public Patron findPatron() {
		// creates empty object
		Patron none = new Patron();
		while (true) {
			System.out.println("Search by:\n1. Name\n2. ID");
			int select = Main.getInt();
			if (select == 1) {
				System.out.println("Enter the patron's name:");
				String patronName = Main.getString();
				// iterates through patrons
				int count = 0;
				for (Patron patron : patrons) {
					// if patron with correct name is found, empty patron is reassigned to that object
					if (patron.getName() != null) {
						if (patron.getName().equals(patronName)) {
							count++;
							none = patron;
						}
					}
				}
				// if there is more than one patron with the name being searched for, the user is instead prompted to search for the user by ID, since this value is unique
				if (count > 1) {
					System.out.println("Identical names found. Please search by ID.");
					continue;
				}
				break;
			}
			else if (select == 2) {
				// prompt user for input, receive input
				System.out.println("Enter the patron's ID:");
				String patronID = Main.getString();
				// iterates through patrons
				for (Patron patron : patrons) {
					// if patron with correct name is found, empty patron is reassigned to that object
					if (patron.getId() != null) {
						if (patron.getId().equals(patronID)) {
							none = patron;
							break;
						}
					}
				}
				break;
			}
			else {
				System.out.println("Invalid selection. Please try again.");
				continue;
			}
		}
		// returns patron object, either empty or the object that was searched for
		return none;
	}
	
	// iterates through the library's book list, calls the displayDetails() function for each book, irrespective of type
	public <T extends Book> void displayCatalog(List<T> books) {
		System.out.println("Book list:");
		for (T book : books) {
			book.displayDetails();
		}
	}
	
	// iterates through the library's patron list, calls the displayPatronData() function for each patron, irrespective of type
	public <T extends Patron> void displayPatrons(List<T> patrons) {
		for (T patron : patrons) {
			patron.displayPatronData();
		}
	}
}
