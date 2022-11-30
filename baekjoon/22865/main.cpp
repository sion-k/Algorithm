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

const int INF = 1e9;

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(3);
    for (auto& x : a) {
        cin >> x;
    }

    int m;
    cin >> m;

    vector<vector<pair<int, int>>> adj(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        adj[u].emplace_back(v, w);
        adj[v].emplace_back(u, w);
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    vector<int> dist(n + 1, INF);

    for (auto& x : a) {
        pq.emplace(0, x);
        dist[x] = 0;
    }

    while (!pq.empty()) {
        auto [here_cost, here] = pq.top();
        pq.pop();

        if (dist[here] < here_cost) continue;

        for (auto& [there, cost] : adj[here]) {
            int there_cost = here_cost + cost;
            if (dist[there] > there_cost) {
                dist[there] = there_cost;
                pq.emplace(there_cost, there);
            }
        }
    }

    pair<int, int> max;
    for (int i = 1; i <= n; i++) {
        if (dist[i] != INF && max.first < dist[i]) {
            max.first = dist[i];
            max.second = i;
        }
    }

    cout << max.second << "\n";
}
