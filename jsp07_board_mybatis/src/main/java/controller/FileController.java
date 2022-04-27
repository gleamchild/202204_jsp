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

@WebServlet("/file/*")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri =request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("download")) {
			String filename = request.getParameter("filename");
			String downdir = getServletContext().getInitParameter("savedir");
			String fileurl = downdir + "/" + filename;
			
			String mimeType = getServletContext().getMimeType(filename);
			//파일의 확장자가 없을경우 브라우저에 어떤 타입으로 보낼지 세팅
			if(mimeType == null) { 
				mimeType = "application/octet-stream;charset=utf-8";
			}
			response.setContentType(mimeType);
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
			
			FileInputStream fis = new FileInputStream(fileurl);
			ServletOutputStream out = response.getOutputStream();
			
			byte[] b = new byte[2048];
			int numRead=0;
			while((numRead = fis.read(b, 0, b.length)) != -1) {
				out.write(b, 0, numRead);
			}
			out.flush();
			out.close();
			fis.close();
			
		}
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
