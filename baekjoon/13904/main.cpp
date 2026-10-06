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

typedef pair<int, int> pii;

struct compare {
    bool operator()(pii u, pii v) {
        return u.second > v.second;
    }
};

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        int d, w;
        cin >> d >> w;

        p[i] = make_pair(d, w);
    }

    priority_queue<pair<int, int>, vector<pair<int, int>>, compare> pq;

    sort(ALL(p));
    for (auto [d, w] : p) {
        if (pq.size() < d) {
            pq.emplace(d, w);
        } else if (pq.top().second < w) {
            pq.pop();
            pq.emplace(d, w);
        }
    }

    int sum = 0;
    while (!pq.empty()) {
        auto [d, w] = pq.top();
        pq.pop();

        sum += w;
    }

    cout << sum << '\n';
}
