package jdbc01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLTimeoutException;
import java.sql.Statement;

// ** 순서
// 1) JDBC API 에 정의된 필요한 객체들을 전역변수 정의
// 2) CRUD 기능 메서드
// 3) main 에서 사용

public class DBStart {
	
	private static Connection cn = DBConnection.getConnection();
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	// ** StudentList 만들기
	// => MySql Command
	// 	-> Login -> DB선택 -> sql 구문 실행 -> 결과
	// => JDBC
	// 	-> Connection 객체 생성 -> sql 구문 실행 : Statement 또는 PreparedStatement
	//  -> 결과 : ResultSet 에 전달됨
	
	
	// ** 조별 List1
	// => Statement활용 : 매개변수를 활용한 조건문 추가
	public static void selectList() {
		
		sql="select * from student";
		try {
			st=cn.createStatement();
			rs=st.executeQuery(sql); // sql에 입력한 데이터가 들어있음
			// ** 결과 출력
			// => 결과 존재 확인
			// => ResultsSet 객체는 이를 위한 메서드 제공
			// => next() : 다음에 Data가 존재하면 true, 현재 Data를 제공
			System.out.println("   ** Student List **   ");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("| sno | name   | age  | jno  | info  | point");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			
			if(rs.next()) {
				// => selectList 결과 존재
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				}while(rs.next());
				
			}else {
				// => selectList 결과가 1건도 없음을 의미
				System.out.println("** selectList 결과가 1건도 없음 **");
			} //if_else
		} catch (Exception e) {
			System.out.println("** selectList Exception =>" + e.toString());
		} //try
	} // selectList
	
	
	// ** 조별 List3
	// => PreparedStatement 활용
	public static void joListPS(int jno) {
		
		sql = "select * from student where jno=?";
		try {
//			st=cn.createStatement();
//			rs=st.executeQuery(sql);
			
			
			pst=cn.prepareStatement(sql);
			pst.setInt(1, jno);
			rs=pst.executeQuery();			
			System.out.println("** Jo별 List =>" + jno);
			if(rs.next()) {
				do {
					System.out.print(rs.getInt(1)+" ");
					System.out.print(rs.getString("name")+" ");
					System.out.print(rs.getInt(3)+" ");
					System.out.print(rs.getInt(4)+" ");
					System.out.print(rs.getString(5)+" ");
					System.out.print(rs.getDouble(6)+"\n");
				}while(rs.next());
			}else {
				System.out.println("** joList 결과가 1건도 없음 **");
				
			}
		} catch (Exception e) {
			System.out.println("** joList Exception =>" + e.toString());
		}
	} // joList
	
	// ** insert (문제점)
	// => 입력에 필요한 컬럼을 모두 매개변수로 전달 받아야 함
	//	  많으면 처리 불편해짐 -> 객체화
	// 	  -> 엔티티(Table과 같음) -> Java Class로 객체화
	//	  -> ~DTO, VO, Entity(JPA)(Entity는 한참 뒤에 나중에 사용)
	// => sql 구문을 완성하기 위해서 문자열 연산을 작성해야 함
	// 	  insert into student(name, age, jno, info) values('홍길동', 30, 9, '관리자입니다.')
	// 	  "insert into student(name, age, jno, info) values('"
	//		+ name+"',"age.....
	// => 이 점을 보완하기 위해서 제공된 객체가 PreparedStatement
	//	  변수의 위치에 ?(바인딩변수) 를 사용
	//	  insert into student(name, age, jno, info) values(?,?,?,?)
	// 	  ? 에 대응값은 JavaCode로 처리(PreparedStatement 제공 메서드)
	
	public static void insert(String name, int age, int jno, String info) {
		sql="insert into student(name, age, jno, info) values(?,?,?,?)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, age);
			pst.setInt(3, jno);
			pst.setString(4, info);
			
//			int cnt = pst.executeUpdate(); // insert, update, delete 
//			if(cnt==1) System.out.println("** insert 성공 =>" + cnt);
//			else System.out.println("** insert 실패 =>" + cnt);
			
			if(pst.executeUpdate() > 0) System.out.println("** insert 성공 =>");
			else System.out.println("** insert 실패 =>");
			
			
		} catch (Exception e) {
			System.out.println("** insert Exception =>" + e.toString());
		} // try_catch
	}
	
	public static void main(String[] args) {
		// 1) Connection 확인
		// => toString()은 생략 가능
		//	  즉, 출력문에서 인스턴스명을 사용하면 toString() 를 호출하는 것
		System.out.println("** Connection 확인 =>" + cn.toString());
		
		// 2) StudentList 
//		selectList();
		
		// 3) 조별 List 출력
		joListPS(3);
		
		
		

	} //main

} //class
