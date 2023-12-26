package Assignment1;

import java.util.*;

public class Main {
	
	// statically declared Scanner
	static Scanner sc = new Scanner(System.in);
	
	// static methods to take String/int input
	// ensure that invalid inputs don't throw errors; will instead prompt user to re-enter input until valid input is entered
	// int method also clears Scanner buffer after taking an int, which prevents input from 
	// 	being skipped due to the newline left after the int is received being left in the buffer
	static public String getString() {
		String out = null;
		while (true) {
			if (sc.hasNextLine()) {
				out = sc.nextLine();
				break;
			}
			else {
				System.out.println("Input error. Please try again.");
				continue;
			}
		}
		return out;
	}
	
	static public int getInt() {
		int out = -1;
		while (true) {
			if (sc.hasNextInt()) {
				out = sc.nextInt();
				sc.nextLine();
				break;
			}
			else {
				System.out.println("Input error. Please try again.");
				sc.nextLine();
				continue;
			}
		}
		return out;
	}
	
	// main
	public static void main (String args[]) {
		
		// define exit condition for loop; to be used later
		boolean menu = true;
		
		// create objects and structures for later use
		Library library = new Library();
		
		ArrayList<Book> faraanBooks = new ArrayList<Book>();
		Patron faraan = new Patron("01", "Faraan Rashid", 19, faraanBooks);
		Child faraanBrother = new Child("011", "Other Rashid", 10, null, faraan);
		
		ArrayList<Book> theLordOfTheRings = new ArrayList<Book>();
		
		Book fellowship = new Book("0345339703", "The Fellowship of the Ring", "J.R.R. Tolkien", true);
		Book towers = new Book("0345339711", "The Two Towers", "J.R.R. Tolkien", true);
		Book king = new Book("0345339738", "The Return of the King", "J.R.R. Tolkien", true);
		
		theLordOfTheRings.add(fellowship);
		theLordOfTheRings.add(towers);
		theLordOfTheRings.add(king);
		
		Book hp1 = new Book("0590353403", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling", true);
		Book hp2 = new Book("0439064864", "Harry Potter and the Chamber of Secrets", "J.K. Rowling", true);
		Book hp3 = new Book("0439136350", "Harry Potter and the Prisoner of Azkaban", "J.K. Rowling", true);
		Book hp4 = new Book("0439139597", "Harry Potter and the Goblet of Fire", "J.K. Rowling", true);
		Book hp5 = new Book("043935806X", "Harry Potter and the Order of the Phoenix", "J.K. Rowling", true);
		Book hp6 = new Book("0439784549", "Harry Potter and the Half-Blood Prince", "J.K. Rowling", true);
		Book hp7 = new Book("0545010225", "Harry Potter and the Deathly Hallows", "J.K. Rowling", true);
		
		ArrayList<Book> harryPotter = new ArrayList<Book>();
		harryPotter.add(hp1);
		harryPotter.add(hp2);
		harryPotter.add(hp3);
		harryPotter.add(hp4);
		harryPotter.add(hp5);
		harryPotter.add(hp6);
		harryPotter.add(hp7);
		
		ArrayList<Book> witcher = new ArrayList<Book>();
		Book w1 = new Book("01", "The Last Wish", "Andrzej Sapkwoski", true);
		Book w2 = new Book("02", "Sword of Destiny", "Andrzej Sapkwoski", true);
		Book w3 = new Book("03", "Blood of Elves", "Andrzej Sapkwoski", true);
		Book w4 = new Book("04", "The Time of Contempt", "Andrzej Sapkwoski", true);
		Book w5 = new Book("05", "Baptism of Fire", "Andrzej Sapkwoski", true);
		Book w6 = new Book("06", "The Tower of Swallows", "Andrzej Sapkwoski", true);
		Book w7 = new Book("07", "The Lady of the Lake", "Andrzej Sapkwoski", true);
		Book w8 = new Book("08", "Season of Storms", "Andrzej Sapkwoski", true);
		witcher.add(w1);
		witcher.add(w2);
		witcher.add(w3);
		witcher.add(w4);
		witcher.add(w5);
		witcher.add(w6);
		witcher.add(w7);
		witcher.add(w8);
		
		ReferenceBook audubonBirdGuide05 = new ReferenceBook("0123", "Audubon's Field Guide to Birds of North America", "John James Audubon", true, 05, "Audubon Society");
		ReferenceBook audubonBirdGuide10 = new ReferenceBook("0120034", "Audubon's Field Guide to Birds of North America", "John James Audubon", true, 10, "Audubon Society");
		ReferenceBook planets = new ReferenceBook("99004", "The Solar System", "Neil DeGrasse Tyson", true, 01, "NASA");
		ReferenceBook trees = new ReferenceBook("03986", "Encyclopedia of Trees", "Bob Ross", true, 04, "Arbour Society");
		
		library.addCollection(harryPotter);
		library.addCollection(theLordOfTheRings);
		library.addCollection(witcher);
		
		library.addBook(audubonBirdGuide05);
		library.addBook(audubonBirdGuide10);
		library.addBook(planets);
		library.addBook(trees);
		
		library.addPatron(faraan);
		library.addPatron(faraanBrother);		
		
		// main menu
		System.out.println("Welcome to the Library management system!");

		while (menu) {
			System.out.println("\nMain Menu");
			System.out.println("1. Add a book");
			System.out.println("2. Remove a book");
			System.out.println("3. Register a patron");
			System.out.println("4. Borrow a book");
			System.out.println("5. Return a book");
			System.out.println("6. Find a book");
			System.out.println("7. Find a patron");
			System.out.println("8. Print the catalogue");
			System.out.println("9. Display the full list of patrons");
			System.out.println("10. Exit\n");
			System.out.println("Please enter your selection:");
			
			// take user input
			int selection = getInt();

			// switch-case based on user input
			switch (selection) {
			case 1: 
				// add a book
				System.out.println("Add a book:");
				// prompt user for input
				System.out.println("Enter which type of book you would like to add to the library:");
				System.out.println("1. Normal book");
				System.out.println("2. Reference book");
				System.out.println("Please enter your selection:");
				
				// receive int for selection
				int bookSelect = getInt();
				
				// this data is common to both types, so it is received independently of the selection statement
				System.out.println("Enter the book's details to add it to the library.");
				System.out.println("Enter the book's title:");
				String title = getString();
				System.out.println("Enter the book's author:");
				String author = getString();
				// checks that the ISBN being entered is unique
				String isbn = null;
				// loops until acceptable input is entered i.e. unique ISBN
				while (true) {
					// tracks # of duplicate ISBN #s
					int count = 0;
					System.out.println("Enter the book's ISBN:");
					isbn = getString();
					// iterates through list of books, prints message if the ISBN entered by the user is a duplicate
					for (Book book: library.getBooks()) {
						if (book.getIsbn().equals(isbn)) {
							System.out.println("Duplicate ISBN entered. Please try again.");
							count++;
							break;
						}
					}
					// if the user-entered ISBN was a duplicate, the loop continues, again prompting the user for input
					if (count > 0) continue;
					// otherwise, the ISBN is stored and program execution continues
					else break;
				}
				// selection
				switch (bookSelect) {
				case 1:
					// create object & add to library
					Book newBook = new Book(isbn, title, author, true);
					library.addBook(newBook);
					System.out.println("Book added.");
					break;
				case 2:
					// create object & add to library
					System.out.println("Enter the book's publisher:");
					String publisher = sc.nextLine();
					System.out.println("Enter the edition number:");
					int edition = getInt();
					
					ReferenceBook newRefBook = new ReferenceBook(isbn, title, author, true, edition, publisher);
					library.addBook(newRefBook);
					System.out.println("Reference book added.");
					break;
				default:
					System.out.println("Invalid selection. Please try again.");
					break;
				}
				break;
			case 2:
				// remove a book
				System.out.println("Remove a book:");
				System.out.println("Enter a book to remove:");
				// finds book using function
				Book remBook = library.findBook();
				if (remBook.getTitle() != null) {
					// if the book is found, it is removed from the library
					library.removeBook(remBook);
					System.out.println("Book removed.");
					}
				else System.out.println("Book not found.");
				break;
			case 3:
				// register a patron
				System.out.println("Register a patron:");
				// prompt user for input, receive input
				System.out.println("Enter which type of patron you would like to register?");
				System.out.println("1. Normal patron");
				System.out.println("2. Child");
				System.out.println("Please enter your selection:");
				
				int patronSelect = getInt();
				
				switch (patronSelect) {
				case 1:
					// register a patron
					// prompts user for input, receives input
					System.out.println("Enter the patron's information to register them.");
					System.out.println("Enter the patron's name:");
					String name = getString();
					System.out.println("Enter the patron's age:");
					int age = getInt();
					// verifies the Patron's age
					if (age > 0 && age < 13) 
						System.out.println("This person is too young for a regular account. Assign them to a child account instead.");
					else if (age < 0)
						System.out.println("This person has yet to be born. What are they going to do with books?");
					else {
						// ensures that the ID that is entered is unique; similar process to ensuring the ISBN of a book is unique
						String id = null;
						while (true) {
							int count = 0;
							System.out.println("Enter the patron's ID:");
							id = sc.nextLine();
							for (Patron patron : library.getPatrons()) {
								if (patron.getId().equals(id)) {
									System.out.println("Duplicate ID entered. Please try again.");
									count++;
									break;
								}
							}
							if (count > 0) continue;
							else break;
						}
						// create object & add to library
						Patron newPatron = new Patron(id, name, age, null);
						library.addPatron(newPatron);
						System.out.println("Patron registered.");
					}
					break;
				case 2:
					// register a child
					// prompt user for input, receive input
					System.out.println("Enter the child's information to register them.");
					System.out.println("Enter the child's name:");
					String childName = getString();
					System.out.println("Enter the child's age:");
					int childAge = getInt();
					// verifies the Child's age
					if (childAge >= 13)
						System.out.println("This person is not a child. Assign them a regular patron account instead.");
					else if (childAge < 0)
						System.out.println("This person has yet to be born. What are they going to do with books?");
					else {
						// ensures ID is unique, same process as is used above
						String childId = null;
						while (true) {
							int count = 0;
							System.out.println("Enter the child's ID:");
							childId = getString();
							for (Patron patron : library.getPatrons()) {
								if (patron.getId().equals(childId)) {
									System.out.println("Duplicate ID entered. Please try again.");
									count++;
									break;
								}
							}
							if (count > 0) continue;
							else break;
						}
						
						// find parent patron based on their name, used to create child object if they are found
						System.out.println("Enter the child's parent");
						Patron parent = library.findPatron();
						if (parent.getName() == null) {
							System.out.println("Parent not found.");
						}
						else {					
							// create object and add to library
							Child newChild = new Child(childId, childName, childAge, null, parent);
							library.addPatron(newChild);
							System.out.println("Child registered.");
						}
					}
					break;
				default:
					System.out.println("Invalid selection. Please try again.");
					break;
				
				}
				break;
			case 4:
				System.out.println("Borrow a book:");
				// borrow a book
				// find patron by searching for their name, using a function
				System.out.println("Enter a patron:");
				Patron patron = library.findPatron();
				// if patron is not found, print message
				if (patron.getName() == null) {
					System.out.println("Patron not found.");
				}
				else {
					// find book using a function
					System.out.println("Enter a book:");
					Book book = library.findBook();
					// if book is not found, print message
					if (book.getTitle() == null) {
						System.out.println("Book not found.");
					}
					else {
						// call borrowBook function
						// print a message if the function completed successfully
						int success = patron.borrowBook(book);
						if (success == 0) {
							System.out.println("Book borrowed."); 
							patron.printBooks();
						}
					}
				}
				break;
			case 5:
				System.out.println("Return a book");
				// return a book
				// find patron by searching for their name, using a function
				System.out.println("Enter a patron: ");
				patron = library.findPatron();
				// if patron is not found, print message
				if (patron.getName() == null) {
					System.out.println("Patron not found.");
				}
				else {
					// find book using a function
					System.out.println("Enter a book:");
					Book book = library.findBook();
					// if book is not found, print message
					if (book.getTitle() == null) {
						System.out.println("Book not found.");
					}
					else {
						// call returnBook function
						// print a message if the function completed successfully
						int success = patron.returnBook(book);
						if (success == 0) {
							System.out.println("Book returned.");
						}
					}
				}
				break;
			case 6:
				System.out.println("Find a book");
				// find a book
				System.out.println("Enter a book: ");
				// find book by searching for its title, using a function
				Book book = library.findBook();
				// if book is not found, print message
				if (book.getTitle() == null) {
					System.out.println("Book not found.");
				}
				else {
					// call function to display book's info
					book.displayDetails();
				}
 				break;
			case 7:
				System.out.println("Find patron");
				// find patron using a function
				patron = library.findPatron();
				// if patron is not found, print message
				if (patron.getName() == null) {
					System.out.println("Patron not found.");
				}
				else {
					// call function to display patron's data
					patron.displayPatronData();
				}
				break;
			case 8:
				// print the catalog
				library.displayCatalog(library.getBooks());
				break;
			case 9:
				// print the list of patrons
				library.displayPatrons(library.getPatrons());
				break;
			case 10:
				// exit
				System.out.println("\nExiting library system. Goodbye!");
				menu = false;
				break;
			default:
				System.out.println("Invalid selection. Please try again.");
				break;
			}
		}
	}
}
