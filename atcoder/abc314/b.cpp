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

    vector<int> a(n);
    vector<vector<int>> b(n, vector<int>(37));
    for (int i = 0; i < n; i++) {
        cin >> a[i];

        for (int j = 0; j < a[i]; j++) {
            int x;
            cin >> x;

            b[i][x] = true;
        }
    }

    int x;
    cin >> x;

    int min = 987654321;

    for (int i = 0; i < n; i++) {
        if (b[i][x]) {
            min = ::min(min, a[i]);
        }
    }

    int cnt = 0;
    for (int i = 0; i < n; i++) {
        if (b[i][x] && a[i] == min) {
            cnt++;
        }
    }

    cout << cnt << "\n";
    for (int i = 0; i < n; i++) if (b[i][x] && a[i] == min) {
        cout << i + 1 << " ";
    }

    cout << '\n';
}
