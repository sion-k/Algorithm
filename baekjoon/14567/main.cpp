#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> adj[1001];
int indegree[1001];
int dist[1001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int a, b;
        cin >> a >> b;
        adj[a].push_back(b);
        indegree[b]++;
    }
    queue<int> q;
    for (int here = 1; here <= n; here++) {
        if (indegree[here] == 0) {
            q.push(here);
        }
    }
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        for (int there : adj[here]) if (--indegree[there] == 0) {
            q.push(there);
            dist[there] = dist[here] + 1;
        }
    }
    for (int here = 1; here <= n; here++) {
        cout << dist[here] + 1 << " ";
    }
}
