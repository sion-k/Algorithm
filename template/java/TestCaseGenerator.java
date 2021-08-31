public class TestCaseGenerator {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("testCase.txt"));
		br.close();
		BufferedWriter bw = new BufferedWriter(new FileWriter("testCase.txt"));
		StringBuilder data = new StringBuilder();
		int N = 500000;
		data.append(N).append("\n");
		for (int i = 1; i <= N; i++) {
			data.append(i);
			if (i != N) data.append(" ");
		}
		data.append("\n");
		for (int i = N; i >= 1; i--) {
			data.append(i);
			if (i != 1) data.append(" ");
		}
		data.append("\n");
		bw.write(data.toString());
		bw.flush();
		bw.close();
	}

}