#include <stdio.h>

int main() {
	int T, x, y;
	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &x, &y);
		int dist = y - x;
		// dist를 넘지 않는 최대의 sum(lo)를 찾는다
		long long lo = 0; long long hi = 46341;
		while (lo + 1 < hi) {
			long long mid = (lo + hi) / 2;
			if (mid * (mid + 1) > dist) hi = mid;
			else lo = mid;
		}
		int cnt = 2 * lo;
		dist -= lo * (lo + 1);
		if (dist == 0);
		else if (dist <= lo + 1) cnt++;
		else cnt += 2;
		printf("%d\n", cnt);
	}
}
