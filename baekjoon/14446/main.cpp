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

vector<vector<int>> ch;

int timer;
vector<int> st, en, et;

void dfs(int here) {
    st[here] = timer++;
    et.push_back(here);

    for (int there : ch[here]) {
        dfs(there);
    }

    en[here] = timer - 1;
}

class mergesort_tree {
public:
    vector<vector<int>> t;

    mergesort_tree(int n, vector<int>& a) {
        t.resize(4 * n);
        init(1, 0, n - 1, a);
    }

    void init(int v, int vl, int vr, vector<int>& a) {
        if (vl == vr) {
            t[v].push_back(a[vl]);
        } else {
            int vm = (vl + vr) / 2;

            init(2 * v, vl, vm, a);
            init(2 * v + 1, vm + 1, vr, a);

            merge(ALL(t[2 * v]), ALL(t[2 * v + 1]), back_inserter(t[v]));
        }
    }

    int query(int v, int vl, int vr, int ql, int qr, int qv) {
        if (qr < vl || vr < ql) {
            return 0;
        }

        if (ql <= vl && vr <= qr) {
            return t[v].end() - upper_bound(ALL(t[v]), qv);
        }

        int vm = (vl + vr) / 2;
        return query(2 * v, vl, vm, ql, qr, qv) + query(2 * v + 1, vm + 1, vr, ql, qr, qv);
    }
};

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    ch.resize(n);
    for (int here = 1; here <= n - 1; here++) {
        int there;
        cin >> there;
        there--;

        ch[there].push_back(here);
    }

    st.resize(n), en.resize(n);
    dfs(0);

    for (int i = 0; i < n; i++) {
        et[i] = a[et[i]];
    }

    mergesort_tree mst(n, et);
    for (int i = 0; i < n; i++) {
        cout << mst.query(1, 0, n - 1, st[i], en[i], a[i]) << "\n";
    }

}
