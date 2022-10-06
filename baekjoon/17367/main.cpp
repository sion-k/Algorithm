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

int f(int a, int b, int c) {
    if (a == b && b == c) return 10'000 + a * 1'000;
    if (a == b || b == c) return 1'000 + b * 100;
    if (a == c) return 1'000 + a * 100;
    return max(a, max(b, c)) * 100;
}

int n;
vector<vector<vector<double>>> cache;

double dp(int i, int a, int b) {
    if (i == n) return 0;
    if (cache[i][a][b] != -1) return cache[i][a][b];

    double sum = 0;
    for (int j = 1; j <= 6; j++) {
        sum += max(i >= 2 ? (double)f(a, b, j) : 0, dp(i + 1, b, j));
    }
    sum /= (double)6;

    return cache[i][a][b] = sum;
}

int main() {
    FAST();

    cin >> n;

    cache = vector<vector<vector<double>>>(n, vector<vector<double>>(7, vector<double>(7, -1)));

    cout.precision(15);

    cout << dp(0, 0, 0) << "\n";
}
