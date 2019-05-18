public class OperatingSystem {
	int ProcessNumber;
	int cpuLinesPerCycle;
	int framesize;
	int frames;
	Memory memory;
	Process[] jobQueue;
	CPU cpu;
	ReadyQueue readyQueue;
	public OperatingSystem(int ProcessNumber, int cpuLinesPerCycle, int framesize)
	{
		this.ProcessNumber=ProcessNumber;
		this.cpuLinesPerCycle=cpuLinesPerCycle;
		this.framesize=framesize;
		this.frames=ProcessNumber;
		jobQueue=new Process[ProcessNumber];
		memory=new Memory(ProcessNumber,framesize);
		jobQueue=new Process[ProcessNumber];
		cpu=new CPU(cpuLinesPerCycle,true,memory,jobQueue);
		readyQueue=new ReadyQueue();
	}

	public void run( ) {
		//initial the whole state: create n processes; create the job queue and readyQueue;
		//each of the process load a page to memory frames
		for(int i=0;i<this.ProcessNumber;i++) {
			Process p=new Process(i, 100+20*i, ProcessState.READY,memory);
			readyQueue.add(p.pcb);
			jobQueue[i]=p;
			System.out.println("Create a new Process "+p.pid+": "
			+"size: "+p.processSize);
			for(int j=0;j<memory.framesize;j++) {
				memory.array[i][j]=1;
			}
			System.out.println("Load page 0 of Process"+p.pid+": "
					+"to Memory Frame "+i);
		}
		System.out.println("********************************");
		System.out.println("********************************");
		//work on the ready q until it is empty
		while(!readyQueue.isEmpty()) {
			System.out.println("*******************************************");
			PCB pcb=readyQueue.remove();
			//System.out.println("cpu exec:"+cpu.exec(pcb));
			if(cpu.exec(pcb)) {
				System.out.println("Add Process"+pcb.pid+" on the back of the readyQueue ");
				readyQueue.add(pcb);
			}
			
		 }
		System.out.println("********************************");
		System.out.println("********************************");
		System.out.println("********************************");
		System.out.println("Congratulations! All Done!");
		System.out.println("Total CPU time: "+cpu.totalCPUTime);
	}

}
