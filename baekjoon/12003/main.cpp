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

    int n, k;
    cin >> n >> k;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    vector<int> b(n);

    int head = 0, tail = 0;
    while (head < n) {
        while (tail + 1 < n && a[tail + 1] - a[head] <= k) {
            tail++;
        }

        b[head] = tail;
        head++;
    }

    vector<int> p(n);
    for (int i = n - 1; i >= 0; i--) {
        if (i + 1 < n) {
            p[i] = p[i + 1];
        }

        p[i] = ::max(p[i], b[i] - i + 1);
    }

    int max = 0;

    for (int here = 0; here < n; here++) {
        int cand = b[here] - here + 1;

        if (b[here] + 1 < n) {
            cand += p[b[here] + 1];
        }

        max = ::max(max, cand);
    }

    cout << max << "\n";
}
