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

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<vector<pair<int, int>>> adj(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v, w;
        cin >> u >> v >> w;

        adj[u].emplace_back(v, w);
        adj[v].emplace_back(u, w);
    }

    vector<int> dist(n + 1, INT_MAX);
    dist[1] = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.emplace(0, 1);

    while (!pq.empty()) {
        auto [here_cost, here] = pq.top();
        pq.pop();

        if (dist[here] < here_cost) continue;

        for (auto e : adj[here]) {
            int there = e.first, there_cost = here_cost + e.second;
            if (dist[there] > there_cost) {
                dist[there] = there_cost;
                pq.emplace(there_cost, there);
            }
        }
    }

    vector<pair<int, int>> edge;

    vector<int> visit(n + 1);
    visit[1] = true;

    queue<int> q;
    q.emplace(1);

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        for (auto [there, cost] : adj[here]) {
            if (!visit[there] && dist[there] == dist[here] + cost) {
                visit[there] = true;
                q.emplace(there);

                edge.emplace_back(here, there);
            }
        }
    }

    cout << SIZE(edge) << "\n";
    for (auto [here, there] : edge) {
        cout << here << " " << there << "\n";
    }
}
