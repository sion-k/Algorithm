#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

int n, m;
int s[100][100];

const int dy[8] = {-1, 1, 0, 0, -1, -1, 1, 1};
const int dx[8] = {0, 0, -1, 1, -1, 1, -1, 1};

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

const int INF = 1e9 + 1;

int f() {
    priority_queue<tuple<int, int, int, int>> pq;
    vector<vector<vector<int>>> dist;
    dist.push_back(vector<vector<int>>());
    dist.push_back(vector<vector<int>>());
    for (int y = 0; y < n; y++) {
        dist[0].push_back(vector<int>(m, INF));
        dist[1].push_back(vector<int>(m, INF));
    }
    pq.emplace(-s[0][0], 1, 0, 0);
    dist[1][0][0] = s[0][0];
    while (!pq.empty()) {
        int here_cost = -get<0>(pq.top());
        int y = get<2>(pq.top());
        int x = get<3>(pq.top());
        int k = get<1>(pq.top());
        pq.pop();
        if (dist[k][y][x] < here_cost) continue;
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d]; int nx = x + dx[d];
            if (!inRange(ny, nx)) continue;
            int there_cost = max(here_cost, s[ny][nx]);
            if (dist[k][ny][nx] > there_cost) {
                dist[k][ny][nx] = there_cost;
                pq.emplace(-there_cost, k, ny, nx);
            }
        }
        if (!k) continue;
        for (int d = 0; d < 4; d++) {
            int ny = y + 2 * dy[d]; int nx = x + 2 * dx[d]; int nk = false;
            if (!inRange(ny, nx)) continue;
            int there_cost = max(here_cost, s[ny][nx]);
            if (dist[nk][ny][nx] > there_cost) {
                dist[nk][ny][nx] = there_cost;
                pq.emplace(-there_cost, nk, ny, nx);
            }
        }
    }
    return min(dist[0][n - 1][m - 1], dist[1][n - 1][m - 1]);
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> s[i][j];
        }
    }
    cout << f() << "\n";
}
