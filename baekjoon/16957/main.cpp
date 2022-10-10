#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

int n, m;

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

class Disjoint_set {
public:
    int n;
    vector<int> parent;

    Disjoint_set(int n) : n(n), parent(n) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    void merge(int u, int v) {
        u = find(u), v = find(v);
        if (u == v) return;
        parent[u] = v;
    }

    int find(int u) {
        if (u == parent[u]) return u;
        return parent[u] = find(parent[u]);
    }
};

int main() {
    FAST();

    cin >> n >> m;

    vector<vector<int>> a(n, vector<int>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> a[i][j];
        }
    }

    Disjoint_set ds(n * m);

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            pair<int, int> min(INT_MAX, -1);

            for (int d = 0; d < 8; d++) {
                int ny = y + dy[d], nx = x + dx[d];

                if (inRange(ny, nx) && a[ny][nx] < a[y][x]) {
                    min = ::min(min, { a[ny][nx], d });
                }
            }

            int d = min.second;
            if (d == -1) continue;

            int ny = y + dy[d], nx = x + dx[d];

            int u = m * y + x, v = m * ny + nx;

            ds.merge(u, v);
        }
    }

    vector<vector<int>> b(n, vector<int>(m));
    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            int u = m * y + x;
            int v = ds.find(u);

            int ny = v / m, nx = v % m;
            b[ny][nx]++;
        }
    }

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
            cout << b[y][x] << " \n"[x == m - 1];
        }
    }
}
