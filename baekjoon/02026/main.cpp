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

bool found;
int n, k;
vector<vector<int>> adj;

void btk(int here, vector<int>& pick) {
    if (found) {
        return;
    }
    if (SIZE(pick) == k) {
        for (int p : pick) {
            cout << p << '\n';
        }
        found = true;
        return;
    }
    if (here == n + 1) {
        return;
    }

    if (SIZE(pick) < k) {
        bool flag = true;
        for (int there : pick) if (!adj[here][there]) {
            flag = false;
            break;
        }

        if (flag) {
            pick.push_back(here);
            btk(here + 1, pick);
            pick.pop_back();
        }

    }

    btk(here + 1, pick);
}

int main() {
    FAST();

    int m;
    cin >> k >> n >> m;

    adj.resize(n + 1, vector<int>(n + 1));
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        adj[u][v] = adj[v][u] = true;
    }

    vector<int> pick;
    btk(1, pick);

    if (!found) {
        cout << -1 << '\n';
    }
}
