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

    int n, q;
    cin >> n >> q;

    string s;
    cin >> s;

    vector<int> r(n), b(n);
    r[n - 1] = n;
    for (int i = n - 2; i >= 0; i--) {
        r[i] = s[i] == 'R' ? i : r[i + 1];
    }

    b[0] = -1;
    for (int i = 1; i < n; i++) {
        b[i] = s[i] == 'B' ? i : b[i - 1];
    }

    for (int i = 0; i < q; i++) {
        int u, v;
        cin >> u >> v;

        vector<int> p;
        p.push_back(r[u]);

        if (r[u] + 1 < n) {
            p.push_back(r[r[u] + 1]);
        }

        if (b[v] - 1 >= 0) {
            p.push_back(b[b[v] - 1]);
        }

        p.push_back(b[v]);

        vector<int> q = p;
        sort(ALL(q));

        if (p != q || SIZE(p) != 4) {
            cout << -1 << "\n";
        } else {
            for (int j = 0; j < 4; j++) {
                cout << p[j] << " \n"[j == 3];
            }
        }
    }
}
