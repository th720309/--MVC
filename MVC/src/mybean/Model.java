package mybean;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;



public class Model {
	String VERIFATIONURL = "http://jw.djtu.edu.cn/academic/getCaptcha.do";
   static String LOGINURL = "http://jw.djtu.edu.cn/academic/j_acegi_security_check";
  // static  String LOGINURL="http://jw.djtu.edu.cn/academic/index.jsp";
    String HOSTURL = "http://jw.djtu.edu.cn/academic/common/security/login.jsp";
    String MAINBODYHTML = "";
    public static String Cookie = "";
    String name;
	String password;
	String verifation;
	
	public void setName(String name)
	{
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password)
	{
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setVerifation(String verifation)
	{
		this.verifation=verifation;
	}
	
	public String getVerifation() {
		return verifation;
	}
	
	public void DoGetVerifation() {

       
                HttpPost httPost = new HttpPost(VERIFATIONURL);
                HttpClient client = new DefaultHttpClient();
                try {
                    HttpResponse httpResponse = client.execute(httPost);
                    Cookie = httpResponse.getFirstHeader("Set-Cookie").getValue();
                    byte[] bytes = new byte[1024];
                    bytes = EntityUtils.toByteArray(httpResponse.getEntity());
                    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);    
                    BufferedImage image =ImageIO.read(bais); 
                    int len;
                    // 输出的文件流
                   File sf=new File("/home/tianhao/study/test/MVC/WebContent/a.jpg");
                   if(!sf.exists()){
                    sf.mkdirs();
                   }
                   ImageIO.write(image, "JPEG", sf);   
                } catch (IOException e) {
                    e.printStackTrace();
                }

    }
    public String DoLogin(final String user, final String password, final String verifation) {
   
                DefaultHttpClient   defaultclient = new DefaultHttpClient();  

                //设置post参数
                List<NameValuePair> params = new ArrayList<NameValuePair>();
/*
                groupId
                        j_captcha
                04703
                j_password
                720309
                j_username
                1318180123
                login
                        登录*/
                params.add(new BasicNameValuePair("groupId", ""));
                params.add(new BasicNameValuePair("j_username", user));
                params.add(new BasicNameValuePair("j_password", password));
                params.add(new BasicNameValuePair("j_captcha", verifation));
                params.add(new BasicNameValuePair("login", "登录"));

                //获得个人主界面的HTML
                try {
                	 HttpPost httpPost = new HttpPost(LOGINURL);
                	 System.out.println( LOGINURL);
                     httpPost.setHeader("Cookie", Cookie);
                     HttpResponse httpResponse;
                     httpPost.setEntity((HttpEntity) new UrlEncodedFormEntity(params, HTTP.UTF_8));
                     httpResponse = defaultclient.execute(httpPost);
                     if (httpResponse.getStatusLine().getStatusCode() == 200) {
                          StringBuffer sb = new StringBuffer();
                          HttpEntity entity = httpResponse.getEntity();
                          MAINBODYHTML = EntityUtils.toString(entity);        
                         
                    }

                    if(httpResponse.getStatusLine().getStatusCode() == 302){
                    	String locationUrl=httpResponse.getLastHeader("Location").getValue();  
                        System.out.println(locationUrl);
                        LOGINURL=locationUrl;
                        DoLogin(user, password, verifation);
                    }                 
                    else{
                        StringBuffer sb = new StringBuffer();
                        HttpEntity entity = httpResponse.getEntity();
                        MAINBODYHTML = EntityUtils.toString(entity);
                       System.out.println( MAINBODYHTML);
                    }
                } catch (UnsupportedEncodingException e) {
                	
                    System.out.println("验证码不正确");
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                LOGINURL="http://jw.djtu.edu.cn/academic/j_acegi_security_check";
               // LOGINURL="http://jw.djtu.edu.cn/academic/index.jsp";
				return MAINBODYHTML; 
    }
    
    
/*   public void IsLoginSuccessful(String loginresult){
        org.jsoup.nodes.Document doc = Jsoup.parse(loginresult);
        //先判断是否登录成功，若成功直接退出
        Elements el = doc.select("div[id=error]");

        if(el.text().contains("您输入的验证码不正确")){
            System.out.println("验证码错误");

        }
        else if(el.text().contains("密码不匹配")) {
        	System.out.println( "密码不匹配");

        }
        else if(el.text().contains("用户名")){
        	System.out.println("用户名不存在");
        }

        else{
            System.out.println("登录成功");
        }
    }*/
}