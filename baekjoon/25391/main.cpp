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

    int n, m, k;
    cin >> n >> m >> k;

    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }

    for (int i = 0; i < n; i++) {
        swap(a[i].first, a[i].second);
    }

    sort(ALL(a));
    reverse(ALL(a));

    long long sum = 0;
    for (int i = 0; i < k; i++) {
        sum += a[i].second;
    }

    for (int i = 0; i < n; i++) {
        swap(a[i].first, a[i].second);
    }

    vector<pair<int, int>> b(a.begin() + k, a.end());

    sort(ALL(b));
    reverse(ALL(b));

    for (int i = 0; i < m; i++) {
        sum += b[i].first;
    }

    cout << sum << "\n";
}
