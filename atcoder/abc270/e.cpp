#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

long long sum(vector<long long>& a, long long k) {
    long long sum = 0;
    for (auto& x : a) {
        sum += min(x, k);
    }
    return sum;
}

int main() {
    FAST();
    int n;
    long long k;
    cin >> n >> k;

    vector<long long> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    long long lo = 0, hi = 1'000'000'000'000LL;
    while (lo + 1 < hi) {
        long long mid = (lo + hi) / 2;
        if (sum(a, mid) >= k) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    long long sum = 0;
    for (int i = 0; i < n; i++) {
        sum += min(a[i], lo);
        a[i] -= min(a[i], lo);
    }

    for (int i = 0; i < n; i++) {
        if (sum == k) {
            break;
        }
        if (a[i]) {
            a[i]--;
            sum++;
        }
    }

    for (int i = 0; i < n; i++) {
        cout << a[i] << " \n"[i == n - 1];
    }
}
