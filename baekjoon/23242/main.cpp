#include <iostream>
#include <algorithm>

using namespace std;
int N;
int S1[4001];
int S2[4001];

int range(int S[], int l, int r) {
    int ret = S[r];
    if (l - 1 >= 0) ret -= S[l - 1];
    return ret;
}

double var(int l, int r) {
    double ret = range(S2, l, r);
    double avg = (double)range(S1, l, r) / (r - l + 1);
    ret -= 2 * avg * range(S1, l, r);
    ret += (r - l + 1) * avg * avg;
    return ret;
}

double cache[4001][31];

// S[i]부터 시작해서 K개의 구간으로 나누고
// K구간의 분산값을 더한 값중에서 최솟값을 반환
double dp(int i, int k) {
    if (i == N) return 0;
    if (k == 1) return var(i, N - 1);
    if (cache[i][k] != -1) return cache[i][k];
    double ret = 987654321;
    // 지금 설정할 구간의 길이 [i, i + l)
    for (int l = 1; i + l <= N - (k - 1); l++) {
        ret = min(ret, var(i, i + l - 1) + dp(i + l, k - 1));
    }
    return cache[i][k] = ret;
}

int main() {
    int B;
    scanf("%d", &B);
    scanf("%d", &N);
    for (int i = 0; i < N; i++)
        for (int b = 0; b <= B; b++)
            cache[i][b] = -1;
    for (int i = 0; i < N; i++) {
        scanf("%d", &S1[i]);
        if (i - 1 >= 0) S1[i] += S1[i - 1];
        S2[i] = range(S1, i, i) * range(S1, i, i);
        if (i - 1 >= 0) S2[i] += S2[i - 1];
    }
    double ret = 987654321;
    for (int b = 1; b <= B; b++) ret = min(ret, dp(0, b));
    printf("%.6f\n", ret);
}