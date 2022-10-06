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
    vector<int> t;

    Segment_tree(int n, vector<int>& a) {
        t = vector<int>(4 * n);
        init(1, 0, n - 1, a);
    }

    void init(int v, int l, int r, vector<int>& a) {
        if (l == r) {
            t[v] = a[l];
        } else {
            int m = (l + r) / 2;
            init(2 * v, l, m, a);
            init(2 * v + 1, m + 1, r, a);
            t[v] = max(t[2 * v], t[2 * v + 1]);
        }
    }

    void update(int v, int l, int r, int k, int x) {
        if (l == r) {
            t[v] = x;
        } else {
            int m = (l + r) / 2;
            if (k <= m) {
                update(2 * v, l, m, k, x);
            } else {
                update(2 * v + 1, m + 1, r, k, x);
            }
            t[v] = max(t[2 * v], t[2 * v + 1]);
        }
    }

    int query(int v, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr) {
            return t[v];
        }
        if (qr < l || r < ql) {
            return 0;
        }
        int m = (l + r) / 2;
        return max(query(2 * v, l, m, ql, qr), query(2 * v + 1, m + 1, r, ql, qr));
    }
};
