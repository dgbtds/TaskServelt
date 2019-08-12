package wy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBCDAO.mysqlImp;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		mysqlImp mImp=new mysqlImp();
		String path=getServletContext().getRealPath("");
		System.out.println(path);
		System.out.flush();
		
		
		if(!mImp.testQuery("zhanghu",username)) {
			if(mImp.insert("zhanghu", username, password)) {
			
				System.out.println("register ----successed");
				//设置状态码? 重新定位 状态码
				response.setStatus(302);
				//定位跳转的位置是哪一个页面。
				response.setHeader("location", "login.html");
			}
			else {
				response.getWriter().write("register failed");
				System.out.println("register failed");
			}
		}
		else {
			response.getWriter().write("username is exsit,register failed!");
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
//	public boolean register(String username,String pwd) {		
//	}

}
