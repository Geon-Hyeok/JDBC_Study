package item;

import java.util.List;

public interface MainItemDAO {
	int insertItem(MainItemDTO mainItem);

	// 학생정보를 전달받아 STUDENT 테이블에 저장된 학생정보를 변경하고 변경행의 갯수를 반환하는 메소드
	int updateItem(MainItemDTO mainItem);

	// 학번을 전달받아 STUDENT 테이블에 저장된 학생정보를 삭제하고 삭제행의 갯수를 반환하는 메소드
	int deleteItem(String name);

	// 학번을 전달받아 STUDENT 테이블에 저장된 해당 학번의 학생정보를 검색하여 반환하는 메소드
	// => 단일행은 값 또는 DTO 객체 반환
	MainItemDTO selectItem(String name);

	// 이름을 전달받아 STUDENT 테이블에 저장된 해당 이름의 학생정보를 검색하여 반환하는 메소드
	// => 다중행은 List 객체 반환
	List<MainItemDTO> selectNameItemList(String champion);

	// STUDENT 테이블에 저장된 모든 학생정보를 검색하여 반환하는 메소드
	List<MainItemDTO> selectAllItemList();

	// 아이템 이름을 전달받아 ITEM테이블에 저장된 해당 아이템의 정보를 겁색하여 반환하는 메소드
	MainItemDTO selectItemPerformance(String name);

	// 상위아이템 이름을 전달받아 재료(하위아이템)을 검색하여 반환하는 메소드
	MainItemDTO findMaterial(String name);
}