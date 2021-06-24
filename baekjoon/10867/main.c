#include <stdio.h>

void main() {
    int N;
    scanf("%d", &N);
    int S[2001];
    for (int i = 0; i < N; i++) {
        int t;
        scanf("%d", &t);
        S[t]++;
    }
    for (int i = 0; i < 2001; i++)
        for (int j = 0; j < S[i]; j++)
            printf("%d ", i - 1000);
}
