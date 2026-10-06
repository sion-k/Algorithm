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

    string s;
    cin >> s;

    set<pair<int, int>> a;
    pair<int, int> p;

    bool flag = false;

    for (int i = 0; i < n; i++) {
        if (a.count(p)) {
            flag = true;
        }
        a.insert(p);

        if (s[i] == 'R') {
            p.first++;
        } else if (s[i] == 'L') {
            p.first--;
        } else if (s[i] == 'U') {
            p.second++;
        } else {
            p.second--;
        }
    }

    if (a.count(p)) {
        flag = true;
    }

    cout << (flag ? "Yes" : "No") << "\n";
}
