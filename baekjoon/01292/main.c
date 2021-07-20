#include <stdio.h>

int main() {
	int S[1001] = {0};
	int t = 1;
	for (int i = 1; i <= 1000; i++) {
		for (int j = 0; j < i; j++)
			if (t <= 1000){
				S[t] = S[t - 1] + i;
				t++;
			} else break;
		if (t > 1000) break;
	}
	int a, b;
	scanf("%d %d", &a, &b);
	printf("%d\n", S[b] - S[a - 1]);
}
