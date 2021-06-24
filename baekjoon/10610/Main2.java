import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		int[] digit = new int[10];
		for (int i = 0; i < S.length(); i++)
			digit[S.charAt(i) - '0']++;
		if (digit[0] == 0) {
			System.out.println(-1);
		} else {
			int sum = 0;
			for (int i = 1; i <= 9; i++)
				sum += i * digit[i];
			if (sum % 3 != 0) {
				System.out.println(-1);
			} else {
				StringBuilder ans = new StringBuilder();
				for (int i = 9; i >= 0; i--)
					for (int j = 0; j < digit[i]; j++)
						ans.append(i);
				System.out.println(ans);
			}
		}
	}

}
