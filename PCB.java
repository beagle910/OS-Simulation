
public class PCB {
	public int pid;
	public int page;
    public int programCounter;
    public ProcessState state;
    public int linesDone;
    public int pageDone;
    public int frameLocation;
    
    public PCB(int pid,int page, int prgcounter,ProcessState ps,int linesDone,int pageDone,int frameLocation) {
        this.pid = pid;
        this.page=page;
        this.programCounter=prgcounter;
        this.state = ps;
        this.linesDone=linesDone;
        this.frameLocation=frameLocation;
    }
}
