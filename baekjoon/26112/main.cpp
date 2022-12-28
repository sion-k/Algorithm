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

int n, p, q;
vector<string> a, b, c;

vector<vector<vector<int>>> cache;

int dp(int k, int i, int j) {
    if (k == n) return 0;
    if (cache[k][i][j] != -1) return cache[k][i][j];

    int max = dp(k + 1, i, j);

    if (i < p) {
        max = ::max(max, dp(k, i + 1, j));

        if (a[k] == b[i]) {
            max = ::max(max, 1 + dp(k + 1, i + 1, j));
        }
    }

    if (j < q) {
        max = ::max(max, dp(k, i, j + 1));

        if (a[k] == c[j]) {
            max = ::max(max, 1 + dp(k + 1, i, j + 1));
        }
    }

    return cache[k][i][j] = max;
}


int main() {
    FAST();

    cin >> n >> p >> q;

    cache = vector<vector<vector<int>>>(n, vector<vector<int>>(p + 1, vector<int>(q + 1, -1)));

    a = vector<string>(n);
    for (auto& x : a) {
        cin >> x;
    }

    b = vector<string>(p);
    for (auto& x : b) {
        cin >> x;
    }

    c = vector<string>(q);
    for (auto& x : c) {
        cin >> x;
    }

    cout << dp(0, 0, 0) << "\n";
}
