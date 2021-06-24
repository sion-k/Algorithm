#include <stdio.h>

int main() {
	int T, N, M, r;
	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &N, &M);
		r = 1;
		for (int i = 1; i <= N; i++) r = r * (M - i + 1) / i;
		printf("%d\n", r);
	}
}
