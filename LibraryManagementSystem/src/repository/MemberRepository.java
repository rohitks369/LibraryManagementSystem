package repository;

import java.util.*;

import Model.Book;
import Model.Member;

public class MemberRepository {
	private Map<String, Member> members;
    private Map<String, List<Book>> borrowedBooks;

    public MemberRepository() {
        this.members = new HashMap<>();
        this.borrowedBooks = new HashMap<>();
    }

    public void addMember(Member member) {
        members.put(member.getName(), member);
        borrowedBooks.put(member.getName(), new ArrayList<>());
    }

    public void removeMember(String memberName) {
        members.remove(memberName);
        borrowedBooks.remove(memberName);
    }

    public Member findMemberByName(String memberName) {
        return members.get(memberName);
    }

    public List<Member> getAllMembers() {
        return new ArrayList<>(members.values());
    }

    public void addBorrowedBook(String memberName, Book book) {
        List<Book> borrowedBooksForMember = borrowedBooks.get(memberName);
        if (borrowedBooksForMember != null) {
            borrowedBooksForMember.add(book);
        } else {
            System.out.println("Member '" + memberName + "' not found.");
        }
    }
    
    public void removeBorrowedBook(String memberName, Book book) {
        List<Book> borrowedBooksForMember = borrowedBooks.get(memberName);
        if (borrowedBooksForMember != null) {
            borrowedBooksForMember.remove(book);
        } else {
            System.out.println("Member '" + memberName + "' not found.");
        }
    }

	public List<Book> getBorrowedBooks(String memberName) {
		return borrowedBooks.getOrDefault(memberName, new ArrayList<>());
	}
}
