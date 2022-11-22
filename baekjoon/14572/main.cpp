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

int main() {
    FAST();

    int n, k, d;
    cin >> n >> k >> d;

    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        int m, p;
        cin >> m >> p;

        a[i].first = p;

        int b = 0;
        for (int j = 0; j < m; j++) {
            int t;
            cin >> t;
            b |= (1 << (t - 1));
        }

        a[i].second = b;
    }

    sort(ALL(a));

    int max = 0;

    int head = 0, tail = 0;
    vector<int> b(30);

    while (head < n) {
        while (tail < n && a[tail].first - a[head].first <= d) {
            for (int i = 0; i < 30; i++) {
                b[i] += a[tail].second & (1 << i) ? 1 : 0;
            }

            tail++;
        }

        int sum = 0;
        for (int i = 0; i < 30; i++) if (b[i]) {
            sum++;
        }

        int product = 0;
        for (int i = 0; i < 30; i++) if (b[i] == tail - head) {
            product++;
        }

        max = ::max(max, (sum - product) * (tail - head));

        for (int i = 0; i < 30; i++) {
            b[i] -= a[head].second & (1 << i) ? 1 : 0;
        }

        head++;
    }

    cout << max << "\n";
}
