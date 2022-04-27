package s01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//확장자 패턴

@WebServlet("*.do")
public class J20220329_02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		StringBuffer url = request.getRequestURL();
		System.out.println(uri);
		System.out.println(url);
		
		int width = Integer.parseInt(request.getParameter("width"));
		System.out.println("가로" + width);
		int height = Integer.parseInt(request.getParameter("height"));
		System.out.println("세로" + height);
		double area = 0.5 * width * height;
		System.out.println(area);
		//forward방식 이동
		//일단 정보저장
		request.setAttribute("width", width);
		request.setAttribute("height", height);
		request.setAttribute("area", area);
		request.getRequestDispatcher("/view/servlet/20220329_02_mapping.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
