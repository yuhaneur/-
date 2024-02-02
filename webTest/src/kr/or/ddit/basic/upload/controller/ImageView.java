package kr.or.ddit.basic.upload.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.basic.upload.service.FileInfoServiceImpl;
import kr.or.ddit.basic.upload.service.IFileInfoService;
import kr.or.ddit.basic.upload.vo.FileinfoVO;

@WebServlet("/images/imageView.do")
public class ImageView extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 파라미터로 넘어온 파일 번호 구하기
		String strFileno = request.getParameter("fileno");
		int fileNo = Integer.parseInt(strFileno);
		
		// 파일 번호에 해당하는 파일 정보를 DB에서 가져온다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		FileinfoVO fvo = service.getFileInfo(fileNo);
		
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 생성한다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		String imgPath = uploadPath + File.separator + fvo.getSave_file_name();
		File file = new File(imgPath);
		
		if(file.exists()) {	// 이미지 파일이 있을 때...
			BufferedInputStream bin = null;
			BufferedOutputStream bout = null;
			
			try {
				// 출력용
				bout = new BufferedOutputStream(response.getOutputStream());
				// 파일 입력용
				bin = new BufferedInputStream(new FileInputStream(file));
				
				byte[] temp = new byte[1024];
				int len =0;
				while((len = bin.read(temp))>0) {
					bout.write(temp, 0, len);
				}
				bout.flush();
			} catch (Exception e) {
				System.out.println("파일 입출력 오류 : " + e.getMessage());
			} finally {
				if(bin!=null)try {bin.close();}catch(IOException e) {}
				if(bout!=null)try {bout.close();}catch(IOException e) {}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
