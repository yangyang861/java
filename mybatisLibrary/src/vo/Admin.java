package vo;

public class Admin {
	private int    adminID;
	private String adminName;
	private String adminPassword;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(int adminID, String adminName, String adminPassword) {
		super();
		this.adminID = adminID;
		this.adminName = adminName;
		this.adminPassword = adminPassword;
	}

	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", adminName=" + adminName + ", adminPassword=" + adminPassword + "]";
	}
	

}
