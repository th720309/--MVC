
import mybean.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.accessibility.internal.resources.accessibility;

/**
 * Servlet implementation class VerfiationServlet
 */
@WebServlet("/VerfiationServlet")
public class VerfiationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public VerfiationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=GB2312");//设置响应的MIME类型。  
		java.io.PrintWriter out = response.getWriter();      
		Model newModel=new Model();//调用模型  
		newModel.DoGetVerifation();
		out.printf("<html><img src=\"/WebContent/a.jpg\"></img></html>");
	}

}
