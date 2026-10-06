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

    vector<int> a(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }

    sort(ALL(a));

    vector<int> p(n + 1);
    for (int i = 1; i <= n; i++) {
        p[i] = a[i] - a[i - 1] - 1;
        p[i] += p[i - 1];
    }

    int min = n - 1, max = 0;
    int head = 1, tail = 1;

    while (head <= n) {
        while (tail + 1 <= n && a[tail] - a[head] + 1 < n) {
            tail++;
        }

        if (a[tail] - a[head] + 1 >= n) {
            int sum = p[tail] - p[head];

            if (a[head] + n - 1 <= a[tail]) {
                int t = a[tail] - 1 - (a[head] + n - 1);

                if (t > 0) {
                    sum -= t;
                }
            }

            min = ::min(min, sum);
            max = ::max(max, sum);
        }

        head++;
    }

    cout << min << "\n";
    cout << max << "\n";
}
