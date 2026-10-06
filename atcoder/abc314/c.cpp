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

    int n, m;
    cin >> n >> m;

    string s, t(n, ' ');
    cin >> s;

    vector<int> c(n);
    for (int i = 0; i < n; i++) {
        cin >> c[i];
    }

    vector<vector<int>> a(m + 1);
    for (int i = 0; i < n; i++) {
        a[c[i]].push_back(i);
    }

    vector<queue<int>> d(m + 1);
    for (int i = 1; i <= m; i++) {
        d[i].push(a[i].back());
        a[i].pop_back();

        for (auto x : a[i]) {
            d[i].push(x);
        }
    }

    for (int i = 0; i < n; i++) {
        t[i] = s[d[c[i]].front()];
        d[c[i]].pop();
    }

    cout << t << "\n";
}
