package framework.bo;

public class ReportBO {

	private String testCaseName;
	private String parameters;
	private String status;
	private String description;
	private String stacktrace;
	private String module;
	private String startTime;
	private String endTime;
	
	@Override
	public String toString() {
		return "ReportBO [testCaseName=" + testCaseName + ", parameters=" + parameters + ", status=" + status
				+ ", description=" + description + ", stacktrace=" + stacktrace + ", module=" + module + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStacktrace() {
		return stacktrace;
	}

	public void setStacktrace(String stacktrace) {
		this.stacktrace = stacktrace;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
