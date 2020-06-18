package vo;
import java.util.Date;
public class Book {
	private String bookID;
	private String bookName;
	private String addTime;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(String bookID, String bookName, String addTime) {
		super();
		this.bookID = bookID;
		this.bookName = bookName;
		this.addTime = addTime;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID = bookID;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	@Override
	public String toString() {
		return "Book [bookID=" + bookID + ", bookName=" + bookName + ", addTime=" + addTime + "]";
	}

}
