package Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Book;
import Model.Member;
import repository.BookRepository;
import repository.MemberRepository;

public class LibraryService {
	private BookRepository bookRepository;
	private MemberRepository memberRepository;
	private Map<Integer, Member> bookReservations;

	public LibraryService() {
		this.bookRepository = new BookRepository();
		this.memberRepository = new MemberRepository();
		this.bookReservations = new HashMap<>();
	}

	/*----------Book related function----------------*/
	// add book
	public void addBook(String bookTitle, String bookAuthor, int totalCopies) {
		Book book = new Book(bookTitle, bookAuthor, totalCopies);
		bookRepository.addBook(book);

		// display the book added
		System.out.println(book.toString());
	}

	public void borrowBook(String memberName, int bookId) {
		Member member = memberRepository.findMemberByName(memberName);
		if (member != null) {
			Book book = bookRepository.findBookById(bookId);
			if (book != null && book.getAvailableCopies() > 0) {
				book.decreaseAvailableCopies();
				memberRepository.addBorrowedBook(memberName, book);
				System.out.println("Output: [ID: " + bookId + ", Borrower: '" + memberName + "']");
			} else {
				System.out.println("Book is not available for borrowing.");
			}
		} else {
			System.out.println("Member not found. Please Register to Library!!");
		}
	}

	private String isBookAvailable(String bookName) {
		Book book = findBookByName(bookName);
		if (book != null) {
			String status = isAvailable(book) ? "Available" : "Borrowed";
			return "Output for a book " + book.getTitle() + " : [ID: " + book.getBookId() + ", Status: '" + status
					+ "']";
		} else {
			return "Book not found.";
		}
	}

	private boolean isAvailable(Book book) {
		return book.getAvailableCopies() > 0;
	}

	private Book findBookByName(String bookName) {
		for (Book book : bookRepository.getAllBooks()) {
			if (book.getTitle().equals(bookName)) {
				return book;
			}
		}
		return null;
	}

	// return the book
	public void returnBook(String memberName, int bookId) {
		Member member = memberRepository.findMemberByName(memberName);
		if (member != null) {
			Book book = bookRepository.findBookById(bookId);
			if (book != null) {
				book.increaseAvailableCopies();
				memberRepository.removeBorrowedBook(memberName, book);
				System.out.println("Output: [ID: " + bookId + ", Return: '" + memberName + "']");
				
				// Check if the returned book has a reservation
				notifyReservedBookReturn(bookId, book);
                

			} else {
				System.out.println("Book not found.");
			}
		} else {
			System.out.println("Member not found.");
		}
	}

	private void notifyReservedBookReturn(int bookId, Book book) {
		if (bookReservations.containsKey(bookId)) {
            Member reservingMember = bookReservations.get(bookId);
            System.out.println("The reserved book " + book.getTitle() + " has been returned.");
            System.out.println("Notifying the member " + reservingMember.getName() + " about the availability of the book.");
            // Make the book available for the reserving member
            bookReservations.remove(bookId);
            book.decreaseAvailableCopies();
            memberRepository.addBorrowedBook(reservingMember.getName(), book);
        }
		
	}

	public void displayAllBooksWithStatus() {
		List<Book> allBooks = bookRepository.getAllBooks();
		if (allBooks.isEmpty()) {
			System.out.println("No books available in the library.");
		} else {
			System.out.println("Books available in the library:");
			for (Book book : allBooks) {
				String status = book.getAvailableCopies() > 0 ? "Available" : "Borrowed";
				System.out.println("Output for a book " + book.getTitle() + " : [ID: " + book.getBookId()
						+ ", Status: '" + status + "']");
			}
		}
	}

	/*----------Member related function----------------*/
	// add member
	public void addMember(String memberName) {
		Member member = new Member(memberName);
		memberRepository.addMember(member);

		// display the added member
		System.out.println(member.toString());
	}

	// Retrieve books held by a member
	// Display all books held by a member
	public void displayBooksHeldByMember(String memberName) {
		Member member = memberRepository.findMemberByName(memberName);
		if (member != null) {
			List<Book> borrowedBooks = memberRepository.getBorrowedBooks(memberName);
			if (borrowedBooks.isEmpty()) {
				System.out.println("Member '" + memberName + "' does not hold any books.");
			} else {
				System.out.print(
						"Output for member " + memberName + " : [Member ID: " + member.getMemberId() + ", Books: [");
				for (int i = 0; i < borrowedBooks.size(); i++) {
					System.out.print("'" + borrowedBooks.get(i).getTitle() + "'");
					if (i < borrowedBooks.size() - 1) {
						System.out.print(", ");
					}
				}
				System.out.println("]]");
			}
		} else {
			System.out.println("Member '" + memberName + "' not found.");
		}
	}

	/*-----------------BONUS-----------------*/
	// Reserve a book
    public void reserveBook(String memberName, int bookId) {
        Member member = memberRepository.findMemberByName(memberName);
        if (member != null) {
            Book book = bookRepository.findBookById(bookId);
            if (book != null) {
                if (book.getAvailableCopies() > 0) {
                    System.out.println("The book " + book.getTitle() + " is already available. No need to reserve.");
                } else {
                    if (bookReservations.containsKey(bookId)) {
                        System.out.println("The book " + book.getTitle() + " is already reserved by another member.");
                    } else {
                        bookReservations.put(bookId, member);
                        System.out.println(memberName + " reserved the book " + book.getTitle() + " successfully.");
                    }
                }
            } else {
                System.out.println("Book not found.");
            }
        } else {
            System.out.println("Member not found.");
        }
    }

}
