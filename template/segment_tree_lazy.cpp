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

            t[v] = t[2 * v] + t[2 * v + 1];
        }
    }

    void update(int v, int vl, int vr, int ql, int qr, long long x) {
        if (qr < vl || vr < ql) {
            return;
        }

        if (ql <= vl && vr <= qr) {
            t[v] += (vr - vl + 1) * x;
            p[v] += x;
        } else {
            push(v, vl, vr);

            int vm = (vl + vr) / 2;
            update(2 * v, vl, vm, ql, qr, x);
            update(2 * v + 1, vm + 1, vr, ql, qr, x);

            t[v] = t[2 * v] + t[2 * v + 1];
        }

    }

    long long query(int v, int vl, int vr, int ql, int qr) {
        if (qr < vl || vr < ql) {
            return 0;
        }

        if (ql <= vl && vr <= qr) {
            return t[v];
        }

        push(v, vl, vr);

        int vm = (vl + vr) / 2;
        return query(2 * v, vl, vm, ql, qr) + query(2 * v + 1, vm + 1, vr, ql, qr);
    }

    void push(int v, int vl, int vr) {
        int vm = (vl + vr) / 2;

        t[2 * v] += (vm - vl + 1) * p[v];
        t[2 * v + 1] += (vr - vm) * p[v];

        p[2 * v] += p[v];
        p[2 * v + 1] += p[v];

        p[v] = 0;
    }
};
