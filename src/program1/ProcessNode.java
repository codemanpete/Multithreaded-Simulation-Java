package program1;

/**
 * A comparable node that represents a process; it has an id, a priority, and a time slice
 * @author Lydia McGovern
 */
public class ProcessNode implements Comparable<ProcessNode> {
    private long id; // Unique by timestamp
    private int priority;
    private int timeSlice;
    
    public ProcessNode(int priority, int timeSlice) {
        id = System.currentTimeMillis();
        this.priority = priority;
        this.timeSlice = timeSlice;
    }
    
    public long getId() {
        return id;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public void setTimeSlice(int timeSlice) {
        this.timeSlice = timeSlice;
    }
    
    public int getTimeSlice() {
        return timeSlice;
    }
    
    public int compareTo(ProcessNode p) {
//	System.out.println("(" + this.priority + "," + p.getPriority() + "," + (this.priority - p.getPriority()) + ")");
	return (this.priority - p.getPriority());
    }
}
