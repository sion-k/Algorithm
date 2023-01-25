#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<vector<pair<int, long long>>> adj(n, vector<pair<int, long long>>(n, { 987654321, 0 }));
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;

        for (int j = 0; j < n; j++) {
            if (s[j] == 'Y') {
                adj[i][j] = { 1, 0 };
            }
        }
    }

    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pair<int, long long> cand(adj[i][k].first + adj[k][j].first, a[k] + adj[i][k].second + adj[k][j].second);

                if (adj[i][j].first > cand.first || adj[i][j].first == cand.first && adj[i][j].second < cand.second) {
                    adj[i][j] = cand;
                }
            }
        }
    }

    int q;
    cin >> q;

    for (int i = 0; i < q; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;

        pair<int, long long> cand = adj[u][v];

        cand.second += a[u];
        cand.second += a[v];

        if (cand.first == 987654321) {
            cout << "Impossible" << "\n";
        } else {
            cout << cand.first << " " << cand.second << "\n";
        }
    }
}
