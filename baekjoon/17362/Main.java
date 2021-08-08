import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] f = new int[] { 2, 1, 2, 3, 4, 5, 4, 3 };
		System.out.println(f[N % 8]);
	}

}
