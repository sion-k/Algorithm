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

mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());

const long long b = 1e9 + 7;
const long long a = uniform_int_distribution<long long>(0, b - 1)(rng);

int main() {
    FAST();

    string s, t;
    cin >> s >> t;

    int n = SIZE(s), m = SIZE(t);

    vector<long long> p;
    p.push_back(1);
    for (int i = 1; i <= n - 1; i++) {
        p.push_back((p.back() * a) % b);
    }

    long long y = t[0];
    for (int i = 1; i < m; i++) {
        y = (y * a + t[i]) % b;
    }

    vector<long long> h;
    string ret;

    for (int i = 0; i < n; i++) {
        if (h.empty()) {
            h.push_back(s[i]);
        } else {
            h.push_back((h.back() * a + s[i]) % b);
        }

        ret.push_back(s[i]);

        if (SIZE(h) >= SIZE(t)) {
            int r = SIZE(h) - 1;
            int l = r - SIZE(t) + 1;

            long long x = h[r];

            if (l - 1 >= 0) {
                x = (x - (h[l - 1] * p[r - l + 1]) % b + b) % b;
            }

            if (x == y) {
                for (int j = 0; j < SIZE(t); j++) {
                    h.pop_back();
                    ret.pop_back();
                }
            }
        }
    }

    cout << ret << "\n";
}
