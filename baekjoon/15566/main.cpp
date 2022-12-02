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

vector<int> f(int n, vector<vector<int>>& interest, vector<pair<int, int>>& prefer, vector<tuple<int, int, int>>& edge, int mask) {
    vector<int> ret(n, -1);

    for (int i = 0; i < n; i++) {
        int p = mask & (1 << i) ? prefer[i].first : prefer[i].second;

        if (ret[p] != -1) {
            return vector<int>();
        }

        ret[p] = i;
    }

    for (auto& [u, v, t] : edge) {
        if (interest[ret[u]][t] != interest[ret[v]][t]) {
            return vector<int>();
        }
    }

    return ret;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<vector<int>> a(n, vector<int>(4));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 4; j++) {
            cin >> a[i][j];
        }
    }

    vector<pair<int, int>> b(n);
    for (int i = 0; i < n; i++) {
        cin >> b[i].first >> b[i].second;
        b[i].first--, b[i].second--;
    }

    vector<tuple<int, int, int>> c;
    for (int i = 0; i < m; i++) {
        int u, v, t;
        cin >> u >> v >> t;
        u--, v--, t--;

        c.emplace_back(u, v, t);
    }

    for (int mask = 0; mask < (1 << n); mask++) {
        vector<int> ret = f(n, a, b, c, mask);

        if (SIZE(ret)) {
            cout << "YES" << "\n";
            for (int i = 0; i < n; i++) {
                cout << ret[i] + 1 << " \n"[i == n - 1];
            }
            return 0;
        }
    }

    cout << "NO" << "\n";
}
