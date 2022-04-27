package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import dto.Air;
import service.AirService;

@WebServlet("*.air")
public class AirController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	AirService airService = new AirService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("add")) {
			String msg="";
			String year = request.getParameter("year").substring(0, 4);
			String itemCode  = request.getParameter("itemCode");
			int cnt;
			try {
				cnt = airService.airParsing(year, itemCode);
				if(cnt != 0) {
					msg = cnt+"건 추가완료!";
				}else {
					msg = "저장된 자료입니다";
				}
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			} catch (IOException | ParseException e) {
			e.printStackTrace();
			}
			response.sendRedirect(path+"/view/airList.jsp?msg="+URLEncoder.encode(msg, "utf-8"));
		}else if(uri.contains("selectList")) {
			String districtName = request.getParameter("districtName");
			List<Air> alist = airService.selectList(districtName);
			
			request.setAttribute("alist", alist);
			request.getRequestDispatcher("/view/airList.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
