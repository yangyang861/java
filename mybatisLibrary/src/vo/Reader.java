package vo;

public class Reader {
	private int readerID;
	private String readerName;
    private String readerPassword;
	public Reader() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reader(int readerID, String readerName, String readerPassword) {
		super();
		this.readerID = readerID;
		this.readerName = readerName;
		this.readerPassword = readerPassword;
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
	public String getReaderPassword() {
		return readerPassword;
	}
	public void setReaderPassword(String readerPassword) {
		this.readerPassword = readerPassword;
	}
	@Override
	public String toString() {
		return "Reader [readerID=" + readerID + ", readerName=" + readerName + ", readerPassword=" + readerPassword
				+ "]";
	}

}
