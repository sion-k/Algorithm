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

class Segment_tree {
public:
    vector<int> t;

    Segment_tree(int n) {
        t = vector<int>(4 * n);
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

int main() {
    FAST();

    int n, k, d;
    cin >> n >> k >> d;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    Segment_tree dp1(500001);
    vector<int> dp2(k + 1);

    int max = 1;

    for (int i = 0; i < n; i++) {
        int cand = 1;

        int ql = ::max(1, a[i] - d), qr = ::min(500000, a[i] + d);

        cand = ::max(cand, dp1.query(1, 1, 500000, ql, qr) + 1);
        cand = ::max(cand, dp2[a[i] % k] + 1);

        if (dp1.query(1, 1, 500000, a[i], a[i]) < cand) {
            dp1.update(1, 1, 500000, a[i], cand);
        }

        dp2[a[i] % k] = ::max(dp2[a[i] % k], cand);

        max = ::max(max, cand);
    }

    cout << max << "\n";
}
