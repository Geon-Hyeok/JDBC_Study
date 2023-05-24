package item;

import java.util.List;

public interface SubItemDAO {

	// 아이템 정보를 전달받아 해당 아이템 정보 삽입
	int insertSubItem(SubItemDTO sub);

	// 아이템 정보를 전달받아 해당 아이템 정보 수정
	int updateSubItem(SubItemDTO sub);

	// 아이템 이름을 전달받아 해당 아이템 정보 삭제
	int deleteSubItem(String name);

	// 아이템 이름을 전달받아 해당 아이템의 정보 출력
	SubItemDTO selectSubItem(String name);

	// 아이템 이름 검색시 해당 아이템의 가격 출력
	SubItemDTO selectPriceSubItemList(String name);

	// 모든 하위템 목록 출력
	List<SubItemDTO> selectAllSubItemList();

}