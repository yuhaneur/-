package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.board.vo.BoardVO1;
import kr.or.ddit.util.MybatisUtil;

public class JdbcBoardDaoImpl implements IJbdcBoardDao {

	private static JdbcBoardDaoImpl dao;
	
	private JdbcBoardDaoImpl() {}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao ==null)dao =new JdbcBoardDaoImpl();
		return dao;
	}
	@Override
	public int insertBoard(BoardVO1 boardVo) {
		int insertCnt = 0 ;
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			insertCnt = session.insert("board.insertBoard", boardVo);
			if(insertCnt>0) {
				session.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return insertCnt;
	}

	@Override
	public int updateBoard(BoardVO1 boardVo) {
		int updateCnt = 0;
		
		SqlSession session = null;
		
		try {
			session= MybatisUtil.getSqlSession();
			updateCnt=	session.update("board.updateBoard", boardVo);
			if(updateCnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return updateCnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int delCnt = 0;
		SqlSession session = null;
		try {
			session=MybatisUtil.getSqlSession();
			delCnt = session.delete("board.deleteBoard", boardNo);
			if(delCnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return delCnt;
		
	}

	@Override
	public List<BoardVO1> getAllBoardList() {
		List<BoardVO1> list = new ArrayList<BoardVO1>();
		SqlSession session = null;
		try {
			session= MybatisUtil.getSqlSession();
			list = session.selectList("board.selectAll");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null) session.close();
		}
		return list;
	}

	@Override
	public BoardVO1 getBoard(int boardNo) {
		SqlSession session = null;
		BoardVO1 vo = null;
		try {
			session=MybatisUtil.getSqlSession();
			vo = session.selectOne("board.selectOne", boardNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		return vo;
	}

	@Override
	public List<BoardVO1> getSearchBoardList(String title) {
		   List<BoardVO1> boardList = null;  //반환값이 저장될 변수
		   SqlSession session = null;
		   try {
			session = MybatisUtil.getSqlSession();
			boardList = session.selectList("board.searchList", title);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		      
		 return boardList;
	}

	@Override
	public int updateBoardCount(int boardNo) {
		int cnt = 0; // 반환값이 저장될 변수
		SqlSession session = null;
		try {
			session = MybatisUtil.getSqlSession();
			cnt = session.update("board.updateBoardCount",boardNo);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session!=null)session.close();
		}
		
		
		return cnt;
	}

}
