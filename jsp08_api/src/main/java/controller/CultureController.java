package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CultureService;

@WebServlet("*.culture")
public class CultureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("addr")) {
			String name = request.getParameter("name");
			
			CultureService cultureService = new CultureService();
			//검색한 곳 주소알아내기
			Map<String, Object> cmap = cultureService.cultureParsing(name);
			//알아낸 주소를 넘겨서 위도 경도 가져오기
			Map<String, Double> nmap = cultureService.geocoding((String)cmap.get("ADDR"));
			
			request.setAttribute("cmap", cmap);
			request.setAttribute("nmap", nmap);
			request.getRequestDispatcher("/view/cultureaddr.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
