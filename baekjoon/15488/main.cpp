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
vector<vector<vector<double>>> c;

const int dx[8] = { 1, 2, 2, 1, -1, -2, -2, -1 };
const int dy[8] = { 2, 1, -1, -2, -2, -1, 1, 2 };

bool in_range(int x, int y) {
    return 1 <= x && x <= n && 1 <= y && y <= n;
}

double dp(int x, int y, int k) {
    if (k == m) {
        return 1;
    }
    if (c[x][y][k] != -1) {
        return c[x][y][k];
    }

    double sum = 0;
    for (int d = 0; d < 8; d++) {
        int nx = x + dx[d], ny = y + dy[d];
        if (in_range(nx, ny)) {
            sum += dp(nx, ny, k + 1) / 8;
        }
    }

    return c[x][y][k] = sum;
}

int main() {
    FAST();

    int x, y;
    cin >> n >> x >> y >> m;

    c.resize(n + 1, vector<vector<double>>(n + 1, vector<double>(m, -1)));

    cout << fixed;
    cout.precision(12);

    cout << dp(x, y, 0) << '\n';
}
