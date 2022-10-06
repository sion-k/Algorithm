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

    Segment_tree(int n) {
        t = vector<int>(4 * n);
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

int solve(int n, vector<pair<int, int>>& p) {
    int sum = n;
    Segment_tree dp(n);
    for (int i = 0; i < n; i++) {
        int x = p[i].second;
        int max = dp.query(1, 0, n - 1, 0, x - 1);
        dp.update(1, 0, n - 1, x - 1, 1 + max);
    }
    sum += dp.query(1, 0, n - 1, 0, n - 1);
    return sum;
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i].first;
    }
    for (int i = 0; i < n; i++) {
        cin >> p[i].second;
    }

    sort(ALL(p));
    int max = solve(n, p);

    cout << max << "\n";
}
