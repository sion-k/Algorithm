import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		List<Member> list = new ArrayList<>(N);
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			list.add(new Member(age, name, i));
		}
		Collections.sort(list);
		for (Member m : list) {
			bw.write(m.toString());
			bw.newLine();
		}
		bw.close();
	}

}

class Member implements Comparable<Member> {
	int age; String name; int reg;
	public Member(int a, String n, int r) {
		age = a; name = n; reg = r;
	}
	@Override
	public int compareTo(Member o) {
		return age == o.age ? reg - o.reg : age - o.age;
	}
	@Override
	public String toString() {return age + " " + name;}
}
