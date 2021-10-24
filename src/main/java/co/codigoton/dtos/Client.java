package co.codigoton.dtos;

public class Client {
	
	private String code;
	private float totalBalance;
	private int male;
	private int type;
	private String location;
	private String company;
	private int encrypt;
	private boolean isDeleted;
	

	public Client(float totalBalance, String code, int male, int type, String location, String company, int encrypt) {
		this.code = code;
		this.totalBalance = totalBalance;
		this.male = male;
		this.type = type;
		this.location = location;
		this.company = company;
		this.encrypt = encrypt;
	}
	
	public Client() {
		
	}

	public float getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(float totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getMale() {
		return male;
	}


	public void setMale(int male) {
		this.male = male;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}

	
	public int getEncrypt() {
		return encrypt;
	}


	public void setEncrypt(int encrypt) {
		this.encrypt = encrypt;
	}
	
	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}
