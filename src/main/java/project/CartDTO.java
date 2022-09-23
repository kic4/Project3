package project;

public class CartDTO {
	int cart_id, package_count, package_no; //장바구니번호, 패키지 수량, 패키지 번호
	String user_id;	//유저 아이디
						
	public CartDTO(int cart_id, String user_id, int package_no, int package_count) {
		this.cart_id=cart_id;
		this.package_count=package_count;
		this.package_no=package_no;
		this.user_id=user_id;
	}

	public CartDTO() {
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getPackage_count() {
		return package_count;
	}

	public void setPackage_count(int package_count) {
		this.package_count = package_count;
	}

	public int getPackage_no() {
		return package_no;
	}

	public void setPackage_no(int package_no) {
		this.package_no = package_no;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
