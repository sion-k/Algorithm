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


int n, m, r, c;
vector<vector<int>> a;

int sum(int x1, int y1, int x2, int y2) {
    int sum = a[x2][y2];

    if (x1 - 1 >= 0) {
        sum -= a[x1 - 1][y2];
    }

    if (y1 - 1 >= 0) {
        sum -= a[x2][y1 - 1];
    }

    if (x1 - 1 >= 0 && y1 - 1 >= 0) {
        sum += a[x1 - 1][y1 - 1];
    }

    return sum;
}

const int dx[4] = { -1, 1, 0, 0 };
const int dy[4] = { 0, 0, -1, 1 };

bool in_range(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y < m;
}

bool movable(int x1, int y1) {
    int x2 = x1 + r - 1, y2 = y1 + c - 1;
    return in_range(x1, y1) && in_range(x2, y2) && (sum(x1, y1, x2, y2) == 0);
}

int bfs(int sx, int sy, int ex, int ey) {
    queue<pair<int, int>> q;
    vector<vector<int>> b(n, vector<int>(m, -1));

    q.emplace(sx, sy);
    b[sx][sy] = 0;

    while (!q.empty()) {
        auto [x, y] = q.front();
        q.pop();

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (::movable(nx, ny) && b[nx][ny] == -1) {
                q.emplace(nx, ny);
                b[nx][ny] = b[x][y] + 1;
            }
        }
    }

    return b[ex][ey];
}

int main() {
    FAST();

    int k;
    cin >> n >> m >> r >> c >> k;

    a.resize(n, vector<int>(m));
    for (int i = 0; i < k; i++) {
        int x, y;
        cin >> x >> y;
        x--, y--;

        a[x][y] = 1;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (i - 1 >= 0) {
                a[i][j] += a[i - 1][j];
            }

            if (j - 1 >= 0) {
                a[i][j] += a[i][j - 1];
            }

            if (i - 1 >= 0 && j - 1 >= 0) {
                a[i][j] -= a[i - 1][j - 1];
            }
        }
    }

    int sx, sy;
    cin >> sx >> sy;

    int ex, ey;
    cin >> ex >> ey;
    sx--, sy--, ex--, ey--;

    cout << bfs(sx, sy, ex, ey) << "\n";
}
