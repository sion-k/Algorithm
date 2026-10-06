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

int n, m;
vector<vector<int>> a, c;
vector<int> dy = { 0, 1, 1 }, dx = { 1, 1, 0 };

int dp(int y, int x) {
    if (a[y][x] != 0) return 0;
    if (y == n - 1 || x == m - 1) return 1;
    if (c[y][x] != -1) return c[y][x];

    int min = max(n, m);
    for (int d = 0; d < 3; d++) {
        int ny = y + dy[d], nx = x + dx[d];
        min = ::min(min, dp(ny, nx));
    }

    return c[y][x] = 1 + min;
}

int main() {
    FAST();

    cin >> n >> m;

    a.resize(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    c.resize(n, vector<int>(m, -1));

    int max = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            max = ::max(max, dp(i, j));
        }
    }

    cout << max << "\n";
}
