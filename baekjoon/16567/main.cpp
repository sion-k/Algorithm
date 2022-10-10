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

    int n, m;
    cin >> n >> m;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    int sum = a[0];
    for (int i = 1; i < n; i++) {
        sum += !a[i - 1] && a[i];
    }

    for (int i = 0; i < m; i++) {
        int x, k;
        cin >> x;
        if (x == 1) {
            cin >> k;
            k--;

            if (a[k]) continue;
            a[k] = true;

            sum += (k == 0 || !a[k - 1]) && (k == n - 1 || !a[k + 1]);

            sum -= 0 <= k - 1 && k + 1 <= n - 1 && a[k - 1] && a[k + 1];
        } else {
            cout << sum << "\n";
        }
    }
}
