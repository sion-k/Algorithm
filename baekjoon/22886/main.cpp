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

const int INF = 987654321;

vector<pair<int, int>> f(int n, vector<int>& a) {
    vector<pair<int, int>> s, r(n, { 0, INF });

    for (int i = 0; i < n; i++) {
        while (!s.empty() && s.back().first <= a[i]) {
            s.pop_back();
        }

        if (!s.empty()) {
            r[i].first += SIZE(s);
            r[i].second = s.back().second;
        }

        s.emplace_back(a[i], i);
    }

    return r;
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<pair<int, int>> left = f(n, a);
    reverse(ALL(a));

    vector<pair<int, int>> right = f(n, a);
    reverse(ALL(right));
    for (int i = 0; i < n; i++) if (right[i].second != INF) {
        right[i].second = n - 1 - right[i].second;
    }

    for (int i = 0; i < n; i++) {
        int c = left[i].first + right[i].first;
        cout << c;

        if (c) {
            cout << " ";

            pair<int, int> p1(abs(left[i].second - i), left[i].second);
            pair<int, int> p2(abs(right[i].second - i), right[i].second);

            cout << min(p1, p2).second + 1;
        }

        cout << "\n";
    }
}
