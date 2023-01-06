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

class Segment_tree {
public:
    vector<long long> t, p;

    Segment_tree(int n, vector<long long>& a) {
        t = vector<long long>(4 * n);
        p = vector<long long>(4 * n);

        init(1, 0, n - 1, a);
    }

    void init(int v, int vl, int vr, vector<long long>& a) {
        if (vl == vr) {
            t[v] = a[vl];
        } else {
            int vm = (vl + vr) / 2;

            init(2 * v, vl, vm, a);
            init(2 * v + 1, vm + 1, vr, a);

            t[v] = t[2 * v] ^ t[2 * v + 1];
        }
    }

    void update(int v, int vl, int vr, int ql, int qr, long long x) {
        if (qr < vl || vr < ql) {
            return;
        }

        if (ql <= vl && vr <= qr) {
            if ((vr - vl + 1) % 2) {
                t[v] ^= x;
            }

            p[v] ^= x;
        } else {
            push(v, vl, vr);

            int vm = (vl + vr) / 2;
            update(2 * v, vl, vm, ql, qr, x);
            update(2 * v + 1, vm + 1, vr, ql, qr, x);

            t[v] = t[2 * v] ^ t[2 * v + 1];
        }

    }

    long long query(int v, int vl, int vr, int p) {
        if (vl == vr) {
            return t[v];
        }

        push(v, vl, vr);

        int vm = (vl + vr) / 2;
        if (p <= vm) {
            return query(2 * v, vl, vm, p);
        } else {
            return query(2 * v + 1, vm + 1, vr, p);
        }
    }

    void push(int v, int vl, int vr) {
        int vm = (vl + vr) / 2;

        if ((vm - vl + 1) % 2) {
            t[2 * v] ^= p[v];
        }

        if ((vr - vm) % 2) {
            t[2 * v + 1] ^= p[v];
        }

        p[2 * v] ^= p[v];
        p[2 * v + 1] ^= p[v];

        p[v] = 0;
    }
};

vector<vector<int>> adj;

int t;
vector<int> st, en;

vector<int> a;
vector<int> depth;
vector<vector<int>> parent;

void dfs(int here, int prev) {
    st[here] = t++;

    for (int there : adj[here]) if (there != prev) {
        a[there] ^= a[here];
        depth[there] = depth[here] + 1;
        parent[there][0] = here;
        dfs(there, here);
    }

    en[here] = t - 1;
}

int lca(int u, int v) {
    if (depth[u] < depth[v]) {
        swap(u, v);
    }

    int diff = depth[u] - depth[v];
    for (int k = 0; k <= 16; k++) {
        if (diff & (1 << k)) {
            u = parent[u][k];
        }
    }

    if (u == v) {
        return u;
    }

    for (int k = 16; k >= 0; k--) {
        if (parent[u][k] != parent[v][k]) {
            u = parent[u][k];
            v = parent[v][k];
        }
    }

    return parent[u][0];
}

int main() {
    FAST();

    int n, q;
    cin >> n >> q;

    a = vector<int>(n);
    for (auto& x : a) {
        cin >> x;
    }

    adj = vector<vector<int>>(n);
    for (int i = 0; i < n - 1; i++) {
        int u, v;
        cin >> u >> v;
        u--, v--;

        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    st = vector<int>(n), en = vector<int>(n);
    depth = vector<int>(n);
    parent = vector<vector<int>>(n, vector<int>(17, -1));

    dfs(0, 0);

    for (int k = 1; k <= 16; k++) {
        for (int i = 0; i < n; i++) {
            if (parent[i][k - 1] != -1) {
                parent[i][k] = parent[parent[i][k - 1]][k - 1];
            }
        }
    }

    vector<long long> b(n);
    for (int i = 0; i < n; i++) {
        b[st[i]] = a[i];
    }

    Segment_tree sg(n, b);

    for (int i = 0; i < q; i++) {
        int o, a, b;
        cin >> o >> a >> b;

        if (o == 1) {
            a--;

            long long prev = sg.query(1, 0, n - 1, st[a]);
            if (parent[a][0] != -1) {
                prev ^= sg.query(1, 0, n - 1, st[parent[a][0]]);
            }

            long long oper = (long long)b ^ prev;

            sg.update(1, 0, n - 1, st[a], en[a], oper);
        } else {
            a--, b--;
            int c = lca(a, b);

            int sum = sg.query(1, 0, n - 1, st[a]) ^ sg.query(1, 0, n - 1, st[b]);
            sum ^= sg.query(1, 0, n - 1, st[c]);

            if (parent[c][0] != -1) {
                sum ^= sg.query(1, 0, n - 1, st[parent[c][0]]);
            }

            cout << sum << "\n";
        }
    }

}
