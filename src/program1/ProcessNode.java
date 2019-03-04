package program1;

/**
 * A comparable node that represents a process; it has an id, a priority, and a time slice; the priority makes it comparable
 * @author Lydia McGovern
 */
public class ProcessNode implements Comparable<ProcessNode> {
    private long id; // "Unique" by random number
    private int priority;
    private int timeSlice;
    
/**
 * The constructor for ProcessNode
 * @param priority The priority for this process
 * @param timeSlice The time slice this process takes to run
 */
    public ProcessNode(int priority, int timeSlice) {
        id = (int) (Math.random() * 10000); // This technically isn't unique, but it works for this small task
        this.priority = priority;
        this.timeSlice = timeSlice;
    }
    
/**
 * Accessor for the id
 * @return the id
 */
    public long getId() {
        return id;
    }
    
/**
 * Mutator for the priority
 * @param priority the new priority for the process
 */
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
/**
 * Accessor for the priority
 * @return the priority
 */
    public int getPriority() {
        return priority;
    }
    
/**
 * Mutator for the time slice
 * @param timeSlice the new time slice for the process
 */
    public void setTimeSlice(int timeSlice) {
        this.timeSlice = timeSlice;
    }
    
/**
 * Accessor for the time slice
 * @return the time slice
 */
    public int getTimeSlice() {
        return timeSlice;
    }
    
/**
 * The compare function for this comparable object; compares priorities
 * @param p the other ProcessNode this node is to be compared with
 * @return the difference between this node's priority and the the parameter node's priority
 */
    public int compareTo(ProcessNode p) {
//	System.out.println("(" + this.priority + "," + p.getPriority() + "," + (this.priority - p.getPriority()) + ")");
	return (this.priority - p.getPriority());
    }
}
