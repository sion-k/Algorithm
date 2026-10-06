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
vector<vector<int>> a;

const int dy[4] = { -1, 1, 0, 0 };
const int dx[4] = { 0, 0, -1, 1 };

bool in_range(int y, int x) {
    return 0 <= y && y < n && 0 <= x && x < m;
}

pair<int, int> bfs(int sy, int sx) {
    queue<pair<int, int>> q;
    vector<vector<int>> b(n, vector<int>(m, -1));

    q.emplace(sy, sx);
    b[sy][sx] = 0;

    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (in_range(ny, nx) && a[ny][nx] && b[ny][nx] == -1) {
                q.emplace(ny, nx);
                b[ny][nx] = b[y][x] + 1;
            }
        }
    }

    pair<int, int> max(-1, -1);
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            max = ::max(max, make_pair(b[y][x], a[sy][sx] + a[y][x]));
        }
    }

    return max;
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

    pair<int, int> max(-1, -1);
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            if (a[y][x]) {
                max = ::max(max, bfs(y, x));
            }
        }
    }

    cout << (max.first != -1 ? max.second : 0) << '\n';
}
