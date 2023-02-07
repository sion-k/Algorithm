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

int f(int n, vector<int>& a, int r) {
    int k = 0;
    int last = -1;

    for (int i = 0; i < n; i++) {
        if (last == -1 || a[last] + 2 * r < a[i]) {
            k++;
            last = i;
        }
    }

    return k;
}

int main() {
    FAST();

    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    int lo = -1, hi = 1e9;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;

        if (f(n, a, mid) <= k) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    cout << hi << "\n";
}
