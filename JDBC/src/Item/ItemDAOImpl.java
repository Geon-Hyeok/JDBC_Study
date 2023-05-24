package Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ItemDAOImpl extends JdbcDAO implements MainItemDAO, SubItemDAO {

	private static ItemDAOImpl _Dao;

	private ItemDAOImpl() {

	}

	static {
		_Dao = new ItemDAOImpl();
	}

	public static ItemDAOImpl getDAO() {
		return _Dao;
	}

	@Override
	public int insertSubItem(SubItemDAO sub) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSubItem(SubItemDAO sub) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSubItem(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SubItemDAO selectSubItem(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SubItemDAO selectPriceSubItemList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubItemDAO> selectMaterialItemList(String mainitem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubItemDAO> selectAllSubItemList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMainItem(SubItemDAO student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMainItem(SubItemDAO student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMainItem(String name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SubItemDAO selectMainItem(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubItemDAO> selectNameMainItemList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubItemDAO> selectChampionList(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SubItemDAO> selectAllMainItemList() {
		// TODO Auto-generated method stub
		return null;
	}

}
