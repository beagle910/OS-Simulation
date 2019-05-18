import java.util.Arrays;

public class Memory {
	int[][] array;
	int framesize;
	int frames;
	public Memory(int frames,int framesize) {
		this.framesize=framesize;
		this.frames=frames;
		array=new int[frames][framesize];
		for (int[] row: array)
			Arrays.fill(row, 0);
	}
}
