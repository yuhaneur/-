package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements BoardService{
	
	 
	BoardDaoImpl dao = BoardDaoImpl.getInstance();
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {}
	
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		return service;
	}
	
	@Override
	public int insertBoard(BoardVO boardVO) {
		return dao.insertBoard(boardVO);
	}

	@Override
	public int updateBoard(BoardVO boardVO) {
		return dao.updateBoard(boardVO);
	}

	@Override
	public int deleteBoard(int board_no) {
		return dao.deleteBoard(board_no);
	}

	@Override
	public List<BoardVO> boardList() {
		return dao.boardList();
	}

	@Override
	public List<BoardVO> selectBoard(int board_no) {
		return dao.selectBoard(board_no);
	}

	@Override
	public List<BoardVO> selectTitle(String surch) {
		return dao.selectTitle(surch);
	}

	@Override
	public int updateCnt(int board_no) {
		return dao.updateCnt(board_no);
	}

}
