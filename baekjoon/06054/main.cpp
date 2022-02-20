#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

int n;
int a[1001];
vector<int> adj[1001];

const int INF = 987654321;

int dijkstra() {
    priority_queue<pair<int, int>> pq;
    pq.emplace(0, 1);
    int dist[1001];
    for (int i = 1; i <= n; i++) {
        dist[i] = INF;
    }
    dist[1] = 0;
    while (!pq.empty()) {
        int here = pq.top().second;
        int cost = -pq.top().first;
        pq.pop();
        if (dist[here] < cost) continue;
        for (int there : adj[here]) {
            int thereCost = cost + a[here];
            if (dist[there] > thereCost) {
                dist[there] = thereCost;
                pq.emplace(-thereCost, there);
            }
        }
    }
    int ret = 0;
    for (int here = 1; here <= n; here++) {
        ret = max(ret, dist[here] + a[here]);
    }
    return ret;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int here = 1; here <= n; here++) {
        cin >> a[here];
        int m;
        cin >> m;
        for (int j = 0; j < m; j++) {
            int there;
            cin >> there;
            adj[here].push_back(there);
        }
    }
    cout << dijkstra() << "\n";
}
