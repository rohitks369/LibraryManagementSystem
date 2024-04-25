package Model;

public class Book {
	private static int nextBookId = 1;
	private int bookId;
	private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;
	
	public Book(String title, String author, int totalCopies) {
		this.bookId = nextBookId++;
		this.title = title;
		this.author = author;
		this.totalCopies = totalCopies;
		this.availableCopies = totalCopies;
	}
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
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
	public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies = totalCopies;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies = availableCopies;
	}
    
	public void decreaseAvailableCopies() {
        availableCopies--;
    }

    public void increaseAvailableCopies() {
        availableCopies++;
    }

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author + ", totalCopies=" + totalCopies
				+ "]";
	}

}
