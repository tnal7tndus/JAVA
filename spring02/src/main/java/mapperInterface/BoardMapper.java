package mapperInterface;

import java.util.List;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardMapper {

		//Board_Paging
		public List<BoardDTO> bPageList(Criteria cri);
		public int totalRowsCount(Criteria cri);
	
	
		// List
		public List<BoardDTO> selectList() ;

		// Detail
		public BoardDTO selectOne(int seq) ;

		// Insert
		public int insert(BoardDTO dto) ;
		
		//replyInsert
		public int rinsert(BoardDTO dto) ;
		
		//stepUpdate
		public int stepUpdate(BoardDTO dto) ;

		// Update
		public int update(BoardDTO dto) ;

		// Delete
		public int delete(BoardDTO dto) ;
	
	
	
}
