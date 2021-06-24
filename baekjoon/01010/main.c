#include <stdio.h>

int main() {
	int T, N, M;
	int C[30][30] = {0};
	for (int i = 0; i < 30; i++) {
		for (int j = 0; j < i + 1; j++)
			if (i == j || j == 0) C[i][j] = 1;
			else C[i][j] = C[i - 1][j] + C[i - 1][j - 1];
	}
	scanf("%d", &T);
	while (T--) {
		scanf("%d %d", &N, &M);
		printf("%d\n", C[M][N]);
	}
	return 0;
}