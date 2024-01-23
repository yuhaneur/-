package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.IJbdcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO1;

public class JdbcBoardServiceImple implements IJdbcBoardService {

	private IJbdcBoardDao dao;
	
	private static JdbcBoardServiceImple service;
	
	private JdbcBoardServiceImple() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImple getInstance() {
		if(service==null) service = new JdbcBoardServiceImple();
		return service;
	}
	
	@Override
	public int insertBoard(BoardVO1 boardVo) {
		return dao.insertBoard(boardVo);
	}

	@Override
	public int updateBoard(BoardVO1 boardVo) {
		return dao.updateBoard(boardVo);
	}

	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}

	@Override
	public List<BoardVO1> getAllBoardList() {
		return dao.getAllBoardList();
	}

	@Override
	public BoardVO1 getBoard(int boardNo) {
		// 조회수를 먼저 증가한 다음 게시글 정보를 가져온다.
		
		if(dao.updateBoardCount(boardNo) ==0) { // 조회수 증가 작업이 실패하면...
			return null;
		};
		
		return dao.getBoard(boardNo);
	}

	@Override
	public List<BoardVO1> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}

	@Override
	public int updateBoardCount(int boardNo) {
		return dao.updateBoardCount(boardNo);
	}

}
