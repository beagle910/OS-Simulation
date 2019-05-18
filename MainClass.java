import java.util.Scanner;
public class MainClass {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Process number:");
        int p = Integer.parseInt(in.nextLine());
        System.out.println("Lines per cpu time:");
        int c=  Integer.parseInt(in.nextLine());
        System.out.println("Frame size:");
        int s=  Integer.parseInt(in.nextLine());
		OperatingSystem os=new OperatingSystem(p,c,s);
		
		System.out.println("*******************************************");
		
		os.run();
	}
}
