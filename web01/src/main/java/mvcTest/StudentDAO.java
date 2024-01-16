package mvcTest;

// import 입력하는 단축키 ctrl + shift + o
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//** DAO(Data Access Object)
//=> SQL 구문 처리
//=> CRUD 구현 
//    Create(Insert), Read(selectList, selectOne), Update, Delete

//** 첫번째 예제 DBStart 와 ~~~DAO 의 차이점
//=> 결과를 직접 처리하지 않고 요청자에게 제공해야 함
//=> 즉, 메서드 역할별로 처리결과를 return 해야 함
//=> 그러므로 특히 select 결과를 잘 전달하기 위해서 결과를 객체화 해야 함

public class StudentDAO {
	// ** 전역변수 정의 //인터페이스(interface)
	private static Connection cn = DBConnection.getConnection(); // CehckedException으로 try~catch 해줘야함. 익셉션 처리 해주지 않으면
																	// 컴파일에러
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	
	//**Join Test
	//=> son, name, age, jno jname, project, captain, 조장이름 출력하기
	//=> JoDTO 작성, joinList() 메서드작성( Controller, service, DAO )
	
	public List<StudentDTO> joinList(){
		sql = "select sno, name, age, s.jno, jname, project, captain"
				+ " FROM student s JOIN jo j"
				+ " ON s.jno = j.jno";
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			if(rs.next()) {
				do {
					
				StudentDTO dto = new StudentDTO();
				dto.setSno(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setJname(rs.getString(5));
				dto.setProject(rs.getString(6));
				dto.setCaptain(rs.getInt(7));
				list.add(dto);
				} while(rs.next());
				return list;				
			}else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("**joinList Exception => " + e.toString());
			return null;
		}
		
	}
	
	// ** selectList
	public List<StudentDTO> selectList() { // static 생략
		sql = "select * from student";
		List<StudentDTO> list = new ArrayList<StudentDTO>(); // 제네릭
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery(); // select쿼리를 실행
			// => 결과의 존재 여부
			// => 존재: list에 담기
			// => 없음: return null
			if (rs.next()) {
				do {
					StudentDTO dto = new StudentDTO();
					dto.setSno(rs.getInt(1));
					dto.setName(rs.getNString(2));
					dto.setAge(rs.getInt(3));
					dto.setJno(rs.getInt(4));
					dto.setInfo(rs.getString(5));
					dto.setPoint(rs.getDouble(6));

//             StudentDTO dto = new StudentDTO(rs.getInt(1), rs.getNString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6));

					list.add(dto);
				} while (rs.next());
				return list;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("**selectList Exception => " + e.toString());
			return null;
		}
	}// selectList

	// ** selectOne
	// => 기본자료형 매개변수 _ Call By Value
	public StudentDTO selectOne(int sno) {
		sql = "SELECT * FROM student WHERE sno=?";

		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);
			rs = pst.executeQuery();

			if (rs.next()) {
				StudentDTO dto = new StudentDTO();
				dto.setSno(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getString(5));
				dto.setPoint(rs.getDouble(6));
				return dto;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("** selecOne Exception => " + e.toString());
			return null;
		}

	}// selectOne
	// => 비교 Test
	// => 참조자료형 매개변수 TEST _ Call By Reference
	public void selectOne2(StudentDTO dto) {
		sql = "SELECT * FROM student WHERE sno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, dto.getSno());
			rs = pst.executeQuery();

			if (rs.next()) {
				dto.setName(rs.getString(2));
				dto.setAge(rs.getInt(3));
				dto.setJno(rs.getInt(4));
				dto.setInfo(rs.getString(5));
				dto.setPoint(rs.getDouble(6));
			} else {
				System.out.println("** Student 없음 **");
			}
		} catch (Exception e) {
			System.out.println("** selecOne2 Exception => " + e.toString());
		}
	}// selectOne2
	// 원칙은 메서드명이 같을 수 없지만, 매개변수가 다른경우에는 오버로딩 되어서
    // 메서드명이 같아도 정의만 다르게 사용 가능하다.

	// ** insert
	// => 입력 컬럼: name, age, jno, info
	public int insert(StudentDTO dto) {
		sql = "insert into student(name,age,jno,info) values(?,?,?,?)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getName());
			pst.setInt(2, dto.getAge());
			pst.setInt(3, dto.getJno());
			pst.setString(4, dto.getInfo());

			// int count = pst.executeUpdate();
			// return count;
			// => 비교
			return pst.executeUpdate(); // 처리갯수

		} catch (Exception e) {
			System.out.println("** insert Exception => " + e.toString());
			return 0;
		}
	} // insert

	// ** update
	// => info, point 수정
	public int update(StudentDTO dto) {
		sql = "update student set info=?, point=? where sno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getInfo());
			pst.setDouble(2, dto.getPoint());
			pst.setInt(3, dto.getSno());

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** update Exception => " + e.toString());
			return 0;
		}
	} // update

	// ** delete
	// => sno 로 삭제
	public int delete(int sno) {
		sql = "delete from student where sno=?";
		try {
			pst = cn.prepareStatement(sql);
			pst.setInt(1, sno);

			return pst.executeUpdate(); // 처리갯수
		} catch (Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	} // delete
	
	
   // ** Transaction Test
   // => Connection 객체가 관리
   // => 기본값은 AutoCommit  true 임.
   // => setAutoCommit(false) -> commit 또는 rollback 
   // => Test 사항
   //   - 동일자료를 2번 입력 -> 2번째 입력에서 p.key 중복 오류발생 

   // 1) Transaction 적용전
   // => 동일자료를 2번 입력
   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생 
   //   - Rollback 불가능
   //   - MySql Command 로 1번째 입력 확인 가능 
      
   // 2) Transaction 적용후 
   // => 동일자료를 2번 입력 
   //   - 1번째는 입력완료 되고, 2번째 입력에서 p.key 중복 오류발생
   //   - Rollback 가능 -> 둘다 취소됨

	public void transactionTest() {
		sql = "insert into student values (33, '홍길동', 99, 9, 'Transaction 적용전', 999.99)";
		
		//1) Transaction 적용 전
//		try {
//			pst=cn.prepareStatement(sql);
//			pst.executeUpdate();// 1번째는 Table에 입력 완료
//			pst.executeUpdate();// 2번째는 p.key 중복오류 발생->catch 블럭으로 이동
//		} catch (Exception e) {
//			System.out.println("** Transaction 적용전 =>" + e.toString());
//		}
		
		//2) Transaction 적용 후
		try {
			cn.setAutoCommit(false);// Start Transaction
			pst=cn.prepareStatement(sql);
			pst.executeUpdate();// 1번째는 입력완료 되었지만 Buffer에 보관
			pst.executeUpdate();// 2번째는 p.key 중복오류 발생-> catch 블럭으로 이동-> Rollback
			cn.commit();
		} catch (Exception e) {
			System.out.println("** Transaction 적용후 =>" + e.toString());
			//=>Rollback
			try {
				cn.rollback();
				System.out.println("** Rollback 성공 **" + e.toString());
			} catch (Exception e2) {
				System.out.println("** Rollback Eception =>" + e.toString());
			}
		}//try_catch
	}// transactionTest
	
	
	
} // class