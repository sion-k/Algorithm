import java.io.*;
import java.util.*;

public class Main {
	static int cnt = 1;
	
	static void print(StringBuilder bw, PriorityQueue<Pair> pq) {
		int[] S = new int[4];
		for (Pair x : pq) S[x.index] = x.value;
		for (int i = 0; i < 4; i++) {
			bw.append(S[i]);
			if (i != 3) bw.append(" ");
		}
		cnt++;
		bw.append("\n");
	}
	
	static Pair getMax(PriorityQueue<Pair> pq) {
		Pair ret = null; int max = 0;
		for (Pair x : pq) if (max < x.value) {
			max = x.value;
			ret = x;
		}
		return ret;
	}
	
	// (a, b)바구니의 계란을 c로 옮긴다.
	static void move(Pair a, Pair b, Pair c) {
		a.value--; b.value--;
		c.value += 2;
	}
	
	static void toEven(StringBuilder bw, PriorityQueue<Pair> pq) {
		Pair m1 = pq.poll(); Pair m2 = pq.poll();
		// 기존 계란을 한 개씩 빼서 새로운 t를 만들어줌
		int index = getExtra(m1, m2);
		Pair t = new Pair(index, 0);
		move(m1, m2, t);
		if (m1.value > 0) pq.offer(m1);
		if (m2.value > 0) pq.offer(m2);
		pq.offer(t);
		print(bw, pq);
		// 바구니가 2개 이하가 될 때 까지 
		// 가장 작은 곳에서 가장 큰 곳으로 옮김
		while (pq.size() == 3) {
			m1 = pq.poll(); m2 = pq.poll();
			t = pq.poll();
			move(m1, m2, t);
			if (m1.value > 0) pq.offer(m1);
			if (m2.value > 0) pq.offer(m2);
			pq.offer(t);
			print(bw, pq);
		}
	}
	
	static void is3x(StringBuilder bw, PriorityQueue<Pair> pq) {
		Pair m1 = pq.poll(); Pair m2 = pq.poll();
		pq.offer(m1); pq.offer(m2);
		Pair m3 = new Pair(getExtra(m1, m2), 0);
		pq.offer(m3);
		int range = m1.value / 3;
		while (m3.value < 2 * range) {
			move(m1, m2, m3);
			print(bw, pq);
		}
		// 다시 한 곳에 담기
		while (m1.value > 0) {
			move(m1, m3, m2);
			print(bw, pq);
		}
	}
	
	static int getExtra(Pair m1, Pair m2) {
		for (int i = 0; i < 4; i++)
			if (i != m1.index && i != m2.index)
				return i;
		return 0;
	}
	
	static int getExtra(Pair m1, Pair m2, Pair m3) {
		for (int i = 0; i < 4; i++)
			if (i != m1.index && i != m2.index && i != m3.index)
				return i;
		return 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder bw = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] S = new int[4];
		for (int i = 0; i < 4; i++) S[i] = Integer.parseInt(st.nextToken());
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for (int i = 0; i < 4; i++)
			if (S[i] != 0) pq.offer(new Pair(i, S[i]));
		print(bw, pq);
		// 달걀을 두 바구니 이하로 줄인다
		while (pq.size() >= 3) {
			Pair m1 = pq.poll(); Pair m2 = pq.poll();
			move(m1, m2, getMax(pq));
			if (m1.value > 0) pq.offer(m1);
			if (m2.value > 0) pq.offer(m2);
			print(bw, pq);
		}
		if (pq.size() == 2) {
			Pair m1 = pq.poll(); Pair m2 = pq.poll();
			// 두 원소가 같다면 더 이상 할게 없다.
			if (m1.value == m2.value) {
				Pair t = new Pair(getExtra(m1, m2), 0);
				pq.offer(m1); pq.offer(m2); pq.offer(t);
				while (m1.value > 0) {
					move(m1, m2, t);
					print(bw, pq);
				}
				System.out.print(bw); return;
			}
			// 작은 쪽이 홀수일 경우 짝수로 만들어 준다.
			if (m1.value % 2 == 1) {
				pq.offer(m1); pq.offer(m2);
				if (m1.value % 3 == 0) {
					is3x(bw, pq);
					System.out.print(bw); return;
				}
				toEven(bw, pq);
				if (pq.size() == 1) { System.out.print(bw); return; }
				m1 = pq.poll(); m2 = pq.poll();
				// 두 원소가 같다면 더 이상 할게 없다.
				if (m1.value == m2.value) {
					Pair t = new Pair(getExtra(m1, m2), 0);
					pq.offer(m1); pq.offer(m2); pq.offer(t);
					while (m1.value > 0) {
						move(m1, m2, t);
						print(bw, pq);
					}
					System.out.print(bw); return;
				}
			}
			// 작은 쪽이 짝수고 현재 개수가 2개 이상이고, 서로 같지 않다면
			// bw.append(String.format("%s %s\n", m1, m2));
			pq.offer(m1); pq.offer(m2);
			Pair m3 = new Pair(getExtra(m1, m2), 0);
			Pair m4 = new Pair(getExtra(m1, m2, m3), 0);
			pq.offer(m3); pq.offer(m4);
			while (m1.value > 0) {
				move(m1, m2, m3);
				print(bw, pq);
				move(m1, m2, m4);
				print(bw, pq);
			}
			// m1, m2중 남아 있는 곳에 넣는다
			if (m1.value == 0) {
				while (m3.value > 0) {
					move(m3, m4, m2);
					print(bw, pq);
				}
			} else {
				while (m3.value > 0) {
					move(m3, m4, m1);
					print(bw, pq);
				}
			}
		}
		if (cnt != K) {
			int a = 1 / 0;
		}
		System.out.print(bw);
	}

}

class Pair implements Comparable<Pair> {
	int index, value;
	
	Pair (int i, int v) { index = i; value = v; }
	
	@Override
	public int compareTo(Pair o) { return value - o.value; }
	
	@Override
	public String toString() { return String.format("(%d, %d)", index, value).toString(); }
	
}
