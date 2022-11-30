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

int n;
vector<string> s;

const int dy[8] = { -1, 1, 0, 0, -1, -1, 1, 1 };
const int dx[8] = { 0, 0, -1, 1, -1, 1, -1, 1 };

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < n; }

vector<tuple<int, int, int>> bfs(int sy, int sx) {
    queue<pair<int, int>> q;
    vector<vector<int>> dist(n, vector<int>(n, -1));

    q.emplace(sy, sx);
    dist[sy][sx] = 0;

    while (!q.empty()) {
        auto [y, x] = q.front();
        q.pop();

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (inRange(ny, nx) && s[y][x] != '1' && dist[ny][nx] == -1) {
                dist[ny][nx] = dist[y][x] + 1;
                q.emplace(ny, nx);
            }
        }
    }

    vector<tuple<int, int, int>> edge;

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if ((s[y][x] == 'S' || s[y][x] == 'K') && dist[y][x] != -1) {
                edge.emplace_back(dist[y][x], n * sy + sx, n * y + x);
            }
        }
    }

    return edge;
}

class Disjoint_set {
public:
    int n;
    vector<int> parent, rank, size;

    Disjoint_set(int n) : n(n), parent(n), rank(n), size(n, 1) {
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    void merge(int u, int v) {
        u = find(u); v = find(v);
        if (u == v) return;
        if (rank[u] > rank[v]) swap(u, v);
        parent[u] = v;
        size[v] += size[u];
        if (rank[u] == rank[v]) rank[v]++;
    }

    int find(int u) {
        if (u == parent[u]) return u;
        return parent[u] = find(parent[u]);
    }
};

int main() {
    FAST();

    int m;
    cin >> n >> m;

    s = vector<string>(n);
    for (auto& x : s) {
        cin >> x;
    }

    vector<tuple<int, int, int>> edge;

    for (int y = 0; y < n; y++) {
        for (int x = 0; x < n; x++) {
            if (s[y][x] == 'S' || s[y][x] == 'K') {
                auto e = bfs(y, x);
                edge.insert(edge.end(), ALL(e));
            }
        }
    }

    sort(ALL(edge));

    Disjoint_set ds(n * n);

    int sum = 0, cnt = 0;
    for (auto [w, u, v] : edge) {
        if (ds.find(u) != ds.find(v)) {
            ds.merge(u, v);

            sum += w;
            cnt++;
        }
    }

    cout << (cnt == m ? sum : -1) << "\n";
}
