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

// k개의 무대로 끝나는 시간 (k <= n)
long long f(int n, int k, vector<int>& a) {
    priority_queue<long long, vector<long long>, greater<long long>> pq;
    int i = 0;
    while (i < k) {
        pq.push(a[i]);
        i++;
    }

    long long t = 0;
    while (!pq.empty()) {
        t = max(t, pq.top());
        pq.pop();

        if (i < n) {
            pq.push(t + a[i]);
            i++;
        }
    }

    return t;
}

int main() {
    FAST();

    int n, t;
    cin >> n >> t;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    long long lo = 0, hi = 10000000000LL;
    while (lo + 1 < hi) {
        long long mid = (lo + hi) / 2;

        if (f(n, min(mid, (long long)n), a) <= t) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    cout << hi << "\n";
}
