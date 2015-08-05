package edu.nju.frs.action;

import java.sql.Date;
import java.util.Calendar;

import edu.nju.frs.service.ProjectService;
import edu.nju.frs.service.ProjectUserService;

/**
 * 新建一个项目 【由系统管理员来操作】
 */
public class CreateProjectAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	
	private ProjectService projectService;
	private ProjectUserService projectUserService;
	private String info;
	public String getInfo() {
		return info;
	}



	public void setInfo(String info) {
		this.info = info;
	}



	public String getError() {
		return error;
	}



	public void setError(String error) {
		this.error = error;
	}


	private String error;

	public String execute() throws Exception{
		
		String message = "";
		String beginDateString = this.getRequest().getParameter("from");
		String endDateString = this.getRequest().getParameter("to");
		
		String [] beginStringArray = beginDateString.split("/");
		int beginMonth = Integer.parseInt(beginStringArray[0]);
		int beginDate = Integer.parseInt(beginStringArray[1]);
		int beginYear = Integer.parseInt(beginStringArray[2]);
		
		String [] endStringArray = endDateString.split("/");
		int endMonth = Integer.parseInt(endStringArray[0]);
		int endDate = Integer.parseInt(endStringArray[1]);
		int endYear = Integer.parseInt(endStringArray[2]);
		
		String projectName = this.getRequest().getParameter("projectName");
		String projectDescription = this.getRequest().getParameter("description");
		String hostId = this.getRequest().getParameter("hostId");
		String expenseString = this.getRequest().getParameter("expense");
		double expense = Double.parseDouble(expenseString);
		
		Calendar beginCalendar = Calendar.getInstance();
		beginCalendar.set(Calendar.YEAR, beginYear);
		beginCalendar.set(Calendar.MONTH, beginMonth - 1);
		beginCalendar.set(Calendar.DAY_OF_MONTH, beginDate);
		Date beginTime = new Date(beginCalendar.getTimeInMillis());
		
		Calendar endCalendar=Calendar.getInstance();
		endCalendar.set(Calendar.YEAR, endYear);
		endCalendar.set(Calendar.MONTH, endMonth - 1);
		endCalendar.set(Calendar.DAY_OF_MONTH, endDate);
		Date endTime = new Date(endCalendar.getTimeInMillis());
		
		message = projectService.createProject(projectName, projectDescription, hostId, expense, beginTime, endTime);
		
		if(message!=null){
			projectService.sentErrorMessage(message, this.getRequest(), this.response());
			error = message;
			return SUCCESS;
		}
		
		info = "create project success!";
		return SUCCESS;
	}
	
	

	public ProjectService getProjectService() {
		return projectService;
	}
	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public ProjectUserService getProjectUserService() {
		return projectUserService;
	}


	public void setProjectUserService(ProjectUserService projectUserService) {
		this.projectUserService = projectUserService;
	}
	
	

}
