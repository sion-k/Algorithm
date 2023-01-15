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

vector<int> a;
vector<vector<int>> adj, cache, children;

const int MOD = 1e9 + 7;

void dfs(int here, int prev) {
    for (int there : adj[here]) if (there != prev) {
        children[here].push_back(there);
        dfs(there, here);
    }
}

// parent[here]의 색이 color일 때
// 트리를 색칠하는 경우의 수
int dp(int here, int prev_color) {
    if (cache[prev_color][here] != -1) return cache[prev_color][here];

    vector<int> cand_color;
    if (a[here]) {
        if (a[here] != prev_color) {
            cand_color.push_back(a[here]);
        }
    } else {
        for (int here_color = 1; here_color <= 3; here_color++) if (here_color != prev_color) {
            cand_color.push_back(here_color);
        }
    }

    vector<vector<int>> cand(4);
    for (int here_color : cand_color) {
        for (int there : children[here]) {
            cand[here_color].push_back(dp(there, here_color));
        }
    }

    int sum = 0;
    for (int here_color : cand_color) {
        int product = 1;

        for (int c : cand[here_color]) {
            product = ((long long)product * c) % MOD;
        }

        sum = (sum + product) % MOD;
    }


    return cache[prev_color][here] = sum;
}

int main() {
    FAST();

    int n, k;
    cin >> n >> k;

    adj.resize(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    a.resize(n + 1);
    for (int i = 0; i < k; i++) {
        int b, c;
        cin >> b >> c;

        a[b] = c;
    }

    children.resize(n + 1);
    dfs(1, 1);

    cache = vector<vector<int>>(4, vector<int>(n + 1, -1));

    cout << dp(1, 0) << "\n";
}
