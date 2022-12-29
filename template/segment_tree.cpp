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

    void init(int v, int vl, int vr, vector<int>& a) {
        if (vl == vr) {
            t[v] = a[vl];
        } else {
            int vm = (vl + vr) / 2;

            init(2 * v, vl, vm, a);
            init(2 * v + 1, vm + 1, vr, a);

            t[v] = max(t[2 * v], t[2 * v + 1]);
        }
    }

    void update(int v, int vl, int vr, int k, int x) {
        if (vl == vr) {
            t[v] = x;
        } else {
            int vm = (vl + vr) / 2;

            if (k <= vm) {
                update(2 * v, vl, vm, k, x);
            } else {
                update(2 * v + 1, vm + 1, vr, k, x);
            }

            t[v] = max(t[2 * v], t[2 * v + 1]);
        }
    }

    int query(int v, int vl, int vr, int ql, int qr) {
        if (qr < vl || vr < ql) {
            return 0;
        }

        if (ql <= vl && vr <= qr) {
            return t[v];
        }

        int vm = (vl + vr) / 2;

        return max(query(2 * v, vl, vm, ql, qr), query(2 * v + 1, vm + 1, vr, ql, qr));
    }
};
