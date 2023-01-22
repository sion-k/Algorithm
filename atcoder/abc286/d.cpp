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

int n, x;
vector<int> a, b;

vector<vector<int>> cache;

int dp(int here, int sum) {
    if (here == n) {
        return sum == x;
    }

    if (cache[here][sum] != -1) return cache[here][sum];

    bool flag = dp(here + 1, sum);

    for (int i = 1; i <= b[here]; i++) {
        if (sum + a[here] * i <= x) {
            flag |= dp(here + 1, sum + a[here] * i);
        }
    }

    return cache[here][sum] = flag;
}

int main() {
    FAST();

    cin >> n >> x;

    a.resize(n), b.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }

    cache = vector<vector<int>>(n, vector<int>(x + 1, -1));

    cout << (dp(0, 0) ? "Yes" : "No") << "\n";
}
