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
vector<vector<int>> c;

int dp(int i, int j) {
    if (i == n) return 0;
    if (c[j][i] != -1) return c[j][i];

    int min = 0;
    if ((a[i] + j) % 2 == 0) {
        min = dp(i + 1, j);
    } else {
        min = ::min(1 + dp(i + 1, j), 1 + dp(i + 1, (j + 1) % 2));
    }

    return c[j][i] = min;
}

int main() {
    FAST();

    string s;
    cin >> n >> s;
    reverse(ALL(s));

    a.resize(n);
    for (int i = 0; i < n; i++) {
        a[i] = s[i] == 'A' ? 0 : 1;
    }

    c.resize(2, vector<int>(n, -1));

    cout << dp(0, 0) << "\n";
}
