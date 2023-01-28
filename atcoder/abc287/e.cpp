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

int f(string& s, string& t) {
    int cnt = 0;

    for (int i = 0; i < min(SIZE(s), SIZE(t)); i++) {
        if (s[i] != t[i]) {
            break;
        }
        cnt++;
    }

    return cnt;
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<string, int>> s;
    for (int i = 0; i < n; i++) {
        string x;
        cin >> x;

        s.emplace_back(x, i);
    }

    sort(ALL(s));

    vector<pair<int, int>> r(n);
    for (int i = 0; i < n; i++) {
        r[i].second = s[i].second;

        r[i].first = max(i == 0 ? 0 : f(s[i - 1].first, s[i].first), i == n - 1 ? 0 : f(s[i].first, s[i + 1].first));
    }

    for (int i = 0; i < n; i++) {
        swap(r[i].first, r[i].second);
    }

    sort(ALL(r));

    for (int i = 0; i < n; i++) {
        cout << r[i].second << "\n";
    }
}
