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

    string s, t;
    cin >> s >> t;
    int n = SIZE(s), m = SIZE(t);

    vector<int> pre(m), suf(m);
    for (int i = 0; i < m; i++) {
        if ((i == 0 || pre[i - 1]) && (s[i] == t[i] || s[i] == '?' || t[i] == '?')) {
            pre[i] = true;
        }
    }

    for (int i = 0; i < m; i++) {
        if ((i == 0 || suf[i - 1]) && (s[n - 1 - i] == t[m - 1 - i] || s[n - 1 - i] == '?' || t[m - 1 - i] == '?')) {
            suf[i] = true;
        }
    }

    for (int x = 0; x <= m; x++) {
        if ((x == 0 || pre[x - 1]) && (m - x == 0 || suf[m - x - 1])) {
            cout << "Yes";
        } else {
            cout << "No";
        }
        cout << "\n";
    }
}
