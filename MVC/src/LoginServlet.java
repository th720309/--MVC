
import mybean.*;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.client.ClientProtocolException;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		Model dataBean = null;
        HttpSession session = request.getSession(true);
		response.setContentType("text/html;charset=GB2312");//设置响应的MIME类型。  
		java.io.PrintWriter out = response.getWriter(); 
		 try {
	            dataBean = (Model) session.getAttribute("ok");
	            if (dataBean == null) {
	                dataBean = new Model(); // 创建Javabean对象
	                session.setAttribute("show", dataBean);// 将dataBean存储到session对象中
	            }
	        } catch (Exception exp) {
	            dataBean = new Model(); // 创建Javabean对象
	            session.setAttribute("", dataBean);// 将dataBean存储到session对象中
	        }
		String name=request.getParameter("name");//获取视图里的name  
		String password=request.getParameter("password");//获取视图里的password  
		String verifation = request.getParameter("verifation");
		dataBean.setName(name);
		dataBean.setPassword(password);  
		dataBean.setVerifation(verifation);
		String MAINHTML = dataBean.DoLogin(name,password,verifation);
	    org.jsoup.nodes.Document doc = Jsoup.parse(MAINHTML);
	        //先判断是否登录成功，若成功直接退出
	        Elements el = doc.select("div[id=error]");

	        if(el.text().contains("您输入的验证码不正确")){
	            out.println("<script language='javascript'>alert('验证码错误！');</script>"); 
	        }
	        else if(el.text().contains("密码不匹配")) {
	        	out.println("<script language='javascript'>alert('密码不匹配！');</script>"); 
	        }
	        else if(el.text().contains("用户名")){
	        	out.println("<script language='javascript'>alert('用户名不存在！');</script>"); 
	        }

	        else{
	        	RequestDispatcher dispatcher = request
	                    .getRequestDispatcher("shousucess.jsp");
	            dispatcher.forward(request, response);
	        }
		}  
	

}
