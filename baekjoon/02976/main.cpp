#include <iostream>
using namespace std;
int N;
int S[1001];
int cache[1001][1001];

const int INF = 987654321;

int dp(int i, int k) {
    if (i == N) return S[N];
    if (cache[i][k]) return cache[i][k];
    int& ret = cache[i][k] = INF;
    if (i + k + 1 <= N) ret = min(ret, S[i] + dp(i + k + 1, k + 1));
    if (i - k >= 1) ret = min(ret, S[i] + dp(i - k, k));
    return ret;
}

int main() {
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> S[i];
    cout << dp(2, 1);
}