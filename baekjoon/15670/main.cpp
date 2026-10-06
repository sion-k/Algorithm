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

class Disjoint_set {
public:
    vector<int> p;

    Disjoint_set(int n) : p(n + 1, -1) {}

    int find(int pu) {
        if (p[pu] == -1) return pu;
        return p[pu] = find(p[pu]);
    }

    void merge(int pu, int v) {
        pu = find(pu), v = find(v);

        if (pu == v) {
            return;
        }

        p[v] = pu;
    }
};

int query(vector<int>& p, Disjoint_set& d, int l, int r) {
    l = d.find(l), r = d.find(r);
    return p[r] - p[l - 1];
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    vector<int> pu(n + 1);
    for (int i = 1; i <= n; i++) {
        if (i == 1 || a[i - 1] > a[i]) {
            pu[i]++;
        }

        pu[i] += pu[i - 1];
    }

    Disjoint_set du(n);
    for (int i = 2; i <= n; i++) {
        if (a[i - 1] < a[i]) {
            du.merge(i - 1, i);
        }
    }

    vector<int> pl(n + 1);
    for (int i = 1; i <= n; i++) {
        if (i == 1 || a[i - 1] < a[i]) {
            pl[i]++;
        }

        pl[i] += pl[i - 1];
    }

    Disjoint_set dl(n);
    for (int i = 2; i <= n; i++) {
        if (a[i - 1] > a[i]) {
            dl.merge(i - 1, i);
        }
    }

    for (int i = 0; i < m; i++) {
        int l, r;
        cin >> l >> r;

        int sum = 0;
        if (1 <= l - 1) {
            int left = query(pu, du, 1, l - 1);
            sum += left;
        }

        if (r + 1 <= n) {
            int right = query(pu, du, r + 1, n);
            sum += right;
        }

        int mid = query(pl, dl, l, r);
        sum += mid;

        if (l != 1 && a[l - 1] < a[r]) {
            sum--;
        }

        if (r != n && a[l] < a[r + 1]) {
            sum--;
        }

        cout << sum << '\n';
    }

}
