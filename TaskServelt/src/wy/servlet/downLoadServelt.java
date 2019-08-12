package wy.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class downLoadServelt
 */
@WebServlet("/downLoadServelt")
public class downLoadServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String filename=request.getParameter("filename");
		String path=getServletContext().getRealPath("download/"+filename);
		
		//让浏览器收到这份资源的时候， 以下载的方式提醒用户，而不是直接展示。 
		response.setHeader("Content-Disposition", "attachment; filename="+filename);
		
		InputStream is=new FileInputStream(path);
		OutputStream os=response.getOutputStream();
		
		int len=0;
		byte[]buffer=new byte[1024];
		while((len=is.read(buffer))!=-1) {
			os.write(buffer,0,len);
		}
		is.close();
		os.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
