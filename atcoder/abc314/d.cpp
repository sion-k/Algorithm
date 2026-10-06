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

    int n;
    cin >> n;

    string s;
    cin >> s;

    vector<pair<int, char>> a(n, { -1, ' ' });

    int q;
    cin >> q;

    int l = -1, u = -1;

    for (int i = 0; i < q; i++) {
        int t, x;
        char c;

        cin >> t >> x >> c;
        x--;

        if (t == 1) {
            a[x] = make_pair(i, c);
        } else if (t == 2) {
            l = i;
        } else if (t == 3) {
            u = i;
        }
    }

    for (int i = 0; i < n; i++) {
        if (a[i].first != -1) {
            s[i] = a[i].second;
        }

        int x = a[i].first;

        if ('a' <= s[i] && s[i] <= 'z' && l < u && x < u) {
            s[i] -= ('a' - 'A');
        } else if ('A' <= s[i] && s[i] <= 'Z' && u < l && x < l) {
            s[i] += ('a' - 'A');
        }
    }

    cout << s << '\n';
}
