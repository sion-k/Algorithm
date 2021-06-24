import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Map<Double, Integer> map = new HashMap<>(N);
		Map<Double, Integer> map2 = new HashMap<>(N);
		Map<Double, Integer> map3 = new HashMap<>(N);
		Map<Double, Integer> map4 = new HashMap<>(N);
		int[] bound = new int[4];
		for (int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (x == 0 && y > 0) {bound[0]++; continue;}
			if (x == 0 && y < 0) {bound[1]++; continue;}
			if (y == 0 && x > 0) {bound[2]++; continue;}
			if (y == 0 && x > 0) {bound[3]++; continue;}
			Double key = (double)x / y;
			if (x > 0 && y > 0) {
				map.put(key, map.getOrDefault(key, 0) + 1);
			} else if (x > 0 && y < 0){
				map2.put(key, map2.getOrDefault(key, 0) + 1);
			} else if (x < 0 && y > 0) {
				map3.put(key, map3.getOrDefault(key, 0) + 1);
			} else {
				map4.put(key, map4.getOrDefault(key, 0) + 1);
			}
		}
		int max = 0;
		for (int i = 0; i < 4; i++) {max = Math.max(max, bound[i]);}
		if (map.size() != 0) {
			max = Math.max(max, Collections.max(map.values()));
		}
		if (map2.size() != 0) {
			max = Math.max(max, Collections.max(map2.values()));
		}
		if (map3.size() != 0) {
			max = Math.max(max, Collections.max(map3.values()));
		}
		if (map4.size() != 0) {
			max = Math.max(max, Collections.max(map4.values()));
		}
		System.out.println(max);
	}

}
