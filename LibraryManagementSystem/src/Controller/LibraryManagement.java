package Controller;

import Service.LibraryService;

public class LibraryManagement {

	public static void main(String[] args) {
		// Create a library service instance
		LibraryService libraryService = new LibraryService();

		// Add books
		libraryService.addBook("To Kill a Mockingbird", "Harper Lee", 1);
		libraryService.addBook("Pride and Prejudice", "Jane Austen", 2);

		// Add members
		libraryService.addMember("Bob Smith");
		libraryService.addMember("Alice Johnson");

		// Borrow books
		libraryService.borrowBook("Bob Smith", 1);
		libraryService.borrowBook("Bob Smith", 2);
		libraryService.borrowBook("Alice Johnson", 2);

		// Return a book
		libraryService.returnBook("Alice Johnson", 2);

		// Display all available books
		libraryService.displayAllBooksWithStatus();

		// Display all books held by a member
		libraryService.displayBooksHeldByMember("Bob Smith");

		/*-------------BONUS----------------*/
		// reverse book
		libraryService.reserveBook("Alice Johnson", 1);
		
		// notify when the user return the book(BONUS)
		libraryService.returnBook("Bob Smith", 1);

	}

}
