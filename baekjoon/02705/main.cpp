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

vector<int> cache(1001);

int dp(int x) {
    if (x == 0) return 1;
    if (cache[x]) return cache[x];

    int sum = 0;
    for (int y = 0; y <= x; y += 2) {
        sum += dp((x - y) / 2);
    }

    return cache[x] = sum;
}

void solve() {
    int n;
    cin >> n;
    cout << dp(n) << "\n";
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
