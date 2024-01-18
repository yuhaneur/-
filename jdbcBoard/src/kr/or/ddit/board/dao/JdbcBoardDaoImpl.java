package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO1;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImpl implements IJbdcBoardDao {

	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao ==null)dao =new JdbcBoardDaoImpl();
		return dao;
	}
	@Override
	public int insertBoard(BoardVO1 boardVo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board("
					+ "board_no,board_title,board_writer,board_date,board_cnt,board_content)"
					+ "values(board_seq.nextval, ?, ? , sysdate ,0 , ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO1 boardVo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_title= ? , board_date = sysdate , board_content = ? "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
		
	}

	@Override
	public List<BoardVO1> getAllBoardList() {
		List<BoardVO1> boardList =null;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board" +
			" order by board_no desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			boardList = new ArrayList<BoardVO1>();
			while(rs.next()) {
					BoardVO1 boardVO1 = new BoardVO1();
					boardVO1.setBoard_no(rs.getInt("board_no"));
					boardVO1.setBoard_title(rs.getString("board_title"));
					boardVO1.setBoard_writer(rs.getString("board_writer"));
					boardVO1.setBoard_date(rs.getString("board_date"));
					boardVO1.setBoard_cnt(rs.getInt("board_cnt"));
					boardVO1.setBoard_content(rs.getString("board_content"));
					
					boardList.add(boardVO1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public BoardVO1 getBoard(int boardNo) {
		BoardVO1 boardVO1 =null;
		
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board " +
			" where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				boardVO1 = new BoardVO1();
				boardVO1.setBoard_no(rs.getInt("board_no"));
				boardVO1.setBoard_title(rs.getString("board_title"));
				boardVO1.setBoard_writer(rs.getString("board_writer"));
				boardVO1.setBoard_date(rs.getString("board_date"));
				boardVO1.setBoard_cnt(rs.getInt("board_cnt"));
				boardVO1.setBoard_content(rs.getString("board_content"));
					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
			if(rs!=null)try {rs.close();}catch(SQLException e) {}
		}
		return boardVO1;
	}

	@Override
	public List<BoardVO1> getSearchBoardList(String title) {
		   List<BoardVO1> boardList = null;  //반환값이 저장될 변수
		      Connection conn = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      
		      try {
		         conn = DBUtil3.getConnection();
		         String sql = "select * from jdbc_board "
		                  + " where board_title like '%' || ? || '%'"
		                  + " order by board_no desc ";
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, title);
		         
		         rs = pstmt.executeQuery();
		         
		         boardList = new ArrayList<BoardVO1>();
		         while(rs.next()) {
		            BoardVO1 boardVo = new BoardVO1();
		            boardVo.setBoard_no(rs.getInt("board_no"));
		            boardVo.setBoard_title(rs.getString("board_title"));
		            boardVo.setBoard_writer(rs.getString("board_writer"));
		            boardVo.setBoard_date(rs.getString("board_date"));
		            boardVo.setBoard_cnt(rs.getInt("board_cnt"));
		            boardVo.setBoard_content(rs.getString("board_content"));
		            
		            boardList.add(boardVo);
		         }
		               
		      } catch (SQLException e) {
		         e.printStackTrace();
		      }finally {
		         if(rs!=null) try {rs.close();} catch(SQLException e) {}
		         if(pstmt!=null) try {pstmt.close();} catch(SQLException e) {}
		         if(conn!=null) try {conn.close();} catch(SQLException e) {}
		      }
		      
		      return boardList;
	}

	@Override
	public int updateBoardCount(int boardNo) {
		int cnt = 0; // 반환값이 저장될 변수
		Connection conn =null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set "
					+ " board_cnt = board_cnt + 1 "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null)try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null)try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

}
