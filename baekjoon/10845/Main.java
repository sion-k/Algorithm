import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
	static Deque<Integer> q = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String com = br.readLine();
			String[] temp = com.split(" ");
			if (temp.length > 1) {
				q.offer(Integer.parseInt(temp[1]));
			}
			if (com.equals("pop")) {
				if(q.isEmpty()) {
					bw.write("-1");
				} else {
					bw.write(String.valueOf(q.poll()));
				}
				bw.newLine();
			}
			if (com.equals("size")) {
				bw.write(String.valueOf(q.size()));
				bw.newLine();
			}
			if (com.equals("empty")) {
				if(q.isEmpty()) {
					bw.write("1");
				} else {
					bw.write("0");
				}
				bw.newLine();
			}
			if (com.equals("front")) {
				if(q.isEmpty()) {
					bw.write("-1");
				} else {
					bw.write(String.valueOf(q.peek()));
				}
				bw.newLine();
			}
			if (com.equals("back")) {
				if(q.isEmpty()) {
					bw.write("-1");
				} else {
					bw.write(String.valueOf(q.peekLast()));
				}
				bw.newLine();
			}
			
		}
		br.close();
		bw.close();
	}

}
