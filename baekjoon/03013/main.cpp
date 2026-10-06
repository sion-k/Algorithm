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

    int n, b;
    cin >> n >> b;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int t = 0;
    for (int i = 0; i < n; i++) {
        if (a[i] == b) {
            t = i;
        }
    }

    int d = 0;
    map<int, int> l;
    for (int i = t - 1; i >= 0; i--) {
        if (a[i] > b) {
            d++;
        } else {
            d--;
        }

        l[d]++;
    }

    d = 0;
    map<int, int> r;
    for (int i = t + 1; i < n; i++) {
        if (a[i] > b) {
            d++;
        } else {
            d--;
        }

        r[d]++;
    }

    long long sum = 1 + l[0] + r[0];

    for (auto [k, v] : l) {
        sum += (long long)v * r[-k];
    }

    cout << sum << "\n";
}
