package s01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 디렉토리 패턴
// contextpath제외
// /jsp01/test : uri 여기에서 contextpath제외하고 아래에 적는다
@WebServlet("/J20220328_01")
public class J20220328_01 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //post방식일때 한글 인코딩 문제 때문에
		String uri = req.getRequestURI();
		System.out.println(uri);
		String name = req.getParameter("name");
		System.out.println(name);
		int age = Integer.parseInt(req.getParameter("age"));
		System.out.println(age);
		//forward : 주소가 안바뀜, 대량의 데이터 전송가능
		//redirect : 주소가 바뀜, 대량의 데이터 전송불가
		
		//forward방식 이동
		req.setAttribute("name", name);
		req.setAttribute("age", age);
		//contextpath제외 해야한다(다른 프로젝트로는 이동할 수 없기 때문에)
		//절대경로 : /view/servlet/20220328_01_mapping.jsp 앞에 /의 유무?
		//상대경로 : view/servlet/20220328_01_mapping.jsp
		req.getRequestDispatcher("/view/servlet/20220328_01_mapping.jsp").forward(req, resp);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//get, post처리 방식이 같기때문에 코드의 중복을 제거하기 위해 doGet을 호출
		doGet(req, resp);
	}
}

