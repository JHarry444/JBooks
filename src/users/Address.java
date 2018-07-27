package users;

public class Address {
	
	private String houseName;
	private PostCode postcode;
	
	public Address(String houseName, PostCode postcode) {
		this.houseName = houseName;
		this.postcode = postcode;
	}
	
	public Address() {
		
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public PostCode getPostcode() {
		return postcode;
	}

	public void setPostcode(PostCode postcode) {
		this.postcode = postcode;
	}
	
	

}
