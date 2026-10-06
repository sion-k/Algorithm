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
string s;

int m;
vector<string> a;
vector<int> b, c;

int dp(int i) {
    if (i == n) {
        return 0;
    }
    if (c[i] != -1) {
        return c[i];
    }

    int max = 1 + dp(i + 1);
    for (int j = 0; j < m; j++) {
        if (i + SIZE(a[j]) <= n && s.substr(i, SIZE(a[j])) == a[j]) {
            max = ::max(max, b[j] + dp(i + SIZE(a[j])));
        }
    }

    return c[i] = max;
}

int main() {
    FAST();

    cin >> s;
    n = SIZE(s);

    cin >> m;
    a.resize(m);
    b.resize(m);
    for (int i = 0; i < m; i++) {
        cin >> a[i] >> b[i];
    }

    c.resize(n, -1);

    cout << dp(0) << '\n';
}
