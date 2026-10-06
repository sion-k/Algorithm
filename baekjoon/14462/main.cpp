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
vector<int> a, p;
vector<vector<int>> c;

int dp(int here, int last) {
    if (here == n + 1) return 0;
    if (c[here][last] != -1) return c[here][last];

    int max = dp(here + 1, last);

    int min = n + 1;
    for (int i = ::max(a[here] - 4, 1); i <= ::min(a[here] + 4, n); i++) {
        if (last < p[i] && min > p[i]) {
            min = p[i];
        }
    }

    if (min != n + 1) {
        max = ::max(max, 1 + dp(here + 1, min));
    }

    return c[here][last] = max;
}

int main() {
    FAST();

    cin >> n;
    a.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    p.resize(n + 1);
    for (int i = 1; i <= n; i++) {
        int x;
        cin >> x;

        p[x] = i;
    }

    c.resize(n + 1, vector<int>(n + 1, -1));
    cout << dp(1, 0) << '\n';
}
