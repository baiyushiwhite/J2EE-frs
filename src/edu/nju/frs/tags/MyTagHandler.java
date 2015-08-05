package edu.nju.frs.tags;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class MyTagHandler extends TagSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException{
	    Object value;
	    System.out.println("doTag");
	    HttpServletRequest request = (HttpServletRequest) super.pageContext.getRequest();
	    HttpServletResponse response = (HttpServletResponse) super.pageContext.getResponse();
	    value = super.pageContext.getSession().getAttribute("user");
	    System.out.println(value);
	    if(value==null){
	    	try {
	    		RequestDispatcher dispater=request.getRequestDispatcher(response.encodeURL("/user/welcome.jsp"));
	    		dispater.forward(request,response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }else{
	    }
		return TagSupport.SKIP_BODY;
	}
  
}
