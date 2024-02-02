package kr.or.ddit.basic.upload.service;

import java.util.List;

import kr.or.ddit.basic.upload.dao.FileInfoDaoImpl;
import kr.or.ddit.basic.upload.vo.FileinfoVO;

public class FileInfoServiceImpl implements IFileInfoService{
	private static FileInfoServiceImpl service ;
	private FileInfoDaoImpl dao;
	private FileInfoServiceImpl() {
		dao = FileInfoDaoImpl.getInstance();
	}
	public static FileInfoServiceImpl getInstance() {
		if(service==null)service=new FileInfoServiceImpl();
		return service;
	}
	@Override
	public int insertFIleInfo(FileinfoVO fileVO) {
		return dao.insertFIleInfo(fileVO);
	}

	@Override
	public List<FileinfoVO> getAllFileInfo() {
		return dao.getAllFileInfo();
	}

	@Override
	public FileinfoVO getFileInfo(int fileNo) {
		return dao.getFileInfo(fileNo);
	}

}
