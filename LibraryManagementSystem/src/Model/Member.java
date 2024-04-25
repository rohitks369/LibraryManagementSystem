package Model;

public class Member {
	private static int nextMemberId = 1;
	private int memberId;
	private String name;
	public Member( String name) {
		super();
		this.memberId = nextMemberId++;
		this.name = name;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", name=" + name + "]";
	}
	
	
}
