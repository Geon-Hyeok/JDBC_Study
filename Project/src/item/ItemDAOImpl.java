package item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl extends JdbcDAO implements MainItemDAO, SubItemDAO {
	private static ItemDAOImpl _dao;

	public ItemDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	static {
		_dao = new ItemDAOImpl();
	}

	public static ItemDAOImpl getDAO() {
		return _dao;
	}

	// 메인 아이템 삽입 기능
	@Override
	public int insertItem(MainItemDTO mainItem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into main values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mainItem.getName());
			pstmt.setInt(2, mainItem.getPrice());
			pstmt.setString(3, mainItem.getPerformance());
			pstmt.setString(4, mainItem.getMaterial());
			pstmt.setString(5, mainItem.getChampion());

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("에러 insertItem() 메소드의 SQL오류= " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 메인 아이템 변경 기능
	@Override
	public int updateItem(MainItemDTO mainItem) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "update main set price=?,performance=?,material=?,champion=? where name =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mainItem.getPrice());
			pstmt.setString(2, mainItem.getPerformance());
			pstmt.setString(3, mainItem.getMaterial());
			pstmt.setString(4, mainItem.getChampion());
			pstmt.setString(5, mainItem.getName());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateItem()메소드의 SQL오류= " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 메인 아이템 삭제 기능
	@Override
	public int deleteItem(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "delete from main where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteItem()메소드의 SQL오류 = " + e.getMessage());
		}
		return rows;
	}

	// 메인 아이템 검색 기능
	@Override
	public MainItemDTO selectItem(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainItemDTO mainItem = null;
		try {
			con = getConnection();

			String sql = "select * from main where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mainItem = new MainItemDTO();
				mainItem.setName(rs.getString("name"));
				mainItem.setPrice(rs.getInt("price"));
				mainItem.setPerformance(rs.getString("performance"));
				mainItem.setMaterial(rs.getString("material"));
				mainItem.setChampion(rs.getString("champion"));
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectItem()메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return mainItem;
	}

	// 해당 챔피언이 사용하는 메인 아이템 출력 기능
	@Override
	public List<MainItemDTO> selectNameItemList(String champion) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MainItemDTO> itemtList = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select name from main where champion like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + champion + "%");

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MainItemDTO mainItem = new MainItemDTO();
				mainItem.setName(rs.getString("name"));

				itemtList.add(mainItem);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectNameItemList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return itemtList;
	}

	// 모든 메인아이템 출력 기능
	@Override
	public List<MainItemDTO> selectAllItemList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MainItemDTO> itemList = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from Main";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				MainItemDTO mainItem = new MainItemDTO();
				mainItem.setName(rs.getString("name"));
				mainItem.setPrice(rs.getInt("price"));
				mainItem.setPerformance(rs.getString("performance"));
				mainItem.setMaterial(rs.getString("material"));
				mainItem.setChampion(rs.getString("champion"));

				itemList.add(mainItem);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectAllItemList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return itemList;
	}

	// 검색 메인 아이템의 효과 출력 기능
	@Override
	public MainItemDTO selectItemPerformance(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainItemDTO performance = null;
		try {
			con = getConnection();

			String sql = "select performance from main where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				performance = new MainItemDTO();

				performance.setPerformance(rs.getString("performance"));

			}

		} catch (SQLException e) {
			System.out.println("[에러]selectItemPerformance() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}

		return performance;
	}

	// 상위아이템 이름을 전달받아 재료(하위아이템)을 검색하여 반환하는 메소드
	@Override
	public MainItemDTO findMaterial(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MainItemDTO mainItem = null;
		try {
			con = getConnection();
			String sql = "select material from main where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				mainItem = new MainItemDTO();
				mainItem.setMaterial(rs.getString("material"));

			}

		} catch (SQLException e) {
			System.out.println("[에러]findMaterial() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return mainItem;
	}

	// 서브아이템 삽입 기능
	@Override
	public int insertSubItem(SubItemDTO sub) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "insert into sub values(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sub.getName());
			pstmt.setInt(2, sub.getPrice());
			pstmt.setString(3, sub.getPerformance());
			pstmt.setString(4, sub.getMainitem());
			pstmt.setString(5, sub.getChampion());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]insertSubItem() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 서브아이템 변경 기능
	@Override
	public int updateSubItem(SubItemDTO sub) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();
			String sql = "update sub set price=?,performance=?,mainitem=?,champion=? where name = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sub.getPrice());
			pstmt.setString(2, sub.getPerformance());
			pstmt.setString(3, sub.getMainitem());
			pstmt.setString(4, sub.getChampion());
			pstmt.setString(5, sub.getName());

			rows = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("[에러]updateSubItem() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 서브 아이템 삭제 기능
	@Override
	public int deleteSubItem(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int rows = 0;
		try {
			con = getConnection();

			String sql = "delete from sub where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]deleteSubItem() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}

	// 서브아이템 검색 기능
	@Override
	public SubItemDTO selectSubItem(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubItemDTO sub = null;
		try {
			con = getConnection();

			String sql = "select * from sub where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sub = new SubItemDTO();
				sub.setName(rs.getString("name"));
				sub.setPrice(rs.getInt("price"));
				sub.setPerformance(rs.getString("performance"));
				sub.setMainitem(rs.getString("mainitem"));
				sub.setChampion(rs.getString("champion"));
			}

		} catch (SQLException e) {
			// TODO: handle exception
		}
		return sub;
	}

	// 모든 서브아이템 출력
	@Override
	public List<SubItemDTO> selectAllSubItemList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SubItemDTO> subitemList = new ArrayList<>();
		try {
			con = getConnection();

			String sql = "select * from sub";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				SubItemDTO subitem = new SubItemDTO();
				subitem.setName(rs.getString("name"));
				subitem.setPrice(rs.getInt("price"));
				subitem.setPerformance(rs.getString("performance"));
				subitem.setMainitem(rs.getString("mainitem"));
				subitem.setChampion(rs.getString("champion"));

				subitemList.add(subitem);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectAllSubItemList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return subitemList;
	}

	// 서브아이템 가격 검색 기능
	@Override
	public SubItemDTO selectPriceSubItemList(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SubItemDTO sub = null;
		try {
			con = getConnection();

			String sql = "select price from sub where name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sub = new SubItemDTO();
				sub.setPrice(rs.getInt("price"));

			}
		} catch (SQLException e) {
			System.out.println("[에러]selectPriceSubItemList() 메소드의 SQL 오류 = " + e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return sub;
	}
}