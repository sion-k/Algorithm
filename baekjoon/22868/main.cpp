#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, m;
int s, e;
vector<int> adj[10001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    for (int here = 1; here <= 10000; here++) {
        sort(ALL(adj[here]));
    }
    cin >> s >> e;
    // e에서 s로 bfs
    int dist[10001];
    memset(dist, -1, sizeof(dist));
    queue<int> q;
    q.push(e);
    dist[e] = 0;
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        for (int there : adj[here]) if (dist[there] == -1) {
            q.push(there);
            dist[there] = dist[here] + 1;
        }
    }
    // s에서 e로 사전순으로 가장 먼저오는 경로로 이동
    int prev[10001];
    memset(prev, -1, sizeof(prev));
    q.push(s);
    prev[s] = s;
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        for (int there : adj[here]) {
            if (dist[here] - 1 == dist[there] && prev[there] == -1) {
                q.push(there);
                prev[there] = here;
            }
        }
    }
    bool check[10001];
    memset(check, false, sizeof(check));
    int sum = 0;
    for (int here = e; prev[here] != here; here = prev[here]) {
        check[here] = true;
        sum++;
    }
    // s와 e는 제외
    check[s] = check[e] = false;
    // e에서 s로 다시 bfs
    memset(dist, -1, sizeof(dist));
    q.push(e);
    dist[e] = 0;
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        for (int there : adj[here]) if (dist[there] == -1 && !check[there]) {
            q.push(there);
            dist[there] = dist[here] + 1;
        }
    }
    sum += dist[s];
    cout << sum << "\n";
}
