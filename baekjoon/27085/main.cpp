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

    int n, s;
    cin >> n >> s;
    s--;

    vector<long long> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int p = s, q = s;
    long long max = 0, sum = 0;
    while (true) {
        pair<long long, int> left(0, p), right(0, q);
        long long left_sum = 0, right_sum = 0;

        while (p - 1 >= 0 && sum + left_sum + a[p - 1] >= 0) {
            left_sum += a[p - 1];
            left = ::max(left, { left_sum, p - 1 });
            p--;
        }

        if (left.first > 0) {
            sum += left.first;
            p = left.second;
            max = ::max(max, sum);
            continue;
        }

        while (q + 1 < n && sum + right_sum + a[q + 1] >= 0) {
            right_sum += a[q + 1];
            right = ::max(right, { right_sum, q + 1 });
            q++;
        }

        if (right.first > 0) {
            sum += right.first;
            q = right.second;
            max = ::max(max, sum);
            continue;
        }

        break;
    }

    cout << max << "\n";
}
