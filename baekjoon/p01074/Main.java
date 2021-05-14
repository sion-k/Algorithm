package baekjoon.p01074;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int r; static int c;

	// (y1, x1), (y2, x2)������ �������� (r, c)�� ���°�� �湮�Ǵ��� ��ȯ
	// l = y2 - y1
	static int dc(int y1, int x1, int y2, int x2, int l) {
		if (y1 == y2 && x1 == x2) return 0;
		int my = (y1 + y2) / 2;
		int mx = (x1 + x2) / 2;
		int pos = 0; // ��ü ������ 4���� ������ ���� ������ 1, 2, 3, 4
		int ret = 0;
		if (r <= my) {
			if (c <= mx) {
				ret = dc(y1, x1, my, mx, l / 2);
			} else {
				ret = dc(y1, mx + 1, my, x2, l / 2);
				pos += 1;
			}
		} else {
			if (c <= mx) {
				ret = dc(my + 1, x1, y2, mx, l / 2);
			} else {
				ret = dc(my + 1, mx + 1, y2, x2, l / 2);
				pos += 1;
			}
			pos += 2;
		}
		return (l / 2) * (l / 2) * pos + ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int L = (int)(Math.pow(2, N)); // �� ���� ����
		System.out.println(dc(0, 0, L - 1, L - 1, L));
	}

}