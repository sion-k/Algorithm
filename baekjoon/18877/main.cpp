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

#define ll long long
#define pll pair<ll, ll>

bool compare(pll a, pll b) {
    return a.second < b.second;
}

bool f(int n, int m, vector<pll>& a, ll d) {
    int cnt = 1;
    pll p(0, a[0].first);

    while (p.first < m) {
        if (p.second + d <= a[p.first].second) {
            p.second += d;
        } else {
            auto it = lower_bound(ALL(a), make_pair(0, p.second + d), compare);
            if (it == a.end()) {
                break;
            }

            p.first = it - a.begin();
            p.second = ::max(p.second + d, a[p.first].first);
        }

        cnt++;
    }

    return cnt >= n;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<pll> a(m);
    for (int i = 0; i < m; i++) {
        cin >> a[i].first >> a[i].second;
    }

    sort(ALL(a));

    ll lo = 0, hi = 1000000000000000001LL;
    while (lo + 1 < hi) {
        ll mid = (lo + hi) / 2;

        if (f(n, m, a, mid)) {
            lo = mid;
        } else {
            hi = mid;
        }
    }

    cout << lo << "\n";
}
