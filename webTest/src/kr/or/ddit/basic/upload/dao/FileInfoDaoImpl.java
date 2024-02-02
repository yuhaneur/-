package kr.or.ddit.basic.upload.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.basic.upload.vo.FileinfoVO;
import kr.or.ddit.util.MybatisUtil;

public class FileInfoDaoImpl implements IFileInfoDao {

	private static FileInfoDaoImpl dao ;
	private FileInfoDaoImpl() {}
	public static FileInfoDaoImpl getInstance() {
		if(dao==null)dao=new FileInfoDaoImpl();
		return dao;
	}
	@Override
	public int insertFIleInfo(FileinfoVO fileVO) {
		int cnt = 0; //반환값이 저장될 변수
		SqlSession session= null;
		try {
			session= MybatisUtil.getSqlSession();
			cnt = session.insert("fileinfo.insertFileinfo", fileVO);
			if(cnt>0)session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		
		return cnt;
	}

	@Override
	public List<FileinfoVO> getAllFileInfo() {
		List<FileinfoVO> list = null;
		SqlSession session =null;
		try {
			session=MybatisUtil.getSqlSession();
			list = session.selectList("fileinfo.getAllFileInfo");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
			
		return list;
	}

	@Override
	public FileinfoVO getFileInfo(int fileNo) {
		SqlSession session = null;
		FileinfoVO fvo = null;
		try {
			session= MybatisUtil.getSqlSession();
			fvo= session.selectOne("fileinfo.getFileInfo", fileNo);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)session.close();
		}
		return fvo;
	}

}
