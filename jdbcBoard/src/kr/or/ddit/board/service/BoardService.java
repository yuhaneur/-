package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface BoardService {

public int insertBoard(BoardVO boardVO);
	
	public int updateBoard(BoardVO boardVO);
	
	public int deleteBoard(int board_no);
	
	public List<BoardVO> boardList();
	
	public List<BoardVO> selectBoard(int board_no);
	
	public List<BoardVO>  selectTitle(String surch);
	
	public int updateCnt(int board_no);
}
