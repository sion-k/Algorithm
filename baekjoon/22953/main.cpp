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

long long f(int n, vector<int>& a, long long t) {
    long long cnt = 0;

    for (int i = 0; i < n; i++) {
        cnt += t / a[i];
    }

    return cnt;
}

long long g(int n, vector<int>& a, int k) {
    long long lo = 0, hi = 1000000000000LL;

    while (lo + 1 < hi) {
        long long mid = (lo + hi) / 2;

        if (f(n, a, mid) >= k) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    return hi;
}

long long btk(int n, int i, int c, vector<int>& a, int k) {
    if (i == n) {
        return g(n, a, k);
    }

    long long min = btk(n, i + 1, c, a, k);

    if (c && a[i] > 1) {
        a[i]--;
        min = ::min(min, btk(n, i, c - 1, a, k));
        a[i]++;
    }

    return min;
}

int main() {
    FAST();

    int n, k, c;
    cin >> n >> k >> c;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    cout << btk(n, 0, c, a, k) << "\n";
}
