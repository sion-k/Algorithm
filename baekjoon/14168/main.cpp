#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

#define pii pair<int, int>

int n, m;
vector<pii> a, b;
vector<vector<vector<long long>>> cache;

int dist(pii u, pii v) {
    int x = u.first - v.first;
    int y = u.second - v.second;

    return x * x + y * y;
}

const long long INF = 1e18;

// 마지막으로 a중에선 i번째 소를, b중에선 j번째 소를 방문했을 때
// 모든 소를 방문하는 최소 비용
// k = 0 이면 a[i] 위치에, k = 1이면 b[j] 위치에 있다.
long long dp(int i, int j, int k) {
    if (i == n && j == m) {
        return k ? dist(a[n], b[m]) : 0;
    }
    if (cache[k][i][j] != -1) return cache[k][i][j];

    long long min = INF;
    pii here = k == 0 ? a[i] : b[j];
    if (i != n) {
        min = ::min(min, dist(here, a[i + 1]) + dp(i + 1, j, 0));
    }

    if (j != m) {
        min = ::min(min, dist(here, b[j + 1]) + dp(i, j + 1, 1));
    }

    return cache[k][i][j] = min;
}

int main() {
    FAST();

    cin >> n >> m;

    a.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i].first >> a[i].second;
    }

    b.resize(m + 1);
    for (int i = 1; i <= m; i++) {
        cin >> b[i].first >> b[i].second;
    }

    cache = vector<vector<vector<long long>>>(2, vector<vector<long long>>(n + 1, vector<long long>(m + 1, -1)));

    cout << dp(1, 0, 0) << "\n";
}
