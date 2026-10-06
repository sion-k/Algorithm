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

class segment_tree {
public:
    vector<int> t;

    segment_tree(int n) : t(4 * n) {}

    void update(int v, int vl, int vr, int p, int k) {
        if (vl == vr) {
            t[v] = max(t[v], k);
        } else {
            int vm = (vl + vr) / 2;
            if (p <= vm) {
                update(2 * v, vl, vm, p, k);
            } else {
                update(2 * v + 1, vm + 1, vr, p, k);
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
        return max(query(2 * v, vl, vm, ql, qr),
            query(2 * v + 1, vm + 1, vr, ql, qr));
    }
};

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i].first >> p[i].second;
    }

    sort(ALL(p));

    vector<int> v(n);
    for (int i = 0; i < n; i++) {
        v[i] = p[i].second;
    }

    vector<int> tv = v;
    sort(ALL(tv));

    map<int, int> c;
    for (int i = 0; i < n; i++) {
        c[tv[i]] = i + 1;
    }

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        a[i] = c[v[i - 1]];
    }

    deb_tuple(a);
    segment_tree t(n + 1);

    vector<int> dp(n + 1);
    for (int i = 1; i <= n; i++) {
        dp[i] = 1 + t.query(1, 1, n, 1, a[i] - 1);
        t.update(1, 1, n, a[i], dp[i]);
    }

    cout << (n - *max_element(ALL(dp)) + 1) << '\n';
}
