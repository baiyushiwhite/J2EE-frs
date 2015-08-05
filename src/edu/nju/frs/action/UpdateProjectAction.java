package edu.nju.frs.action;

import java.sql.Date;
import java.util.Calendar;

import edu.nju.frs.service.ProjectService;

public class UpdateProjectAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private String projectId;
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public String execute() throws Exception{
		String message = "";
		projectId = this.getRequest().getParameter("projectId");
		
		String beginDateString = this.getRequest().getParameter("from");
		String endDateString = this.getRequest().getParameter("to");
		
		String [] beginStringArray = beginDateString.split("-");
		int beginMonth = Integer.parseInt(beginStringArray[0]);
		int beginDate = Integer.parseInt(beginStringArray[1]);
		int beginYear = Integer.parseInt(beginStringArray[2]);
		
		String [] endStringArray = endDateString.split("-");
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
		message = projectService.updateProject(projectId, projectName, projectDescription, hostId, expense, beginTime, endTime);
		
		if(message!=null){
			projectService.sentErrorMessage(message, this.getRequest(), this.response());
			return SUCCESS;
		}
		
		return SUCCESS;
	}

}
