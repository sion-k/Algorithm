import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String[] croatia = {
		"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="
	};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder(br.readLine());
		int cnt = 0; int index = 0;
		while (index < sb.length()) {
			for (int c = 0; c < croatia.length; c++) {
				if (index < sb.length() - 1 && sb.substring(index, index + 2).equals(croatia[c])) {
					cnt++;
					index += 2;
					break;
				}
				if (index < sb.length() - 2 && sb.substring(index, index + 3).equals(croatia[2])) {
					cnt++;
					index += 3;
					break;
				}
				if (c == croatia.length - 1) {
					cnt++;
					index++;
				}
			}
		}
		br.close();
		System.out.println(cnt);
	}
}
