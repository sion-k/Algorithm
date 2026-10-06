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

void solve() {
    string a, b;
    cin >> a >> b;

    int n = SIZE(a);
    assert(SIZE(a) == SIZE(b));

    vector<int> s, e;
    for (int i = 0; i < n; i++) {
        if (s[i] == '(') {
            s.push_back(i);
        } else {
            e.push_back(i);
        }
    }

    reverse(ALL(s));
    reverse(ALL(e));

    vector<int> p(n);

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] != b[i]) {

        }
    }

    cout << cnt << '\n';
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
