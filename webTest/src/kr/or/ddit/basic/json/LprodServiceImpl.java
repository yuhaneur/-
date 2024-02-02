package kr.or.ddit.basic.json;

import java.util.List;

public class LprodServiceImpl implements ILprodService{
	LprodDaoImpl dao;
	
	private static LprodServiceImpl service;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getInstance();
	}
	
	public static LprodServiceImpl getInstance() {
		if(service==null) service = new LprodServiceImpl();
		return service;
	}

	@Override
	public List<LprodVO> getAllLprod() {
		return dao.getAllLprod();
	}
}
