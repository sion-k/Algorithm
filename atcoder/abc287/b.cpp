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

    vector<string> s(n), t(m);
    for (int i = 0; i < n; i++) {
        cin >> s[i];
        s[i] = s[i].substr(3);
    }

    for (int i = 0; i < m; i++) {
        cin >> t[i];
    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        bool flag = false;

        for (int j = 0; j < m; j++) {
            if (s[i] == t[j]) {
                flag = true;
            }
        }

        if (flag) {
            cnt++;
        }
    }

    cout << cnt << "\n";
}
