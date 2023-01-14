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

unordered_map<int, int> f(vector<int>& a, int x) {
    int n = SIZE(a);
    unordered_map<int, int> m;

    for (int i = 1; i < (1 << n); i++) {
        long long sum = 0;

        for (int j = 0; j < n; j++) if (i & (1 << j)) {
            sum += a[j];
        }

        if (sum <= x) {
            m[sum]++;
        }
    }

    return m;
}

int main() {
    FAST();

    int n, x;
    cin >> n >> x;

    vector<int> t(n);
    for (auto& x : t) {
        cin >> x;
    }

    vector<int> a(t.begin(), t.begin() + n / 2);
    vector<int> b(t.begin() + n / 2, t.end());

    auto left = f(a, x), right = f(b, x);

    long long sum = left[x] + right[x];
    for (auto p : left) {
        if (right.count(x - p.first)) {
            sum += (long long)p.second * right[x - p.first];
        }
    }

    cout << sum << "\n";
}
