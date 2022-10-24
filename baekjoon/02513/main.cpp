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

int solve(vector<pair<int, int>>& a, int k) {
    sort(ALL(a));
    reverse(ALL(a));

    int ret = 0, sum = 0, pos = -1;
    for (int i = 0; i < SIZE(a); i++) {
        int dif = min(k - sum, a[i].second);

        if (pos == -1) {
            pos = a[i].first;
        }

        a[i].second -= dif;
        sum += dif;

        if (sum == k) {
            ret += 2 * pos;
            pos = -1;
            sum = 0;
        }

        if (a[i].second) i--;
    }

    if (pos != -1) {
        ret += 2 * pos;
    }

    return ret;
}

int main() {
    FAST();

    int n, k, s;
    cin >> n >> k >> s;

    vector<pair<int, int>> left, right;
    for (int i = 0; i < n; i++) {
        int p, c;
        cin >> p >> c;

        if (p < s) {
            left.emplace_back(s - p, c);
        } else {
            right.emplace_back(p - s, c);
        }
    }

    int ret = solve(left, k) + solve(right, k);

    cout << ret << "\n";
}
