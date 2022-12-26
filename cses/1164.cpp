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

    int n;
    cin >> n;

    vector<tuple<int, int, int>> a;
    for (int i = 0; i < n; i++) {
        int u, v;
        cin >> u >> v;

        a.emplace_back(u, 1, i);
        a.emplace_back(v + 1, -1, i);
    }

    sort(ALL(a));

    int max = 0, sum = 0;
    for (int i = 0; i < 2 * n; i++) {
        auto [u, v, t] = a[i];

        sum += v;
        max = ::max(max, sum);
    }

    cout << max << "\n";

    vector<int> b(n);
    priority_queue<int> pq;

    for (int k = 1; k <= max; k++) {
        pq.push(k);
    }

    for (int i = 0; i < 2 * n; i++) {
        auto [u, v, t] = a[i];

        if (v == 1) {
            b[t] = pq.top();
            pq.pop();
        } else {
            pq.push(b[t]);
        }
    }

    for (int i = 0; i < n; i++) {
        cout << b[i] << " \n"[i == n - 1];
    }
}
