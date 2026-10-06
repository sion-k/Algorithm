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

const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };
const int dr[4] = { 3, 2, 1, 0 };

int n, m;
vector<vector<int>> a;
vector<vector<vector<int>>> c;

int dp(int y, int x, int d) {
    if (y == -1) {
        return 2 * n + 2 * m - x;
    }
    if (y == n) {
        return n + 1 + x;
    }
    if (x == -1) {
        return 1 + y;
    }
    if (x == m) {
        return 2 * n + m - y;
    }

    if (c[d][y][x]) {
        return c[d][y][x];
    }

    int nd = a[y][x] ? dr[d] : d;
    int ny = y + dy[nd], nx = x + dx[nd];
    return c[d][y][x] = dp(ny, nx, nd);
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

    c.resize(4, vector<vector<int>>(n, vector<int>(m)));

    for (int y = 0; y < n; y++) {
        cout << dp(y, 0, 3) << ' ';
    }

    for (int x = 0; x < m; x++) {
        cout << dp(n - 1, x, 0) << ' ';
    }

    for (int y = n - 1; y >= 0; y--) {
        cout << dp(y, m - 1, 2) << ' ';
    }

    for (int x = m - 1; x >= 0; x--) {
        cout << dp(0, x, 1) << ' ';
    }
}
