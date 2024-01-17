package kr.or.ddit.board.dao;

import java.awt.image.DataBufferUShort;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements BoardDao{
	private static BoardDaoImpl dao ;
	
	private BoardDaoImpl() {}
	
	public static BoardDaoImpl getInstance() {
		if(dao ==null) dao= new BoardDaoImpl();
		return dao;
	}
	
	
	// 게시글 저장
	@Override
	public int insertBoard(BoardVO boardVO) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "insert into jdbc_board values(board_seq.nextval,?,?,sysdate,0,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_title());
			pstmt.setString(2, boardVO.getBoard_writer());
			pstmt.setString(3, boardVO.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set BOARD_TITLE=?,BOARD_CONTENT=? where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVO.getBoard_title());
			pstmt.setString(2, boardVO.getBoard_content());
			pstmt.setInt(3, boardVO.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int board_no) {
		
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where BOARD_NO="+board_no;
			
			pstmt = conn.prepareStatement(sql);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> boardList() {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board order by board_no desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_content(rs.getString("board_content"));
				boardVO.setBoard_date(rs.getString("board_date"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				
				list.add(boardVO);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}

	@Override
	public List<BoardVO> selectBoard(int board_no) {
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_no="+board_no;
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setBoard_no(board_no);
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_content(rs.getString("board_content"));
				boardVO.setBoard_date(rs.getString("board_date"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				
				list.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}

	public List<BoardVO> selectTitle(String surch) {
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select * from jdbc_board where board_title like '"+surch+"%'";
			
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BoardVO boardVO = new BoardVO();
				boardVO.setBoard_no(rs.getInt("board_no"));
				boardVO.setBoard_title(rs.getString("board_title"));
				boardVO.setBoard_writer(rs.getString("board_writer"));
				boardVO.setBoard_cnt(rs.getInt("board_cnt"));
				
				list.add(boardVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
		}
		return list;
	}
	//조회수 변경
	@Override
	public int updateCnt(int board_no) {
		Connection conn = null;
		PreparedStatement pstmt= null;
		
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "update jdbc_board set board_cnt=board_cnt+1 where board_no="+board_no;
			
			pstmt = conn.prepareStatement(sql);
		
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
		}
		return cnt;
	}

}
