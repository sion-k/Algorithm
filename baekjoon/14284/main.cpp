#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<pair<int, int>> adj[5001];
int s, t;

const int INF = 987654321;

int dijkstra() {
    priority_queue<pair<int, int>> pq;
    pq.emplace(0, s);
    vector<int> dist(n + 1);
    fill(dist.begin(), dist.end(), INF);
    while (!pq.empty()) {
        int here = pq.top().second;
        int cost = -pq.top().first;
        pq.pop();
        if (dist[here] < cost) continue;
        if (here == t) return cost;
        for (auto& e : adj[here]) {
            int there = e.first;
            int thereCost = cost + e.second;
            if (dist[there] > thereCost) {
                dist[there] = thereCost;
                pq.emplace(-thereCost, there);
            }
        }
    }
    return -1;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int a, b, c;
        cin >> a >> b >> c;
        adj[a].emplace_back(b, c);
        adj[b].emplace_back(a, c);
    }
    cin >> s >> t;
    cout << dijkstra() << "\n";
}
