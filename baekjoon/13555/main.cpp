#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

const int MOD = 5000000;

class Fenwick_tree {
public:
    int n;
    vector<int> t;

    Fenwick_tree(int n): n(n), t(n + 1) {}

    void add(int i, int v) {
        i++;
        while (i < n) {
            t[i] += v;
            if (t[i] >= MOD) t[i] -= MOD;
            i += i & -i;
        }
    }

    int sum(int i) {
        i++;
        int sum = 0;
        while (i) {
            sum += t[i];
            if (sum >= MOD) sum -= MOD;
            i -= i & -i;
        }
        return sum;
    }
};

bool compare(pair<int, int> &u, pair<int, int> &v) {
    if (u.first == v.first) return u.second > v.second;
    return u.first < v.first;
}

vector<int> f(int n, vector<int> &dp, vector<int> &p) {
    Fenwick_tree t(n);
    vector<int> r(n);
    for (int x : p) {
        if (x - 1 >= 0) {
            r[x] = t.sum(x - 1);
        }
        t.add(x, dp[x]);
    }
    return r;
}

vector<int> g(int n, vector<int> &a) {
    vector<pair<int, int>> p;
    for (int i = 0; i < n; i++) {
        p.emplace_back(a[i], i);
    }
    sort(ALL(p), compare);
    vector<int> ret;
    for (int i = 0; i < n; i++) {
        ret.push_back(p[i].second);
    }
    return ret;
}

int main() {
    FAST();
    int n, k;
    cin >> n >> k;
    vector<int> a(n);
    for (auto &x : a) {
        cin >> x;
    }
    vector<int> dp(n, 1);
    vector<int> p = g(n, a);
    for (int i = 0; i < k - 1; i++) {
        dp = f(n, dp, p);
    }
    int sum = 0;
    for (int i = 0; i < n; i++) {
        sum += dp[i];
        if (sum >= MOD) sum -= MOD;
    }
    cout << sum << "\n";
}
