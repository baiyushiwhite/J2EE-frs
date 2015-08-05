package edu.nju.frs.action;


import edu.nju.frs.service.ProjectService;

public class UpdateTaskAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProjectService projectService;
	private String task;
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	public String execute() throws Exception{
		String message = null;
		task = this.getRequest().getParameter("task");
		String projectId = this.getRequest().getParameter("projectId");
		String userId = this.getRequest().getParameter("userId");
		
		message = projectService.updateProjectUserTask(userId, projectId, task);
		if(message != null){
			projectService.sentErrorMessage(message, this.getRequest(), this.response());
			task = null;
			return SUCCESS;
		}
		return SUCCESS;
	}

}
