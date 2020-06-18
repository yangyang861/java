package vo;
import java.util.Date;
public class ReadRecord {
	  private int serialNumber;
	  private String bookID;
      private String bookName;
	  private int readerID;
	  private String readerName;
	  private String borrowTime;
	  private String backTime;
	public ReadRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReadRecord(int serialNumber, String bookID, String bookName, int readerID, String readerName,
			String borrowTime, String backTime) {
		super();
		this.serialNumber = serialNumber;
		this.bookID = bookID;
		this.bookName = bookName;
		this.readerID = readerID;
		this.readerName = readerName;
		this.borrowTime = borrowTime;
		this.backTime = backTime;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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
	public int getReaderID() {
		return readerID;
	}
	public void setReaderID(int readerID) {
		this.readerID = readerID;
	}
	public String getReaderName() {
		return readerName;
	}
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}
	public String getBorrowTime() {
		return borrowTime;
	}
	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}
	public String getBackTime() {
		return backTime;
	}
	public void setBackTime(String backTime) {
		this.backTime = backTime;
	}
	@Override
	public String toString() {
		return "ReadRecord [serialNumber=" + serialNumber + ", bookID=" + bookID + ", bookName=" + bookName
				+ ", readerID=" + readerID + ", readerName=" + readerName + ", borrowTime=" + borrowTime + ", backTime="
				+ backTime + "]";
	}

}
