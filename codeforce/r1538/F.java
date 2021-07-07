import java.io.*;
import java.util.*;

public class F {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while (TC-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int l = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			long sum = 0;
			int i = 0;
			while (r != 0) {
				sum += ((r % 10) + (r % 10) * (int)(Math.pow(10, i)) + i);
				r /= 10;
				i++;
			}
			i = 0;
			while (l != 0) {
				sum -= ((l % 10) + (l % 10) * (int)(Math.pow(10, i)) + i);
				l /= 10;
				i++;
			}
			bw.append(sum).append("\n");	
		}
		System.out.print(bw);
	}

}