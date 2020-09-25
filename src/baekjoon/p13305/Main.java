package baekjoon.p13305;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int[] R;
	static int[] C;
	static int   N;
	
	//here���� �� ���ñ��� ���µ� �ּ� ����� ��ȯ�Ѵ�
	static int costMin(int here) {
		if (here == N - 1) {return 0;}
		int next = here + 1; int movedDis = R[here];
		
		while(next < N - 1 && C[next] >= C[here]) {
			movedDis += R[next]; next++;
		}
		
		return movedDis * C[here] + costMin(next);
	}

	static int costMinIter(int start) {
		int ret = 0;
		int i = start;
		while (i < N) {
			int toMove = 0;
			while(i < N && C[i] >= C[start]) {
				toMove += R[i];
				i++;
			}
			ret += (toMove * C[start]);
			start = i;
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		R = new int[N]; // �� ���ø��� �����ʿ� ���ΰ� �����ȴ�. (�� ������ ���� ����)
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N - 1; i++) {
			R[i] = Integer.parseInt(st.nextToken());
		}
		
		C = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		br.close();
		for (int i = 0; i < N; i++) {
			C[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(costMin(0));
		//System.out.println(costMinIter(0));
	}

}
