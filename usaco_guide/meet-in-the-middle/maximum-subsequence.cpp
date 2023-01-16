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

vector<int> f(vector<int> a, int m) {
    vector<int> b{ 0 };

    for (auto x : a) {
        b.resize(2 * SIZE(b));

        for (int i = 0; i < SIZE(b) / 2; i++) {
            b[SIZE(b) / 2 + i] = (b[i] + x) % m;
        }
    }

    sort(ALL(b));
    return b;
}

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<int> left = f(vector<int>(a.begin(), a.begin() + n / 2), m);
    vector<int> right = f(vector<int>(a.begin() + n / 2, a.end()), m);

    int max = 0;
    for (auto x : left) {
        auto it = upper_bound(ALL(right), m - 1 - x);

        if (it != right.begin()) {
            it--;
        }

        max = ::max(max, x + *it);
    }

    cout << max << "\n";
}
