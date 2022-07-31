#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

void floyd(int n, vector<vector<int>> &dist) {
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
            }
        }
    }
}

int dist(pair<int, int> u, pair<int, int> v) {
    return abs(u.first - v.first) + abs(u.second - v.second);
}

const int INF = 987654321;

int main() {
    FAST();
    int n, t;
    cin >> n >> t;
    vector<int> a(n);
    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> p[i].first >> p[i].second;
    }
    vector<vector<int>> dist(n, vector<int>(n, INF));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            dist[i][j] = ::dist(p[i], p[j]);
            if (a[i] && a[j]) {
                dist[i][j] = ::min(dist[i][j], t);
            }
        }
    }
    floyd(n, dist);
    int m;
    cin >> m;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        cout << dist[u - 1][v - 1] << "\n";
    }
}
