#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, m;
int adj[101];
int dist[101];

bool inRange(int x) { return 1 <= x && x <= 100; }

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < n + m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u] = v;
    }
    queue<int> q;
    q.push(1);
    memset(dist, -1, sizeof(dist));
    dist[1] = 0;
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        for (int l = 1; l <= 6; l++) {
            int there = here + l;
            if (!inRange(there)) continue;
            while (adj[there]) there = adj[there];
            if (dist[there] == -1) {
                dist[there] = dist[here] + 1;
                q.push(there);
            }
        }
    }
    cout << dist[100] << "\n";
}
