package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Covid;
import service.CovidXMLService;

@WebServlet("*.covid")
public class CovidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CovidXMLService covidService = new CovidXMLService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("list")) {
			List<Covid> covidList = covidService.selectList(request.getParameter("startDt"), request.getParameter("endDt"));
			
			request.setAttribute("covidList", covidList);
			request.getRequestDispatcher("/view/covidList.jsp").forward(request, response);
		}else if(uri.contains("dbsave")) {
			String startDt = request.getParameter("startDt");
			String endDt = request.getParameter("endDt");
			String msg = covidService.covidParsing(startDt, endDt);
			
			response.sendRedirect(path+"/view/covidList.jsp?msg="+URLEncoder.encode(msg, "utf-8")+"&startDt="+URLEncoder.encode(startDt, "utf-8")+"&endDt="+URLEncoder.encode(endDt, "utf-8"));
		}
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
