package Assignment1;

public class ReferenceBook extends Book{
	
	// fields
	private int edition;
	private String publisher;
	
	// constructor
	public ReferenceBook(String isbn, String title, String author, Boolean avail, int edition, String publisher) {
		// constructor uses superclass constructor
		super(isbn, title, author, avail);
		this.edition = edition;
		this.publisher = publisher;
	}
	
	// default constructor
	public ReferenceBook() {
		super();
		this.edition = -1;
		this.publisher = null;
	}
	
	// getters and setters
	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	// override method to show information specific to reference book type
	@Override
	public void displayDetails() {
		System.out.format("%s\n\tAuthor:\t%s\n\tISBN:\t%s\n\tAvailability:\t%s\n", 
				this.getTitle(), this.getAuthor(), this.getIsbn(), this.printAvail());
		System.out.format("\tPublisher:\t%s\n\tEdition:\t%d\n\n", this.getPublisher(), this.getEdition());
	}
	
	
	
}
