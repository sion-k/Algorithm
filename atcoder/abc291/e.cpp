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

vector<int> p, c;
vector<vector<int>> children, cache;

int dp(int here) {
    if (c[here] != -1) return c[here];

    int max = 0;

    for (int there : children[here]) {
        max = ::max(max, 1 + dp(there));
    }

    return c[here] = max;
}

void dfs(int here, int value) {
    p[here] = value;
    int max = 0, pick = 0;

    for (int there : children[here]) {
        int cand = 1 + dp(there);
        if (max < cand) {
            max = cand;
            pick = there;
        }
    }

    if (pick) {
        dfs(pick, value - 1);
    }
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    children.resize(n + 1);
    for (int i = 0; i < m; i++) {
        int u, v;
        cin >> u >> v;

        children[v].push_back(u);
    }

    p.resize(n + 1);
    c.resize(n + 1, -1);

    pair<int, int> max;
    for (int here = 1; here <= n; here++) {
        max = ::max(max, make_pair(dp(here), here));
    }

    if (max.first == n - 1) {
        cout << "Yes" << "\n";

        dfs(max.second, n);
        for (int i = 1; i <= n; i++) {
            cout << p[i] << " \n"[i == n];
        }
    } else {
        cout << "No" << "\n";
    }
}
