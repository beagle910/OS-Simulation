
public class Process {
	public PCB pcb;
	public int pid;
	public int processSize;
	public int page;
	public Process(int pid, int size,  ProcessState ps,Memory m) {
		processSize=size;
		page=size%m.framesize==0?size/m.framesize:size/m.framesize+1;
		//PCB(int pid,int page, int prgcounter,ProcessState ps,int linesDone,int pageDone,int Frame)
        this.pcb=new PCB(pid,page,0,ps,0,0,pid);
        this.pid=pid;
    }
}
