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

/*
9
1 2
1 3
1 4
1 5
2 6
4 7
4 8
4 9
*/
vector<vector<int>> adj;
vector<int> st, en;
int t = 1;

void dfs(int here, int prev) {
    st[here] = t;
    t++;

    for (int there : adj[here]) if (there != prev) {
        dfs(there, here);
    }

    en[here] = t - 1;
}

int main() {
    FAST();

    int n;
    cin >> n;

    adj = vector<vector<int>>(n + 1);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    st = vector<int>(n + 1), en = vector<int>(n + 1);

    dfs(1, 1);

    deb_tuple(st);
    deb_tuple(en);
}
