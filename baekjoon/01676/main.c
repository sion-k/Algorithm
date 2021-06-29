#include <stdio.h>

int main() {
	int N;
	scanf("%d", &N);
	int t = 2;
	int cnt2 = 0, cnt5 = 0;
	while (t <= N) {
		cnt2 += N / t;
		t *= 2;
	}
	t = 5;
	while (t <= N) {
		cnt5 += N / t;
		t *= 5;
	}
	if (cnt2 > cnt5) { int temp = cnt2; cnt2 = cnt5; cnt5 = temp; }
	printf("%d\n", cnt2);
}
