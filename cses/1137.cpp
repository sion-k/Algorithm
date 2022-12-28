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

class Fenwick_Tree {
public:
    vector<long long> t;

    Fenwick_Tree(int n): t(n) {}

    void add(int i, long long x) {
        while (i < SIZE(t)) {
            t[i] += x;
            i += (i & -i);
        }
    }

    long long sum(int i) {
        long long sum = 0;
        while (i > 0) {
            sum += t[i];
            i -= (i & -i);
        }
        return sum;
    }
};

vector<vector<int>> adj;

int c;
vector<int> a, p, v, st, en;

void dfs(int here, int prev) {
    p[c] = here;
    v[c] = a[here];
    st[here] = c;
    c++;

    for (int there : adj[here]) if (there != prev) {
        dfs(there, here);
    }

    en[here] = c - 1;
}


int main() {
    FAST();

    int n, q;
    cin >> n >> q;

    a = vector<int>(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    adj = vector<vector<int>>(n + 1);

    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    p = vector<int>(n + 1);
    v = vector<int>(n + 1);
    st = vector<int>(n + 1);
    en = vector<int>(n + 1);

    dfs(1, 1);

    Fenwick_Tree t(n + 1);
    for (int i = 1; i <= n; i++) {
        t.add(i, v[i - 1]);
    }

    for (int i = 0; i < q; i++) {
        int o;
        cin >> o;

        if (o == 1) {
            int u, x;
            cin >> u >> x;

            u = st[u];
            u++;

            long long p = t.sum(u);
            if (u - 1 >= 1) {
                p -= t.sum(u - 1);
            }

            t.add(u, x - p);
        } else {
            int x;
            cin >> x;

            int u = st[x];
            int v = en[x];
            u++, v++;

            long long sum = t.sum(v);
            if (u - 1 >= 1) {
                sum -= t.sum(u - 1);
            }

            cout << sum << "\n";
        }
    }
}
