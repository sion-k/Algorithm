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

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> a;
    for (int i = 0; i < n; i++) {
        int l, r;
        cin >> l >> r;

        a.emplace_back(l, r);
    }

    sort(ALL(a));

    vector<pair<int, int>> b;
    for (int i = 0; i < n; i++) {
        auto [l, r] = a[i];

        if (!b.empty() && a[i].first <= b.back().second) {
            b.back().second = max(b.back().second, a[i].second);
        } else {
            b.emplace_back(a[i]);
        }
    }

    int max = b[0].second, range = 2 * b[0].second;
    for (auto [l, r] : b) {
        if (l <= range) {
            range = ::max(range, r + r - l);
            max = ::max(max, r);
        } else {
            break;
        }
    }

    cout << max << '\n';
}
