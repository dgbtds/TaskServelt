package wy.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import JDBCDAO.mysqlImp;

/**
 * Servlet implementation class loginServelt
 */
@WebServlet("/loginServelt")
public class loginServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		mysqlImp mImp=new mysqlImp();
		if(mImp.testQueryLog("zhanghu", username, password)) {
			//response.getWriter().write("login successed");
			//设置状态码? 重新定位 状态码
			response.setStatus(302);
			//定位跳转的位置是哪一个页面。
			response.setHeader("location","download.html");
		}
		else {
			response.getWriter().write("login failed");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
