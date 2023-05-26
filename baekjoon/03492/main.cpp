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

int n, k;
string s;
vector<pair<int, int>> p;
vector<int> c;

bool compare(pair<int, int> u, pair<int, int> v) {
    if (c[u.second] == c[v.second]) {
        return c[(u.second + k) % n] < c[(v.second + k) % n];
    }
    return c[u.second] < c[v.second];
}

void solve() {
    cin >> s;
    n = SIZE(s);

    vector<pair<int, int>> t;
    for (int i = 0; i < n; i++) {
        t.emplace_back(s[i], i);
    }
    sort(ALL(t));

    p.resize(n);
    for (int i = 0; i < n; i++) {
        p[i] = { t[i].second, i };
    }

    c.resize(n);
    int order = 0;
    for (int i = 0; i < n; i++) {
        if (i != 0 && s[p[i - 1].first] < s[p[i].first]) {
            order++;
        }
        c[p[i].first] = order;
    }

    k = 1;
    while (k < n) {
        sort(ALL(p), compare);

        vector<int> nc(n);
        int order = 0;
        for (int i = 0; i < n; i++) {
            if (i != 0 && compare(p[i - 1], p[i])) {
                order++;
            }
            nc[p[i].second] = order;
        }
        c = nc;

        for (int i = 0; i < n; i++) {
            swap(p[i].first, p[i].second);
            p[i].second = i;
        }

        k += k;
    }

    for (int i = 0; i < n; i++) {
        if (c[i] == 0) {
            cout << i + 1 << "\n";
            return;
        }
    }
}

int main() {
    FAST();

    int tc;
    cin >> tc;

    while (tc--) {
        solve();
    }
}
