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

int n;
vector<pair<int, int>> a;

bool compare(pair<int, int>& u, pair<int, int>& v) {
    if (u.first == v.first) {
        return u.second > v.second;
    } else {
        return u.first < v.first;
    }
}

bool f(long long k) {
    long long p = -1;

    for (auto [x, l] : a) {
        if (p == -1) {
            p = x;
        } else if (max(p + k, (long long)x) <= x + l) {
            p = max(p + k, (long long)x);
        } else {
            return false;
        }
    }

    return true;
}

int main() {
    FAST();

    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, l;
        cin >> x >> l;
        a.emplace_back(x, l);
    }

    sort(ALL(a));

    long long lo = 0, hi = 2 * 1e9 + 1;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (f(mid)) {
            lo = mid;
        } else {
            hi = mid;
        }
    }

    cout << lo << "\n";
}
