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

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int m = accumulate(ALL(a), 0);

    vector<int> b{ 0 };

    for (auto& x : a) {
        b.resize(2 * SIZE(b));
        for (int i = 0; i < SIZE(b) / 2; i++) {
            b[SIZE(b) / 2 + i] = b[i] + x;
        }
    }

    sort(ALL(b));

    for (int i = 1; i < SIZE(b); i++) {
        if (b[i] != b[i - 1]) {
            m--;
        }
    }

    cout << m << "\n";
}
