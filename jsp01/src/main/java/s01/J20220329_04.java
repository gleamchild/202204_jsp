package s01;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/itemsales")
public class J20220329_04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri); //매핑이 잘 되었는지 확인
	
		String iname = request.getParameter("iname");	
		int price = Integer.parseInt(request.getParameter("price"));	
		int qty = Integer.parseInt(request.getParameter("qty"));	
		System.out.println(iname);
		
		int result = price * qty;
		System.out.println(result);
		
		//변수의 인코딩 방식을 바꿔줌?
		iname = URLEncoder.encode(iname, "utf-8");
		response.sendRedirect("/jsp01/view/servlet/20220329_04_sales.jsp?result="+result+"&iname="+iname);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
