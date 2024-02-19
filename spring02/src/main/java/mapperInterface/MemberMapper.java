package mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ncs.spring02.domain.MemberDTO;

import pageTest.SearchCriteria;

public interface MemberMapper {
	
		//** JUnit Test
		//=> selectDTO, xml 대신 @으로 sql 구현
		@Select("select * from member where id=#{id}")	
		MemberDTO selectDTO(MemberDTO dto);
		
		// => @Param 적용 Test
	    //   -> 기본규칙: Mybatis에서는 매개변수 Type은 무관하지만, 갯수는 1개만 허용
	    //   -> @Param: mapper에서 #{...} 적용, 복수갯수 사용 가능 (단, 기본자료형 사용불가_JUnit에서는 가능 
		@Select("select * from member where id=#{ii} AND jno=#{jno}")
		MemberDTO selectParam(@Param("ii") String id, @Param("jno") int jno);
		
		//Member Check_List
		public List<MemberDTO> mCheckList(SearchCriteria cri);
		public int mCheckRowsCount(SearchCriteria cri);
		
		//Member Saerch_List
		public List<MemberDTO> mSearchList(SearchCriteria cri);
		public int mSearchRowsCount(SearchCriteria cri);
		
		//Member Paging
		public List<MemberDTO> mPageList(SearchCriteria cri);
		public int mtotalRowsCount(SearchCriteria cri);
	
		//** selectList
		public List<MemberDTO> selectList();
		
		//** selectOne
		public MemberDTO selectOne(String id);
		
		//** insert
		public int insert(MemberDTO dto);
		
		//** update
		public int update(MemberDTO dto);
		
		//** Password_Update
		public int pwUpdate(MemberDTO dto);
		
		//** delete
		public int delete(String id);
		
		//** selectJoList
		public List<MemberDTO> selectJoList(String jno);
	
	
	
	

}
