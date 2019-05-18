import java.util.LinkedList;

public class ReadyQueue {
	public LinkedList<PCB> queue;
	public ReadyQueue() {
		queue=new LinkedList<PCB>();
	}
	public void add(PCB pcb) {
    	//if pcb is ready then add to the queue
		if(pcb.state ==ProcessState.READY) {
        	this.queue.add(pcb);
        }
    }
	public PCB remove() {
        return queue.remove();
    }
	public boolean isEmpty() {
        return queue.isEmpty();
    }
   
}
