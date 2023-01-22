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

int n, m;

vector<vector<int>> adj;
vector<int> b;

pair<int, int> bfs(int start) {
    pair<int, int> ret;

    queue<int> q;
    q.push(start);
    b[start] = 1;

    while (!q.empty()) {
        int here = q.front();
        q.pop();

        if (b[here] == 1) ret.first++;
        else ret.second++;

        for (int there : adj[here]) if (!b[there]) {
            q.push(there);
            b[there] = 3 - b[here];
        }
    }

    return ret;
}

int main() {
    FAST();

    cin >> n >> m;

    vector<pair<int, int>> edge;
    adj = vector<vector<int>>(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        edge.emplace_back(u, v);

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    b = vector<int>(n + 1);
    vector<pair<int, int>> c;
    for (int here = 1; here <= n; here++) if (!b[here]) {
        c.push_back(bfs(here));
    }

    for (auto [u, v] : edge) {
        if (b[u] == b[v]) {
            cout << 0 << "\n";
            return 0;
        }
    }

    long long sum = -m;

    for (auto r : c) {
        sum += (long long)r.first * r.second;
    }

    sum += ((long long)n * (n - 1)) / 2;

    for (auto r : c) {
        long long t = r.first + r.second;

        sum -= (t * (t - 1)) / 2;
    }

    cout << sum << "\n";
}
