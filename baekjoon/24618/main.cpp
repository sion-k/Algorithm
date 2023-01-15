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

vector<vector<pair<long long, long long>>> f(vector<pair<int, int>> a) {
    int n = SIZE(a);
    vector<vector<pair<long long, long long>>> ret(41);

    for (int i = 0; i < (1 << n); i++) {
        pair<long long, long long> sum;

        for (int j = 0; j < n; j++) if (i & (1 << j)) {
            sum.first += a[j].first;
            sum.second += a[j].second;
        }

        int c = __builtin_popcountll(i);

        ret[c].push_back(sum);
    }

    for (int i = 0; i <= 40; i++) {
        sort(ALL(ret[i]));
    }

    return ret;
}

int xg, yg;

long long count(vector<pair<long long, long long>>& left, vector<pair<long long, long long>>& right) {
    long long cnt = 0;

    for (auto [x, y] : left) {
        long long dx = xg - x, dy = yg - y;

        auto start = lower_bound(ALL(right), make_pair(dx, dy));
        auto end = upper_bound(ALL(right), make_pair(dx, dy));

        cnt += end - start;
    }

    return cnt;
}

int main() {
    FAST();

    int n;
    cin >> n >> xg >> yg;

    vector<pair<int, int>> a(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i].first >> a[i].second;
    }

    auto left = f(vector<pair<int, int>>(a.begin(), a.begin() + n / 2));
    auto right = f(vector<pair<int, int>>(a.begin() + n / 2, a.end()));

    for (int k = 1; k <= n; k++) {
        long long sum = 0;

        for (int i = 0; i <= k; i++) {
            sum += count(left[i], right[k - i]);
        }

        cout << sum << "\n";
    }
}
