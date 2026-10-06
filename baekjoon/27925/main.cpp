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
vector<int> a;
vector<vector<vector<vector<int>>>> cache;

int dist(int u, int v) {
    if (u < v) swap(u, v);
    return min(u - v, 9 - u + v + 1);
}

int dp(int i, int d1, int d2, int d3) {
    if (i == n) {
        return 0;
    }
    if (cache[d1][d2][d3][i] != -1) return cache[d1][d2][d3][i];

    int min = 987654321;

    min = ::min(min, dist(d1, a[i]) + dp(i + 1, a[i], d2, d3));
    min = ::min(min, dist(d2, a[i]) + dp(i + 1, d1, a[i], d3));
    min = ::min(min, dist(d3, a[i]) + dp(i + 1, d1, d2, a[i]));

    return cache[d1][d2][d3][i] = min;
}

int main() {
    FAST();

    cin >> n;
    a.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    cache = vector<vector<vector<vector<int>>>>(10, vector<vector<vector<int>>>(10, vector<vector<int>>(10, vector<int>(n, -1))));
    cout << dp(0, 0, 0, 0) << "\n";
}
