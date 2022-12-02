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

const int INF = 987654321;

void solve() {
    int n, m;
    cin >> n >> m;

    // u번째 정점에서 v번째 정점으로 가는 w 길이의 t(t >= 1)번째 노선에 속하는 간선
    vector<vector<tuple<int, int, int>>> adj(n);

    for (int t = 1; t <= m; t++) {
        int k;
        cin >> k;

        vector<int> p(2 * k - 1);
        for (auto& x : p) {
            cin >> x;
        }

        for (int j = 0; j < 2 * k - 3; j += 2) {
            int u = p[j], v = p[j + 2], w = p[j + 1];

            adj[u].emplace_back(v, w, t);
            adj[v].emplace_back(u, w, t);
        }
    }

    int start, end;
    cin >> start >> end;

    // cost, transfer, here, line
    priority_queue < tuple<int, int, int, int>, vector<tuple<int, int, int, int>>, greater<tuple<int, int, int, int>>> pq;
    // dist[here][line] = cost, transfer
    vector<vector<pair<int, int>>> dist(n, vector<pair<int, int>>(m + 1, { INF, INF }));

    pq.emplace(0, 0, start, 0);
    dist[start][0] = { 0, 0 };

    while (!pq.empty()) {
        auto [cost, transfer, here, line] = pq.top();
        pq.pop();

        if (dist[here][line] < make_pair(cost, transfer)) {
            continue;
        }

        for (auto [there, weight, there_line] : adj[here]) {
            int thereCost = cost + weight;
            int thereTransfer = transfer + (line != there_line);

            if (dist[there][there_line] > make_pair(thereCost, thereTransfer)) {
                dist[there][there_line] = make_pair(thereCost, thereTransfer);
                pq.emplace(thereCost, thereTransfer, there, there_line);
            }
        }
    }

    pair<int, int> min = *min_element(ALL(dist[end]));

    cout << min.first << " " << max(min.second - 1, 0) << "\n";
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
