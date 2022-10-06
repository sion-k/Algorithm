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

long long cache[60 + 1][3 + 1][3 + 1] = {};

long long dp(int n, int u, int v) {
    if (n == 0) return 0;
    if (cache[n][u][v]) return cache[n][u][v];

    int w = 6 - (u + v);

    long long sum = 0;
    sum += dp(n - 1, u, w);
    sum += 1;
    sum += dp(n - 1, w, v);

    return cache[n][u][v] = sum;
}

bool flag;

void f(int n, int u, int v, long long k) {
    if (n == 0 || flag) return;

    int w = 6 - (u + v);
    if (dp(n - 1, u, w) >= k) {
        f(n - 1, u, w, k);
        return;
    }
    k -= dp(n - 1, u, w);

    k--;
    if (k == 0) {
        flag = true;
        cout << u << " " << v << "\n";
    }
    f(n - 1, w, v, k);
}

int main() {
    FAST();

    int n;
    long long k;
    cin >> n >> k;

    f(n, 1, 3, k);
}
