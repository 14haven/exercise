package kh.com.library.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import kh.com.library.book.Book;
import kh.com.library.member.Member;
import kh.com.library.rent.Rent;

public class LibraryController {
	Scanner sc = new Scanner(System.in);
	
	public void start() {
		
		while(true) {
		System.out.println("###### 도서관 관리 프로그램 ######");
		System.out.println("----- 메인 메뉴 -----");
		System.out.println("1. 책 관리");
		System.out.println("2. 회원 관리");
		System.out.println("3. 대여 관리");
		System.out.println("4. 프로그램 종료");
		System.out.print("메뉴를 선택하세요 : ");
		int select = sc.nextInt();
		
		switch(select) {
		
		case 1:
			book();
			break;
		case 2:
			member();
			break;
		case 3:
			rent();
			break;
		case 4:
			System.out.println("프로그램이 종료됩니다.");
			System.exit(0);
		default :
			System.out.println("번호를 다시 입력해주세요.");
		
			}
		}	
	}
	// 책관리 서브메뉴
	public void book() {
		/*
		1. 전체 책 조회
		2. 책 코드로 조회
		3. 책 추가하기
		4. 책 삭제하기
		5. 메인 메뉴로 이동*/
		while(true) {
		System.out.println("##### 책 관리 메뉴 #####");
		System.out.println("----- 메뉴 -----");
		System.out.println("1. 전체 책 조회");
		System.out.println("2. 책 번호로 조회");
		System.out.println("3. 책 추가하기");
		System.out.println("4. 책 삭제하기");
		System.out.println("5. 메인 메뉴로 이동");
		System.out.print("메뉴 선택 : ");
		int select = sc.nextInt();
		
		switch(select) {
			case 1:
				searchBook();
				break;
			case 2:
				searchBookCode();
				break;
			case 3:
				insertBook();
				break;
			case 4:
				deleteBook();
				break;
			case 5:
				System.out.println("메인 메뉴로 이동합니다.");
				return;
			default:
				System.out.println("번호를 다시 선택해주세요.");
		
			}
		}
	}
	
