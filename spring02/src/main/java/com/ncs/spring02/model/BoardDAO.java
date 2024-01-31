package com.ncs.spring02.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ncs.spring02.domain.BoardDTO;

import lombok.AllArgsConstructor;


@Repository
public class BoardDAO {
	private static Connection cn = DBConnection.getConnection(); // CehckedException으로 try~catch 해줘야함. 익셉션 처리 해주지 않으면
	private static PreparedStatement pst;
	private static ResultSet rs;
	private static String sql;
	

	// List
	public List<BoardDTO> selectList() {
		sql = "select * from board order by root desc, step asc, seq desc";
		//=> 답글달기 추가 후 출력 순서 수정함
		try {
			pst=cn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			List<BoardDTO> list = new ArrayList<BoardDTO>();
			if(rs.next()) {
				do {
					BoardDTO dto = new BoardDTO();
					dto.setSeq(rs.getInt(1));
					dto.setId(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCnt(rs.getInt(6));
					dto.setRoot(rs.getInt(7));
					dto.setStep(rs.getInt(8));
					dto.setIndent(rs.getInt(9));
					
					list.add(dto);
				}while(rs.next());
			}
			return list;
			
		} catch (SQLException e) {
			System.out.println("selectList Error : => " + e.toString());
			return null;
		}
	}//selectList

	// Detail
	public BoardDTO selectOne(int seq) {
		sql = "select * from board where seq=" + seq;
		try {
			pst = cn.prepareStatement(sql);
			rs = pst.executeQuery();
			BoardDTO dto = new BoardDTO();
			if(rs.next()){
				dto.setSeq(rs.getInt(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getString(5));
				dto.setCnt(rs.getInt(6));
				dto.setRoot(rs.getInt(7));
				dto.setStep(rs.getInt(8));
				dto.setIndent(rs.getInt(9));
				
				return dto;
			} else {
				System.out.println("** selectOne : 출력 자료 없음");
				return null;
			}//if
		} catch (Exception e) {
			System.out.println("** selectOne Exception => "+ e.toString());
			return null;
		}//try
	}//selectOne

	// Insert : 원글 입력
    // => 입력 컬럼: id, title, content 
    //    default값: regdate, cnt, step, indent
    // => root : seq 와 동일한 값   
    // => Auto_Inc: seq (계산: auto 보다 IFNULL(max(seq)...) 를 적용)
	public int insert(BoardDTO dto) {
		sql = "Insert Into board Values((select * from (select IFNULL(max(seq),0)+1 from board) as temp),"
				+ "?,?,?, Current_TimeStamp ,0, (select * from (select IFNULL(max(seq),0)+1 from board) as temp), 0, 0)";
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception =>"+ e.toString());
			return 0;
		}
	}//insert
	
	//replyInsert : 답글 입력
	//=> seq: IFNULL 이용
	//=> 입력 컬럼 : id, title, content, root, step, indent
	//=> JDBD subQuery 구문 적용 시 주의사항
	//	-> MySql: select 구문으로 한번 더 씌워 주어야함(insert의 경우에도 동일)
	//=> stepUpdate가 필요함
	//	 댓글 입력 성공 후 실행
	//	 -> 현재 입력된 답글의 step 값은 수정되지 않도록 sql 구문의 조건 주의
	//=> boardList의 출력순서 확인
	//		~~~ order by root desc, step asc
	public int rinsert(BoardDTO dto) {
		sql = "Insert Into board(seq,id,title,content,root,step,indent) values("
				+"(select * from (select IFNULL(max(seq),0)+1 from board) as temp)"
				+ ",?,?,?,?,?,?)";
				
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getId());
			pst.setString(2, dto.getTitle());
			pst.setString(3, dto.getContent());
			pst.setInt(4, dto.getRoot());
			pst.setInt(5, dto.getStep());
			pst.setInt(6, dto.getIndent());
			pst.executeUpdate();	//답글 등록 성공-> stepUpdate
			System.out.println("**stepUpdate Count => " + stepUpdate(dto));
			return 1;
		} catch (Exception e) {
			System.out.println("** ReplyInsert Exception =>"+ e.toString());
			return 0;
		}
	}//rinsert
	
	//** stepUpdate : step 값 증가
	//=> 조건
	//	-> root 동일 and step >= and 새글은 제외
	public int stepUpdate(BoardDTO dto) {
		sql = "update board set step=step+1 where root>=? and step>=? "
				+ "and seq <> (select * from (select IFNULL(max(seq),0) from board) as temp)";
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getRoot());
			pst.setInt(2, dto.getStep());
			return pst.executeUpdate(); //수정된 Data 갯수 return
		} catch (Exception e) {
			System.out.println("** stepUpdate Exception =>"+ e.toString());
			return 0;
		}
	}//stepUpdate
	

	// Update
	public int update(BoardDTO dto) {
		sql = "Update board set title = ? , content =? , cnt = ? where seq = ?";
		
		try {
			pst = cn.prepareStatement(sql);
			pst.setString(1, dto.getTitle());
			pst.setString(2, dto.getContent());
			pst.setInt(3, dto.getCnt());
			pst.setInt(4, dto.getSeq());
			
			return pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("** insert Exception =>"+ e.toString());
			return 0;
		}
	}//update

	// Delete
	//=> seq로 삭제
	//=> 답글 추가 후 : 원글과 답글 구분
	//	-> 원글 : ~ where root=? (모든 답글도 동시에 삭제)
	//	-> 답글 : ~ where seq=?
	public int delete(BoardDTO dto) {
		if(dto.getSeq()==dto.getRoot()) {
			//원글 삭제
			sql = "Delete From board Where root =?";
		}else {
			//답글 삭제
			sql = "Delete From board Where seq =?";
		}
		try {
			pst=cn.prepareStatement(sql);
			pst.setInt(1, dto.getSeq());
			return pst.executeUpdate();
		}catch(Exception e) {
			System.out.println("** delete Exception => " + e.toString());
			return 0;
		}
	}//delete
}//class
