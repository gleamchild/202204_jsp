package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.file")
public class FileController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("upload")) {
			String savedir = "D:/AHJ/savedir"; //파일저장 경로
			int size = 1024 * 1024 * 10; //10mbyte
			//파일저장하기
			MultipartRequest multi = new MultipartRequest(request, savedir, size, "utf-8", new DefaultFileRenamePolicy());
			//저장된 파일이름 가져오기
			String filename = multi.getFilesystemName("file1");
			System.out.println("업로드한 파일:"+filename);
			
			//파일이름을 view로 전달
			request.setAttribute("filename", filename);
			request.getRequestDispatcher("/view/file/20220426_file.jsp").forward(request, response);
		}else if(uri.contains("download")) {
			String filename = request.getParameter("filename");
			System.out.println("다운로드할 파일:"+ filename);
			//다운로드할 경로 + 파일이름
			String downdir = "D:/AHJ/savedir";
			String fileurl = downdir + "/" + filename; //다운로드할 파일 url
			System.out.println(fileurl);
			
			//마임타입 : 파일의 타입
			String mimeType = getServletContext().getMimeType(filename);
			System.out.println(mimeType);
			//파일의 확장자가 없을경우 브라우저에 어떤 타입으로 보낼지 세팅
			if(mimeType == null) {
				mimeType = "application/octet-stream;charset=utf-8";
			}
			response.setContentType(mimeType); //브라우저에 어떤 타입으로 파일을 보낼지 세팅
			
			//첨부파일로 보내기
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
			
			//서버의 파일을 읽기 위한 스트림 생성
			FileInputStream fis = new FileInputStream(fileurl); //파일 입력스트림 생성
			ServletOutputStream out = response.getOutputStream(); //응답 출력스트림 얻기
			
			//반복문으로 파일을 읽어서 전송
			//파일 읽어서 저장할 배열
			byte[] b = new byte[4096]; //1024단위(1kbyte) 로 입력
			int numRead=0;
			//read() 반환값 : 읽어들일 바이트수, read(바이트배열, 읽을위치, 읽을길이)
			while((numRead = fis.read(b, 0, b.length)) != -1) {  //읽어들일 바이트가 -1 종료
				out.write(b, 0, numRead);
			}
			out.flush(); //마지막 버퍼를 비우기 위해 내보내기
			out.close();
			fis.close();
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