	// 1. 전체 책 조회
	public void searchBook() {
		System.out.println("-------- 전체 책 조회 -------");
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
	
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT * FROM BOOK";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			ArrayList<Book> list = new ArrayList();
			while(rset.next()) {
				Book book = new Book(
				rset.getInt("BOOK_NO"), rset.getString("BOOK_NAME"),
				rset.getString("BOOK_WRITER"), rset.getInt("BOOK_PRICE"),
				rset.getString("PUBLISHER"), rset.getString("GENRE"));
				
				list.add(book);		
			}
			
			if(list.size()>0) {
				System.out.println("총 "+list.size()+"권이 조회되었습니다.");
				for(Book bk : list) {
					System.out.println(bk.getBookNo()+" / "+bk.getBookName()+" / "+
				bk.getBookWriter()+" / "+bk.getBookPrice()+" / "+bk.getPublisher()+" / "+bk.getGenre());
					
				}
			} else {
				System.out.println("조회된 책 목록이 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
			
		}
		
	
	}
	// 2. 책 번호로 조회
	public void searchBookCode() {
		System.out.println("----- 책 코드로 조회 -----");
		System.out.print("책 번호를 입력해주세요 : ");
		int code = sc.nextInt();
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT * FROM BOOK WHERE BOOK_NO = '"+code+"'";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(!rset.next()) {
				System.out.println("해당 책이 존재하지 않습니다.");
				return;
			}
			
			Book bk = new Book(rset.getInt(1),rset.getString(2),rset.getString(3),
					rset.getInt(4),rset.getString(5),rset.getString(6));
			
			System.out.println("[ "+bk.getBookNo()+"번 책 정보 ]");
			System.out.println("제목 :"+bk.getBookName());
			System.out.println("글쓴이 : "+bk.getBookWriter());
			System.out.println("가격 : "+bk.getBookPrice());
			System.out.println("출판사 : "+bk.getPublisher());
			System.out.println("장르 : "+bk.getGenre());
			System.out.println();
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
		}
	}
	
	// 3. 책 추가
	public void insertBook() {
		System.out.println("------ 책 추가 ------");
		//BOOK_NO, BOOK_NAME, BOOK_WRITER, BOOK_PRICE, PUBLISHER, GENRE
		Book bk = new Book();
		System.out.print("책 번호 : ");
		bk.setBookNo(sc.nextInt());
		System.out.print("제목 : ");
		if(sc.hasNextLine()) {
		sc.nextLine();}
		bk.setBookName(sc.nextLine());
		System.out.print("글쓴이 : ");
		if(sc.hasNextLine()) {
			sc.nextLine();}
		bk.setBookWriter(sc.nextLine());
		System.out.print("가격 : ");
		bk.setBookPrice(sc.nextInt());
		System.out.print("출판사 : ");
		if(sc.hasNextLine()) {
			sc.nextLine();}
		bk.setPublisher(sc.nextLine());
		System.out.print("장르 : ");
		bk.setGenre(sc.next());
		
	
		//DB에 저장
		Connection conn = null;
		String query = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "INSERT INTO BOOK VALUES (?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, bk.getBookNo());
			pstmt.setString(2, bk.getBookName());
			pstmt.setString(3, bk.getBookWriter());
			pstmt.setInt(4, bk.getBookPrice());
			pstmt.setString(5, bk.getPublisher());
			pstmt.setString(6, bk.getGenre());
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("책 추가 완료");
			}else {
				System.out.println("책 추가 실패");
			}
			
		
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
			
		}
		
	}
	// 4. 책 삭제
	public void deleteBook() {
		System.out.println("----- 책 삭제 -----");
		System.out.print("삭제할 책의 번호 입력 : ");
		int code = sc.nextInt();
		
		// DB연결을 위한 참조변수 
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELCT * FROM BOOK WHERE BOOK_NO ="+code;
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(!rset.next()) {
				System.out.println("책 정보가 없습니다.");
				return;
			}
			
			System.out.println("[ "+rset.getInt("BOOK_NO")+"번 책 정보 ]");
			System.out.println("제목 : "+rset.getString("BOOK_NAME"));
			System.out.println("글쓴이  :"+rset.getString("BOOK_WRITER"));
			System.out.print("삭제 하시겠습니까?(Y/N) : " );
			if(sc.next().charAt(0) ==  'Y') {
				query = "DELETE FROM BOOK WHERE BOOK_NO ="+code;
				result = stmt.executeUpdate(query);
				if(result > 0) {
					System.out.println("책 정보 삭제 완료");
				}else {
					System.out.println("책 정보 삭제 실패");
				}
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	// 회원관리 서브메뉴
	public void member() {
		/*
		1. 회원 전체 조회
		2. 회원 이름으로 조회
		3. 회원 아이디로 조회
		4. 회원 가입 
		5. 회원 정보 수정
		6. 회원 탈퇴
		7. 메인 메뉴로 이동
		*/
		
	while(true) {
	System.out.println("##### 회원 관리 메뉴 #####");
	System.out.println("----- 메뉴 -----");
	System.out.println("1. 회원 전체 조회");
	System.out.println("2. 회원 이름으로 조회");
	System.out.println("3. 회원 아이디로 조회");
	System.out.println("4. 회원 가입");
	System.out.println("5. 회원 정보 수정");
	System.out.println("6. 회원 탈퇴");
	System.out.println("7. 메인 메뉴로 이동");
	System.out.print("메뉴 선택 : ");
	int select = sc.nextInt();
	
	switch(select) {
		case 1:
			searchMember();
			break;
		case 2:
			searchMemberName();
			break;
		case 3:
			searchMemberId();
			break;
		case 4:
			insertMember();
			break;
		case 5:
			updateMember();
			break;
		case 6:
			deleteMember();
			break;
		case 7:
			return;
		default :
			System.out.println("번호를 다시 입력해주세요.");
	
	}
	}
}
	// 1. 회원 전체 조회
	public void searchMember() {
		System.out.println("----- 회원 전체 조회 -----");
		
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT * FROM CUSTOMER";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
		
			ArrayList<Member> list = new ArrayList();
			while(rset.next()) {
				Member mb = new Member();
				mb.setUserNo(rset.getInt("USER_NO"));
				mb.setUserId(rset.getString("USER_ID"));
				mb.setUserName(rset.getString("USER_NAME"));
				mb.setUserAge(rset.getInt("USER_AGE"));
				mb.setAddr(rset.getString("ADDR"));
				mb.setGender(rset.getString("GENDER"));
				mb.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				list.add(mb);
			}
			
			if(list.size()>0) {
				System.out.println("총 "+list.size()+"명이 조회되었습니다.");
				System.out.println("----------------------");
				for(Member mb : list) {
					System.out.println(mb.getUserNo()+" / "+mb.getUserId()+
							"/ "+mb.getUserName()+" / "+mb.getEnrollDate());
				}
			}else {
				System.out.println("등록된 회원이 없습니다.");
			}
		
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e2) {
				System.out.println("오류 발생 : 관리자에게 문의하세요");
			}
		}
	}
	// 2. 회원 이름으로 조회
	public void searchMemberName() {
	 System.out.println("------ 회원 정보 조회(이름) ------");
	 // 사용자에게 입력 받은 데이터
	 System.out.print("조회할 회원 이름 입력 : ");
	 String name = sc.next();
	 
	 // DB연결을 위한 참조변수
	 Connection conn = null;
	 String query = null;
	 Statement stmt = null;
	 ResultSet rset = null;
	 
	 ArrayList<Member> list = new ArrayList<Member>();
	 
	 try {
		conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
		query = "SELECT * FROM CUSTOMER WHERE USER_NAME ='"+name+"'";
		stmt = conn.createStatement();
		rset = stmt.executeQuery(query);
		
		while(rset.next()) {
			Member mb = new Member();
			mb.setUserNo(rset.getInt("USER_NO"));
			mb.setUserId(rset.getString("USER_ID"));
			mb.setUserName(rset.getString("USER_NAME"));
			mb.setUserAge(rset.getInt("USER_AGE"));
			mb.setAddr(rset.getString("ADDR"));
			mb.setGender(rset.getString("GENDER"));
			mb.setEnrollDate(rset.getDate("ENROLL_DATE"));
			
			list.add(mb);
		}
		
		if(list.size()>0) {
			System.out.println("총 "+list.size()+"명의 회원이 조회되었습니다.");
			for(Member mb : list) {
				System.out.println("[ "+mb.getUserId()+"의 회원 정보 ]");
				System.out.println("이름 : "+mb.getUserName());
				System.out.println("나이 : "+mb.getUserAge());
				System.out.println("성별 : "+mb.getGender());
				System.out.println("주소 : "+mb.getAddr());
				System.out.println("등록일자 : "+mb.getEnrollDate());
			}
		}else {
			System.out.println("회원 정보가 없습니다.");
		}
	 } catch (SQLException e) {
		System.out.println("오류 발생 : 관리자에게 문의하세요.");
		e.printStackTrace();
	 }finally {
		 try {
			rset.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		}
		 
	 }
	 
	}
	
	// 3. 회원 아이디로 조회
	public void searchMemberId() {
		System.out.println("------ 회원 정보 조회(ID) ------");
		 // 사용자에게 입력 받은 데이터
		 System.out.print("조회할 회원 이름 입력 : ");
		 String id = sc.next();
		 
		 // DB연결을 위한 참조변수
		 Connection conn = null;
		 String query = null;
		 Statement stmt = null;
		 ResultSet rset = null;
		 
		 
		 try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT * FROM CUSTOMER WHERE USER_ID ='"+id+"'";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(!rset.next()) {
				System.out.println("회원 정보가 없습니다.");
				return;
			}
			
			
				Member mb = new Member();
				mb.setUserNo(rset.getInt("USER_NO"));
				mb.setUserId(rset.getString("USER_ID"));
				mb.setUserName(rset.getString("USER_NAME"));
				mb.setUserAge(rset.getInt("USER_AGE"));
				mb.setAddr(rset.getString("ADDR"));
				mb.setGender(rset.getString("GENDER"));
				mb.setEnrollDate(rset.getDate("ENROLL_DATE"));
				
				
		
				System.out.println("[ "+mb.getUserId()+"의 회원 정보 ]");
				System.out.println("이름 : "+mb.getUserName());
				System.out.println("나이 : "+mb.getUserAge());
				System.out.println("성별 : "+mb.getGender());
				System.out.println("주소 : "+mb.getAddr());
				System.out.println("등록일자 : "+mb.getEnrollDate());
				
		 } catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		 }finally {
			 try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
		 }
	}
	// 4. 회원 가입
	public void insertMember() {
		System.out.println("----- 회원 등록 -----");
		//USER_NO, USER_ID, USER_NAME, USER_AGE,
		// ADDR, GENDER , ENROLL_DATE
		Member mb = new Member();
		System.out.print("번호 : ");
		mb.setUserNo(sc.nextInt());
		System.out.print("아이디 : ");
		mb.setUserId(sc.next());
		System.out.print("이름 : ");
		mb.setUserName(sc.next());
		System.out.print("나이 : ");
		mb.setUserAge(sc.nextInt());
		System.out.print("주소 : ");
		if(sc.hasNextLine()) {
			sc.nextLine();
		}
		mb.setAddr(sc.nextLine());
		System.out.print("성별 : ");
		mb.setGender(sc.next());
		System.out.print("가입일 : ");
		mb.setEnrollDate(new java.sql.Date(new java.util.Date().getTime()));
		
		
		//DB에 저장
		Connection conn = null;
		String query = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "INSERT INTO CUSTOMER VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mb.getUserNo());
			pstmt.setString(2, mb.getUserId());
			pstmt.setString(3, mb.getUserName());
			pstmt.setInt(4, mb.getUserAge());
			pstmt.setString(5, mb.getAddr());
			pstmt.setString(6, mb.getGender());
			pstmt.setDate(7, mb.getEnrollDate());
			result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("회원 등록 완료");
			}else {
				System.out.println("회원 등록 실패");
			}
		
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
		}
		
	}
	
	// 5. 회원 정보 수정
	public void updateMember() {
		System.out.println("----- 회원 정보 수정 -----");
		System.out.println("수정할 회원 아이디 입력 : ");
		String id = sc.next();
		
		// DB 연결을 위한 참조변호
		Connection conn = null;
		String query = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
		
			//DB에서 회원번호로 회원 조회
			query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,id);
			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				System.out.println("회원 정보가 없습니다.");
				return;
			}
			
			Member mb = new Member(
				rset.getInt(1), rset.getString(2), rset.getString(3), rset.getInt(4)
				, rset.getString(5), rset.getString(6), rset.getDate(7));
			
			int select = 0;
			while (select !=4 ) {
				System.out.println("[ "+mb.getUserId()+"의 회원 정보 ]");
				System.out.println("회원 번호 : "+mb.getUserNo());
				System.out.println("이름 : "+mb.getUserName());
				System.out.println("등록일자 : "+mb.getEnrollDate());
				System.out.println("1.나이 ");
				System.out.println("2.주소 ");
				System.out.println("3.성별");
				System.out.println("4.수정 완료");
				System.out.print("수정할 항목 번호 : ");
				select = sc.nextInt();
				
				switch(select) {
				case 1:
					System.out.print("새로운 나이  : ");
					mb.setUserAge(sc.nextInt());
					break;
				case 2:
					System.out.print("새로운 주소 : ");
					if(sc.hasNextLine()) {
						sc.nextLine();
					}
					mb.setAddr(sc.nextLine());
					break;
				case 3:
					System.out.print("새로운 성별 : ");
					mb.setGender(sc.next());
					break;
				case 4:
					break;
				default :
					System.out.println("잘못된 번호를 입력하셨습니다.");
				}
				
			}
			pstmt.close();
			query = "UPDATE CUSTOMER SET USER_NO =?, USER_ID=?, USER_NAME=?"
					+ ",USER_AGE=?, ADDR=?, GENDER=?, ENROLL_DATE=? WHERE USER_ID =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mb.getUserNo());
			pstmt.setString(2,mb.getUserName());
			pstmt.setInt(3, mb.getUserAge());
			pstmt.setString(4, mb.getAddr());
			pstmt.setString(5, mb.getGender());
			pstmt.setDate(6, mb.getEnrollDate());
			pstmt.setString(7, mb.getUserId());
			result = pstmt.executeUpdate();
			if(result > 0) {
				System.out.println("회원 정보 수정 완료");
			}else {
				System.out.println("회원 정보 수정 실패");
			}
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		}
	}
	
	// 6. 회원 탈퇴
	public void deleteMember() {
		System.out.println("----- 회원 탈퇴 -----");
		// 사용자에게 입력 받은 데이터
		System.out.print("삭제할 회원 아이디 입력 : ");
		String id = sc.next();
		
		Connection conn = null;
		String query = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			// DB에서 아이디로 회원 조회
			query = "SELECT * FROM CUSTOMER WHERE USER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			
			if(!rset.next()) {
				System.out.println("회원 정보가 없습니다.");
				return;
			}
			System.out.println("[ "+rset.getString("USER_ID")+" 회원 정보 ]");
			System.out.println("이름 : "+rset.getString("USER_NAME"));
			System.out.println("등록 일자 : "+rset.getDate("ENROLL_DATE"));
			System.out.print("삭제하시겠습니까?(Y/N) : ");
			if(sc.next().charAt(0) == 'Y') {
				query = "DELETE FROM CUSTOMER WHERE USER_ID = '"+rset.getString("USER_ID")+"'";
				result = pstmt.executeUpdate(query);
				if(result>0) {
					System.out.println("회원 정보 삭제 완료");
				}else {
					System.out.println("회원 정보 삭제 실패");
				}
			}

		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e2) {
				System.out.println("오류발생 : 관리자에게 문의하세요");
			}
		}	
	}
	
	
	// 대여관리 서브메뉴
	public void rent() {
		/*
		1. 대여 관리 전체 조회
		2. 회원 아이디로 대여 조회
		3. 책 이름으로 대여 조회
		4. 대여정보 추가
		5. 메인 메뉴로 이동
	*/
		while(true) {
		System.out.println("##### 대여 관리 메뉴 #####");
		System.out.println("----- 메뉴 -----");
		System.out.println("1. 대여 관리 전체 조회");
		System.out.println("2. 회원 아이디로 대여 조회");
		System.out.println("3. 책 이름으로 대여 조회");
		System.out.println("4. 대여 정보 추가");
		System.out.println("5. 메인 메뉴로 이동");
		System.out.print("메뉴 선택 : ");
		int select = sc.nextInt();
		
		switch(select) {
		case 1:
			searchRent();
			break;
		case 2:
			searchRentMemberId();
			break;
		case 3:
			searchRentBookName();
			break;
		case 4:
			insertRent();
			break;
		case 5:
			return;
		default:
			System.out.println("번호를 다시 입력해주세요.");
		
			}
		}
	}
	// 1. 대여관리 전체 조회
	public void searchRent() {
		System.out.println("----- 대여 목록 조회 -----");
		
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;

		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");

			query = "SELECT LEASE_NO, USER_ID, USER_NAME, BOOK_NAME FROM LIBRARY "
					+ "LEFT OUTER JOIN CUSTOMER USING (USER_ID) "
					+ "LEFT OUTER JOIN BOOK USING (BOOK_NO)";
		
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			while(rset.next()) {
				System.out.println(rset.getInt("LEASE_NO")+" / "+rset.getString("USER_ID")
				+" / "+rset.getString("USER_NAME")+" / "+rset.getString("BOOK_NAME"));
			}			
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
			
		}
	}
	// 2. 회원 아이디로 대여 조회
	public void searchRentMemberId() {
		System.out.println("----- 대여 조회 (ID) -----");
		System.out.print("아이디 : ");
		String id = sc.next();
		
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT LEASE_NO, USER_ID, USER_NAME, BOOK_NAME FROM LIBRARY "
					+ "LEFT OUTER JOIN CUSTOMER USING (USER_ID) "
					+ "LEFT OUTER JOIN BOOK USING (BOOK_NO) WHERE USER_ID = '"+id+"'";
			
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(!rset.next()) {
				System.out.println("대여하고 있는 책이 없습니다.");
			}else {
				System.out.println("[ "+id+"님의 대여 정보]");

			System.out.println("대여 번호 : "+rset.getInt("LEASE_NO"));
			System.out.println("아이디 :"+rset.getString("USER_ID"));
			System.out.println("이름 : "+rset.getString("USER_NAME"));
			System.out.println("책 제목 : "+rset.getString("BOOK_NAME"));
			}
			} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
			
		}

	}
	// 3. 책 이름으로 대여 조회
	public void searchRentBookName() {
		System.out.println("----- 대여 조회 (책 제목) -----");
		System.out.print("책 제목 : ");
		sc.nextLine();
		String name = sc.nextLine();
		
		Connection conn = null;
		String query = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT LEASE_NO, USER_ID, USER_NAME, BOOK_NAME FROM LIBRARY "
					+ "LEFT OUTER JOIN CUSTOMER USING (USER_ID) "
					+ "LEFT OUTER JOIN BOOK USING (BOOK_NO) WHERE BOOK_NAME = '"+name+"'";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(!rset.next()) {
				System.out.println("대여 중인 회원이 없습니다.");
			}else {
				System.out.println("[ "+name+"의 대여 정보]");

			System.out.println("대여 번호 : "+rset.getInt("LEASE_NO"));
			System.out.println("아이디 :"+rset.getString("USER_ID"));
			System.out.println("이름 : "+rset.getString("USER_NAME"));
			System.out.println("책 제목 : "+rset.getString("BOOK_NAME"));
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
			
		}
	}
	// 4. 대여 정보 추가
	public void insertRent() {
		System.out.println("----- 대여 정보 추가 ------");
		System.out.print("대여 번호 입력 : ");
		int no = sc.nextInt();
		System.out.print("아이디 : ");
		String id = sc.next();
		System.out.print("책 제목 : ");
		sc.nextLine();
		String book = sc.nextLine();
		
		Connection conn = null;
		String query = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","STUDENT","STUDENTPASS");
			query = "SELECT BOOK_NO FROM BOOK WHERE BOOK_NAME =?";
			pstmt = conn.prepareStatement(query);		
			pstmt.setString(1, book);
			rset = pstmt.executeQuery();
			rset.next();
			
			query = "INSERT INTO LIBRARY VALUES (?, ?, ?, ?,?)";
			pstmt2 = conn.prepareStatement(query);
			pstmt2.setInt(1, no);
			pstmt2.setInt(2, rset.getInt("BOOK_NO"));
			pstmt2.setString(3, id);
			pstmt2.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
			pstmt2.setDate(5, new java.sql.Date(new java.util.Date().getTime()+2));
			result = pstmt2.executeUpdate();
			if(result > 0) {
				System.out.println("대여 정보 추가 완료");
			} else {
				System.out.println("대여 정보 추가 실패");
			}
			
		} catch (SQLException e) {
			System.out.println("오류 발생 : 관리자에게 문의하세요.");
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt2.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println("오류 발생 : 관리자에게 문의하세요.");
				e.printStackTrace();
			}
		}
		
	}
	
	
}
