package project;
//DBConnectionMgr(DB접속,관리),BoardDTO(매개변수,반환형,데이터를 담는 역할)

import java.sql.*;//DB사용
import java.util.*;//ArrayList,List을 사용

public class BoardDAO { // MemberDAO

	private DBConnectionMgr pool = null;// 1.연결객체선언
	// 공통
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;// select
	private String sql = "";// 실행시킬 SQL구문 저장

	// 2.생성자를 통해서 연결=>의존관계
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		} catch (Exception e) {
			System.out.println("DB접속 오류=>" + e);
		}
	}// 생성자

	// 1.페이징 처리를 위한 전체레코드수를 구해와야 한다.
	// select count(*) from board; select count(*) from member
	public int getArticleCount() { // getMemberCount() ->MemberDAO에서 작성
		int x = 0;// 총레코드갯수를 저장
		try {
			con = pool.getConnection();
			System.out.println("con=>" + con);
			sql = "select count(*) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				x = rs.getInt(1);// 변수명=rs.get자료형(필드명 또는 인덱스번호)=>필드명X(그룹함수)
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}

	// 2.글목록보기에 대한 메서드구현->레코드 한개이상->한 페이지당 10개씩 끊어서 보여준다.
	// 1)레코드의 시작번호 2) 불러올 레코드의 갯수
	public List getArticles(int start, int end) {// getMemberList(int start,int end){

		List articleList = null;// ArrayList articleList=null;//(O)

		try {
			con = pool.getConnection();
			/*
			 * 그룹번호가 가장 최신의 글을 중심으로 정렬하되,만약에 level이 같은 경우에는 step값으로 오름차순을 통해서 몇번째 레코드번호를
			 * 기준해서 몇개까지 정렬할것인가를 지정해주는 SQL구문
			 */
			sql = "select * from board order by ref desc,re_step limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, start - 1);// mysql은 레코드순번이 내부적으로 0부터 시작
			pstmt.setInt(2, end);// 불러와서 담을 갯수
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				articleList = new ArrayList(end);// 10=>end갯수만큼 데이터를 담을 공간을 만들어라
				do {
					BoardDTO article = new BoardDTO();// MemberDTO=>필드별로 담을것.
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setSubject(rs.getString("subject"));
					article.setPasswd(rs.getString("passwd"));

					article.setReg_date(rs.getTimestamp("reg_date"));// 오늘날짜->코딩
					article.setReadcount(rs.getInt("readcount"));// 조회수 default->0
					article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글을 묶어주는 역할
					article.setRe_step(rs.getInt("re_step"));// 답변글이 나오는 순서
					article.setRe_level(rs.getInt("re_level"));// 들여쓰기(답변의 깊이)

					article.setContent(rs.getString("content"));// 글내용
					article.setIp(rs.getString("ip"));// 글쓴이의 ip주소->request.getRemoteAddr()
					// 추가
					articleList.add(article);// 생략하면 데이터가 저장X->for문 에러유발
				} while (rs.next());
			}
		} catch (Exception e) {
			System.out.println("getArticleCount() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return articleList;
	}

	// ----게시판의 글쓰기 및 답변글쓰기-----
	// insert into board values(?,?,,,,
	public void insertArticle(BoardDTO article) {// ~(MemberDTO mem)
		// 1.article->신규글 인지 답변글인지 확인
		int num = article.getNum();// 0 신규글 0이아닌경우 (답변글)
		int ref = article.getRef();
		int re_step = article.getRe_step();
		int re_level = article.getRe_level();

		int number = 0;// 데이터를 저장하기위한 게시물번호
		System.out.println("insertArticle메서드의 내부 num=>" + num);// 0 신규글
		System.out.println("ref=" + ref + ",re_step=" + re_step + ",re_level=" + re_level);

		try {
			con = pool.getConnection();
			sql = "select max(num) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				number = rs.getInt(1) + 1;// 최대값+1
			} else {
				number = 1;// 테이블에 한개의 데이터가 없다면
			}
			// 답변글이라면
			if (num != 0) { // 양수이면서 1이상
				// 같은 그룹번호를 가지고 있으면서 나보다 step값이 큰 게시물을 찾아서 그 step값을하나 증가시킴
				sql = "update board set re_step=re_step+1 where ref=? and re_step>?";// 중간에 껴들어갈수있도록 수정
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				int update = pstmt.executeUpdate();
				System.out.println("댓글수정유무(update)=>" + update); // 1(성공), 0(실패)
				// 답변글
				re_step = re_step + 1;
				re_level = re_level + 1;

			} else {// 신규글이라면 num=0
				ref = number;// 1,2,3,4,,,
				re_step = 0;
				re_level = 0;
			}
			// 12개->num,reg_date,readcount(생략)->default
			// 작성날짜=>sysdate, now() (mysql)
			sql = "insert into board(writer,subject,passwd,reg_date,";
			sql += " ref,re_step,re_level,content,ip)values(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());// 웹에서는 이미 데이터저장된 상태(Setter~)
			pstmt.setString(2, article.getSubject());
			pstmt.setString(3, article.getPasswd());
			pstmt.setTimestamp(4, article.getReg_date());// 5번째에 ? 대신에 now()
			// -------------ref,re_step,re_level에 대한 계산이 적용된 상태에서 저장
			pstmt.setInt(5, ref);// 5
			pstmt.setInt(6, re_step);// 0
			pstmt.setInt(7, re_level);// 0
			// -------------------------------------------------------------------
			pstmt.setString(8, article.getContent());// 내용
			pstmt.setString(9, article.getIp());// request.getRemoteAddr();
			int insert = pstmt.executeUpdate();
			System.out.println("게시판의 글쓰기 성공유무(insert)=>" + insert);
		} catch (Exception e) {
			System.out.println("insertArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
	}
	// 글상세보기
	// "content.jsp?num=<%=article.getNum()%>&pageNum=<%=currentPage%>"
	// 형식) select * from board where num=3
	// 형식2)update board set readcount = readcount + 1 where num=3
	// public MemberDTO getMember(String id){~}

	public BoardDTO getArticle(int num) {
		BoardDTO article = null; // ArrayList articleList = null; //여러개 담을때

		try {
			con = pool.getConnection();

			sql = "update board set readcount=readcount+1 where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int update = pstmt.executeUpdate();
			System.out.println("조회수 증가유무(update)=>" + update); // 1

			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				article = makeArticleFromResult();
				/*
				 * article=new BoardDTO();//MemberDTO=>필드별로 담을것.
				 * article.setNum(rs.getInt("num")); article.setWriter(rs.getString("writer"));
				 * article.setEmail(rs.getString("email"));
				 * article.setSubject(rs.getString("subject"));
				 * article.setPasswd(rs.getString("passwd"));
				 * article.setReg_date(rs.getTimestamp("reg_date"));//오늘날짜->코딩
				 * article.setReadcount(rs.getInt("readcount"));//조회수 default->0
				 * article.setRef(rs.getInt("ref"));//그룹번호->신규글과 답변글을 묶어주는 역할
				 * article.setRe_step(rs.getInt("re_step"));//답변글이 나오는 순서
				 * article.setRe_level(rs.getInt("re_level"));//들여쓰기(답변의 깊이)
				 * article.setContent(rs.getString("content"));//글내용
				 * article.setIp(rs.getString("ip"));//글쓴이의 ip주소->request.getRemoteAddr()
				 */
			}
		} catch (Exception e) {
			System.out.println("getArticle() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// ------중복된 레코드 한개를 담을 수 있는 메서드를 따로 만들어서 처리------
	// 접근지정자가 private인 경우 => 외부에서 호출목적x, 내부에서 호출목적으로 사용
	private BoardDTO makeArticleFromResult() throws Exception {
		BoardDTO article = new BoardDTO();// MemberDTO=>필드별로 담을것.
		article.setNum(rs.getInt("num"));
		article.setWriter(rs.getString("writer"));
		article.setSubject(rs.getString("subject"));
		article.setPasswd(rs.getString("passwd"));
		article.setReg_date(rs.getTimestamp("reg_date"));// 오늘날짜->코딩
		article.setReadcount(rs.getInt("readcount"));// 조회수 default->0
		article.setRef(rs.getInt("ref"));// 그룹번호->신규글과 답변글을 묶어주는 역할
		article.setRe_step(rs.getInt("re_step"));// 답변글이 나오는 순서
		article.setRe_level(rs.getInt("re_level"));// 들여쓰기(답변의 깊이)
		article.setContent(rs.getString("content"));// 글내용
		article.setIp(rs.getString("ip"));// 글쓴이의 ip주소->request.getRemoteAddr()
		return article;
	}

	// -------------------------------------------------------------------
	// 글수정
	// 1)수정할 데이터를 찾을 메서드 필요-> select * from board where num=?
	public BoardDTO updateGetArticle(int num) {
		BoardDTO article = null; // ArrayList articleList = null; //여러개 담을때

		try {
			con = pool.getConnection();
			sql = "select * from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				article = makeArticleFromResult();
			}
		} catch (Exception e) {
			System.out.println("updateGetArticle() 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return article;
	}

	// 2)수정시키는 메서드 작성=>본인인지 확인절차=>회원탈퇴(암호를 비교=>탈퇴)와 동일한 기능
	public int updateArticle(BoardDTO article) {// insertArticle(BoardDTO article)

		String dbpasswd = null; // db에서 찾은 암호를 저장
		int x = -1; // 게시물의 수정성공유무

		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, article.getNum());
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				dbpasswd = rs.getString("passwd");
				//System.out.println("dbpasswd=>" + dbpasswd); // 확인 디버깅 코딩(나중에는 무조건 지워야함)
				if (dbpasswd.equals(article.getPasswd())) {
					sql = "update board set writer=?, subject=?, passwd=?,";
					sql += " content=? where num=?";

					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, article.getWriter());// 웹에서는 이미 데이터저장된 상태(Setter~)
					pstmt.setString(2, article.getSubject());
					pstmt.setString(3, article.getPasswd());
					pstmt.setString(4, article.getContent());// 내용
					pstmt.setInt(5, article.getNum());

					int update = pstmt.executeUpdate();
					System.out.println("게시판의 글수정 성공유무(update)=>" + update);
					x = 1;// 수정 성공 유무
				} else { // 암호가 틀린경우
					x = 0;
				}
			}
		} catch (Exception e) {
			System.out.println("updateArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	//글삭제=>암호를 비교 -> delete from board where num=?
	public int deleteArticle(int num, String passwd) { //회원탈퇴와 동일한 기능
		String dbpasswd = null; // db에서 찾은 암호를 저장
		int x = -1; // 게시물의 삭제 성공유무

		try {
			con = pool.getConnection();
			sql = "select passwd from board where num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {// 보여주는 결과가 있다면
				dbpasswd = rs.getString("passwd");
				//System.out.println("dbpasswd=>" + dbpasswd); // 확인 디버깅 코딩(나중에는 무조건 지워야함)
				if (dbpasswd.equals(passwd)){
					sql = "delete from board where num=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					int delete = pstmt.executeUpdate();
					System.out.println("게시판의 글삭제 성공유무(delete)=>" + delete);
					x = 1;// 삭제 성공 유무
				} else { // 암호가 틀린경우
					x = 0; //삭제 실패
				}
			}
		} catch (Exception e) {
			System.out.println("deleteArticle() 메서드 에러유발=>" + e);
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return x;
	}
	
	
}
