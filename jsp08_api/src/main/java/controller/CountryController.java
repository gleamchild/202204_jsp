package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import dto.Country;
import service.CountryJSONService;

@WebServlet("*.country")
public class CountryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		CountryJSONService cService = new CountryJSONService();
		
		if(uri.contains("dbsave")) {
			//파싱후 db저장
			String iso = request.getParameter("iso");
			
			 try {
				 int cnt = cService.countryParsing(iso);
				 String msg =null;
				 if(cnt!=0) {
					 msg = cnt + "건 db저장";
				 }else {
					 msg = "저장실패";
				 }
				 response.sendRedirect(path+"/jspform.country?msg="+URLEncoder.encode(msg, "utf-8")+"&iso="+iso);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else if (uri.contains("jspform")) {
			//iso조회후 뷰에 뿌려주기
			
			//iso조회
			List<Map<String, String>> isolist = cService.selectList_iso();
			request.setAttribute("isolist", isolist);
			request.getRequestDispatcher("/view/countryList.jsp").forward(request, response);
		}else if(uri.contains("list")) {
			String iso = request.getParameter("iso");
			List<Country> clist = cService.selectList(iso);
			
			request.setAttribute("clist", clist);
			request.getRequestDispatcher("/jspform.country").forward(request, response);
		}else if(uri.contains("detail")) {
			String sfty_notice_id = request.getParameter("sfty_notice_id");
			
			Country country = cService.selectOne(sfty_notice_id);
			request.setAttribute("country", country);
			request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
