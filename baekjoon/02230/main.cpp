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
    int n, m;
    cin >> n >> m;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    int head = 0; int tail = 0;
    int min = 2 * 1e9;

    while (head < n) {
        // abs(a[head] - a[tail]) >= m이 될 때 까지 tail을 증가시킵니다.
        while (tail + 1 < n && abs(a[head] - a[tail]) < m) {
            tail++;
        }

        // abs(a[head] - a[tail]) >= m 을 만족시키지 못할 수도 있습니다.
        int cand = abs(a[head] - a[tail]);
        if (cand >= m) {
            min = ::min(min, cand);
        }

        head++;
    }

    cout << min << "\n";
}
