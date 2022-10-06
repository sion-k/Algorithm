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

int n, m;
vector<vector<int>> s;
vector<vector<int>> cache;

const int MOD = 1e9 + 7;

bool inRange(int y, int x) { return 0 <= y && y < n && 0 <= x && x < m; }

int dp(int y, int x) {
    if (y == n - 1 && x == m - 1) return 1;
    if (cache[y][x] != -1) return cache[y][x];

    int sum = 0;
    if (x % 2 == 0 && inRange(y - 1, x + 1) && !s[y - 1][x + 1]) {
        sum += dp(y - 1, x + 1);
    }

    if (sum >= MOD) sum -= MOD;

    if (x % 2 == 1 && inRange(y + 1, x + 1) && !s[y + 1][x + 1]) {
        sum += dp(y + 1, x + 1);
    }

    if (sum >= MOD) sum -= MOD;

    if (inRange(y, x + 1) && !s[y][x + 1]) {
        sum += dp(y, x + 1);
    }

    if (sum >= MOD) sum -= MOD;

    if (inRange(y + 1, x) && !s[y + 1][x]) {
        sum += dp(y + 1, x);
    }

    if (sum >= MOD) sum -= MOD;

    return cache[y][x] = sum;
}

int main() {
    FAST();

    cin >> n >> m;
    s = vector<vector<int>>(n, vector<int>(m));

    int k;
    cin >> k;
    for (int i = 0; i < k; i++) {
        int y, x;
        cin >> y >> x;
        y--, x--;
        s[y][x] = true;
    }

    cache = vector<vector<int>>(n, vector<int>(m, -1));
    cout << dp(0, 0) << "\n";
}
