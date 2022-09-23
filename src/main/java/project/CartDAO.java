package project;
//DBConnectionMgr(DB접속,관리),BoardDTO(매개변수,반환형,데이터를 담는 역할)

//DB사용
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//ArrayList,List을 사용
import java.util.ArrayList;
import java.util.List;

public class CartDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.연결객체선언
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select
	private String sql = "";// 실행시킬 SQL구문 저장

	public CartDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}// 생성자

	//상품 목록보기
	public ArrayList<CartDTO> getCarts(String user_id) {// getMemberList(int start,int end){

		ArrayList<CartDTO> getCarts = null;// ArrayList articleList=null;//(O)

		try {
			con = pool.getConnection();
			sql = "select * from shoppingcart where user_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			if (rs.next()) {// 보여주는 결과가 있다면
				do {
					CartDTO cart = new CartDTO();
					cart.setCart_id(rs.getInt("cart_id"));
					cart.setPackage_count(rs.getInt("package_count"));
					cart.setPackage_no(rs.getInt("package_no"));
					cart.setUser_id(rs.getString("user_id"));
					
					getCarts.add(cart);
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return getCarts;
	}
	
	
	//상품 상세보기
	public CartDTO getCart(String user_id, int package_no) {
		CartDTO cart = null;
		try {
			con = pool.getConnection();
			sql = "select * from shoppingcart where user_id=? and package_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, package_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cart = new CartDTO();
				cart.setCart_id(rs.getInt("cart_id"));
				cart.setUser_id(rs.getString("user_id"));
				cart.setPackage_no(rs.getInt("package_no"));
				cart.setPackage_count(rs.getInt("package_count"));		
			} 	
		} catch (Exception e) {
			System.out.println("getCart() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return cart;
	}
	
	// 장바구니에 상품추가
	public void cartAdd(CartDTO cart) {
		String user_id = cart.user_id;
		int package_no = cart.package_no;
		int package_count = cart.package_count;
		System.out.println("CartDAO.java cartAdd()의 user_id=>" + user_id + "package_count=>" + package_count
				+ "package_no=>" + package_no);
		try {
			con = pool.getConnection();
			sql = "select count(cart_id) from shoppingcart";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			sql = "insert into shoppingcart values (?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getCart_id());
			pstmt.setString(2, cart.getUser_id());
			pstmt.setInt(3, cart.getPackage_no());
			pstmt.setInt(4, cart.getPackage_count());
			int insert = pstmt.executeUpdate();
			System.out.println("장바구니 담기 성공여부=>" + insert); // 1(성공), 0(실패)
		} catch (Exception e) {
			System.out.println("cartAdd() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	
	//장바구니에서 상품 삭제
	public void cartDelete(String user_id, int cart_id) {

		try {
			con = pool.getConnection();
			sql = "delete from shoppingcart where user_id=? and cart_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setInt(2, cart_id);
			rs = pstmt.executeQuery();
			
			int delete = pstmt.executeUpdate();
			System.out.println("장바구니 내 상품삭제 성공유무=>" + delete);
		} catch (Exception e) {
			System.out.println("cartDelete() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}

	
	
}
