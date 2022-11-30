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

    vector<pair<int, int>> a;
    a.emplace_back(1, 1);
    a.emplace_back(-1, -1);
    a.emplace_back(1, -1);
    a.emplace_back(-1, 1);

    vector<int> f = { 0, 3, 2, 1 };

    int t = f[n % 4];
    for (int i = 0; i < n; i++) {
        cout << a[t].first << " " << a[t].second << " \n"[i == n - 1];
        t = (t + 1) % 4;
    }
}
