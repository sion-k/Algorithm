#include <iostream>
#include <queue>
using namespace std;

vector<int> adj[100001];
bool booked[100001];

int bfs(int start) {
    queue<int> q;
    q.push(start);
    booked[start] = true;
    int ret = 0;
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        ret++;
        for (auto& there : adj[here]) if (!booked[there]) {
            q.push(there);
            booked[there] = true;
        }
    }
    return ret;
}

int main() {
    int n, m;
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;
        adj[u].push_back(v);
        adj[v].push_back(u);
    }
    int c, h, k;
    cin >> c >> h >> k;
    bfs(h);
    int ret = bfs(c);
    priority_queue<int> pq;
    for (int here = 1; here <= n; here++) {
        if (!booked[here]) {
            pq.push(bfs(here));
        }
    }
    int size = pq.size();
    for (int i = 0; i < min(size, k); i++) {
        ret += pq.top();
        pq.pop();
    }
    cout << ret << endl;
}