package kr.or.ddit.basic.upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import kr.or.ddit.basic.upload.service.FileInfoServiceImpl;
import kr.or.ddit.basic.upload.service.IFileInfoService;
import kr.or.ddit.basic.upload.vo.FileinfoVO;

/*
	- Servlet 3.0이상에서 파일 업로드를 하려면 
		서블릿에 @MultipartConfig 애노테이션을 설정해야 한다.
	
	-@MultipartConfig 애노테이션의 설정해야할 속성들...
	1) location : 업로드한 파일이 임시적으로 저장될 경로 지정(기본값 : "")
	2) fileSizeThreshold : 이 속성에 지정한 값보다 큰 파일이 전송되면 location속성에
				지정한 임시 경로에 저장된다.(단위 : byte, 기본값 : 0(무조건 파일로 저장))
	3) maxFileSize : 1개 파일의 최대 크기 설정 ( 단위 :byte, 기본값 :-1(무제한))
	4) maxRequestSize : 서버로 전송되는 Request데이터 전체의 최대 크기 설정
						(모든 파일의 크기의 합 + formData )
						(단위 : byte, 기본값 :-1(무제한))

*/


@WebServlet("/fileUpload.do")
@MultipartConfig(
	fileSizeThreshold = 1024*1024*10, maxFileSize = 1024*1024*30,
	maxRequestSize = 1024*1024*100	)
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GET방식으로의 요청이 오면 Upload Form화면이 출력되도록 한다.
		request.getRequestDispatcher("/basic/uploadFile/fileUpload.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// 업로드된 파일들이 저장될 폴더 설정 (서버 컴퓨터 기준)
		String uploadPath = "d:/d_other/uploadFiles";
		
		// 저장될 폴더가 없으면 새로 생성한다.
		File f = new File(uploadPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		//----------------------------------------------------
		
		// 파일이 아닌 일반 데이터는 getParameter()메서드나 
		// getParameterValues() 메서드를 이용해서 구한다.
		String userName = request.getParameter("username");
		System.out.println("일반 파라미터 데이터 ==>" + userName);
		
		//----------------------------------------------------
		// 수신 받은 파일 데이터 처리하기
		
		// upload한 파일이 여러 개 일 경우에 파일 목록이 저장될 List객체 생성
		List<FileinfoVO> fileList = new ArrayList<FileinfoVO>();
		
		/*
		- 서블릿 3.0이상에서 새롭게 추가된 Upload용 메서드
		1) Request객체.getParts() ==> 전체 Part객체를 Collection객체에 담아서 반환한다.
		2) Request객체.getPart("이름") ==> 지정된 '이름'을 가진 개별 Part객체를 반환한다.
								'이름' ==> <form>태그 안에 입력요소의 name속성값으로 구별한다.
		*/
		
		// 전체 Part객체 개수만큼 반복 처리
		for(Part part : request.getParts()) {
			String fileName = extractFileName(part); //upload한 파일이름 구하기
			
			// 찾은 파일이름이 null이면 파일이 아닌 이반 파라미터 데이터가 된다.
			if(fileName!=null) { // 파일인지 검사
				
				// 1개의 파일정보가 저장될 FileInfoVo객체 생성
				FileinfoVO fvo = new FileinfoVO();
				
				fvo.setFile_writer(userName); // 작성자를 VO객체에 저장
				fvo.setOrigin_file_name(fileName); // 원래의 파일명을 VO객체에 저장
				
				// 실제 저장되는 파일 이름이 중복되는 것을 방지학 ㅣ위해서 UUID객체를
				// 이용하여 저장할 파일명을 만든다.
				String saveFileName = UUID.randomUUID().toString() + "_"+fileName;
				
				// 새로 만든 파일명을 VO객체에 '저장파일명'으로 저장
				fvo.setSave_file_name(saveFileName);
				
				// Part객체.getSize() ==> Upload된 파일의 크기 반환(단위 :byte)
				
				// byte단위의 파일 크기를 KB단위로 변환해서 VO에 저장
				
				fvo.setFile_size((long)(Math.ceil(part.getSize()/1024.0)));
				
				try {
					// Upload된 파일을 지정한 '업로드 폴더'에 저장하기
					// 저장하는 메서드 ==> Part객체.write()메서드 이용
					part.write(uploadPath + File.separator + saveFileName);
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				// Upload된 파일 정보를 List에 추가하기(DB에 저장하기위해)
				fileList.add(fvo);
			} // if문 끝...
		}	// for문 끝...
		
		// List에 추가된 파일 정보들을 DB에 저장한다.
		IFileInfoService service = FileInfoServiceImpl.getInstance();
		
		for(FileinfoVO fvo : fileList) {
			service.insertFIleInfo(fvo);
		}
		
		// 저장이 완료된 후 파일 목록을 보여준다.
		response.sendRedirect(request.getContextPath() + "/fileList.do");
		
		
	}// doPOst()메서드 끝...
	
	// Part의 구조 
	/*
	1) 파일이 아닌 일반 데이터일 경우
	-------------23423ㄱawfef23f2a2f 	==> Part들을 구분한 구분선
	content-disposition : form-data; name="username"  	==> 파라미터 이름
							==> 빈줄
	a001					==> 파라미터 값
	*/
	
	/*
	2) 파일일 경우
	-------------23423ㄱawfef23f2a2f 	==> Part들을 구분한 구분선
	content-disposition : form-data; name="upFile1"; filename="test.txt"==> 파일 정보
	content-type : text/plain			==> 파일의 종류
										==> 빈줄
	1234abcd안녕							==> 파일의 내용
			
	 */
	
	// Part 구조 안에서 파일명을 찾는 메서드
	public String extractFileName(Part part) {
		String fileName = null; 	//반환값(파일명) 이 저장될 변수
		String dispostionStr = part.getHeader("content-disposition");
		String[] itemArr = dispostionStr.split(";");
		
		for(String item : itemArr) {
			if(item.trim().startsWith("filename")) {
				//filename="test1.txt"
				fileName = item.substring(item.indexOf("=")+2,item.length()-1);
				break;
			}
		}
		
		return fileName;
	}
	
}
