
public class CPU {
	public int LINES_OF_CYCLE;
    public boolean isIdle;
    public Memory memory;
    Process[] jobQueue;
    public int totalCPUTime=0;
    
    public CPU(int LINES_OF_CYCLE, boolean b,Memory m,Process[] jobQueue) {
    	this.LINES_OF_CYCLE=LINES_OF_CYCLE;
    	this.isIdle=b;
    	this.memory=m;
    	this.jobQueue=jobQueue;
    	this.totalCPUTime=m.frames-1;
    }
    public boolean exec(PCB pcb) {
    	//increase cpu time;
    	this.totalCPUTime++;
    	System.out.println("Working on Process "+pcb.pid);
    	//change cpu from idle to !idle; change processtate from ready to running;
    	isIdle = false;
    	this.updatePState(pcb);
    	//finish a cpu cycle: update pcb: change process state and program counter
    	System.out.println("Finish the cpu cycle on Process "+pcb.pid);
    	System.out.println("Update PCB "+pcb.pid);
    	this.updatePState(pcb);
    	//this.addCycle(pcb);
    	if(!this.addCycle(pcb)) {
    		return false;
    	}
    	return true;
    	
    }
    
    public boolean addCycle(PCB pcb) {
    	int n=this.LINES_OF_CYCLE;
    	//cpu is working on instructions on memory line by line and update the pcb:
    	//linesDone;pageDone;programCounter;
    	while(n>0) {
    		if(pcb.linesDone<this.jobQueue[pcb.pid].processSize) {
    			pcb.linesDone++;
    		}
    		//done a process
    		else {
    			System.out.println("Process"+pcb.pid+" is Finished!");
    			emptyFrame(pcb);
    			return false;
    		}
    		//work on the page;
    		if(pcb.programCounter<memory.framesize-1) {
    			pcb.programCounter++;
    			n--;
    		}
    		//work on a new page;
    		else {
    			pcb.frameLocation=changePage(pcb);
    			if(pcb.pageDone<pcb.page)
    				pcb.pageDone++;
    			pcb.programCounter=0;
    			System.out.println("Page: "+pcb.pageDone);
    		}
    	}
    	return true;
    	
    }
    //update pcb
    public void updatePState(PCB p) {
		if(p.state==ProcessState.RUNNING)
			p.state=ProcessState.READY;
		else 
			p.state=ProcessState.RUNNING;
	}
    //load a new page to memory
    public int loadToMemory() {
    	int res=0;
    	for(int i=0;i<this.memory.frames;i++) {
    		if(this.memory.array[i][0]==0) {
    			for(int j=0;j<this.memory.framesize;j++) {
    				this.memory.array[i][0]=1;
    			}
    			res= i;
    		}
    	}
    	return res;
    }
    //after done a page, empty the frame
    public void emptyFrame(PCB pcb) {
    	for(int j=0;j<this.memory.framesize;j++) {
    		this.memory.array[pcb.frameLocation][j]=0;
    	}
    }
    //load to a frame
    public int changePage(PCB pcb) {
    	emptyFrame(pcb);
    	int f=loadToMemory();
    	System.out.println("#########Load another page to Memory Frame"+f+" #########");
    	return f;
    }
}
