package app;

public class OperationListItem {
	
	Command command;
	String operationName;
	
	public OperationListItem(Command c, String o) {
		this.command = c;
		this.operationName = o;
	}

	public String toString() {
		return this.operationName;
	}
}
