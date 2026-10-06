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

const int INF = 987654321;

int n, m;
vector<string> a;
vector<vector<int>> c;

vector<int> dy = { 0, 1, -1 };

bool in_range(int y, int x) {
    return 0 <= y && y < n && 0 <= x && x < m;
}

int dp(int y, int x) {
    if (x == m - 1) {
        return a[y][x] == 'O' ? 0 : -m;
    }

    if (c[y][x] != -INF) return c[y][x];

    int max = -m;

    if (a[y][x] == 'O') {
        max = ::max(max, 0);
    }

    for (int d = 0; d < 3; d++) {
        int ny = y + dy[d], nx = x + 1;

        if (in_range(ny, nx) && a[ny][nx] != '#') {
            max = ::max(max, dp(ny, nx));
        }
    }

    return c[y][x] = (a[y][x] == 'C') + max;
}

int main() {
    FAST();

    cin >> n >> m;

    a.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    c = vector<vector<int>>(n, vector<int>(m, -INF));

    int ret = -1;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (a[i][j] == 'R') {
                ret = max(ret, dp(i, j));
            }
        }
    }

    cout << ret << "\n";
}
