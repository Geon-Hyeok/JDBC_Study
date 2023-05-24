package Item;

import java.util.List;

public interface MainItemDAO {
	int insertMainItem(SubItemDAO student);

	// 아이템 정보를 전달받아 STUDENT 테이블에 저장된 아이템정보를 변경하고 변경행의 갯수를 반환하는 메소드
	int updateMainItem(SubItemDAO student);

	// 아이템 이름을 전달받아 STUDENT 테이블에 저장된 아이템를 삭제하고 삭제행의 갯수를 반환하는 메소드
	int deleteMainItem(String name);

	// 아이템 이름을 전달받아 STUDENT 테이블에 저장된 해당 학번의 아이템 정보를 검색하여 반환하는 메소드
	// => 단일행은 값 또는 DTO 객체 반환
	SubItemDAO selectMainItem(String name);

	// 이름을 전달받아 메인아이템 테이블에 저장된 해당 이름의 아이템정보를 검색하여 반환하는 메소드
	// => 다중행은 List 객체 반환
	List<SubItemDAO> selectNameMainItemList(String name);
	
	// 챔피언 이름을 전달받아 메인아이템 테이블에 저장된 해당 이름의 챔피언이 사용하는 아이템 정보를 반환하는 메소드
	List<SubItemDAO> selectChampionList(String name);

	// Main 테이블에 저장된 모든 아이템 정보를 검색하여 반환하는 메소드
	List<SubItemDAO> selectAllMainItemList();
}
