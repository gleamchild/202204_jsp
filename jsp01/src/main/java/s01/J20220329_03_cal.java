package s01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/J20220329_03_cal")
public class J20220329_03_cal extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		double first = Double.parseDouble(request.getParameter("first"));
		double second =Double.parseDouble(request.getParameter("second"));
		String sign = request.getParameter("sign");
		double result=0;
		switch (sign) {
		case "+" :
			result = first + second;
			break;
		case "-" :
			result = first - second;
			break;
		case "*" :
			result = first * second;
			break;
		case "/" :
			result = first / second;
			break;
		default :
			System.out.println("기호 에러");
		}
		System.out.println(result);
		
//		request.setAttribute("result", result);
//		request.getRequestDispatcher("/view/servlet/20220329_03_redirect.jsp").forward(request, response);
		
		//redirect방식
		//contextpath포함 해야됨 - 다른패키지로 이동가능
		response.sendRedirect("/jsp01/view/servlet/20220329_03_redirect.jsp?result="+result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
