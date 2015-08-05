package edu.nju.frs.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionSupport;

import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.UserService;

public class BaseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	public HttpSession session(){
		return ServletActionContext.getRequest().getSession();
	}
	
	public ServletContext application(){
        return ServletActionContext.getServletContext();
    }
	
    public HttpServletResponse response(){
        return ServletActionContext.getResponse();
    }
	@Override
    public String execute() throws Exception {
		return null;
	}
    
	public UserService userService(){
		ServletContext servletContext = ServletActionContext.getServletContext();
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        return (UserService)webApplicationContext.getBean("UserService"); 
	}
	
	public ProjectService projectService(){
		ServletContext servletContext = ServletActionContext.getServletContext();
        WebApplicationContext webApplicationContext= WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        return (ProjectService)webApplicationContext.getBean("ProjectService"); 
	}
	

}
