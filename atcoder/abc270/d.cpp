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

int k;
vector<int> a;

vector<int> cache1, cache2;
vector<int> best1, best2;

int dp1(int n) {
    if (n == 0) return 0;
    if (cache1[n]) return cache1[n];
    int max = 0;
    for (int i = 0; i < k; i++) if (a[i] <= n) {
        int cand = a[i] + dp1(n - a[i] - (n - a[i] == 0 ? 0 : a[best2[n - a[i]]]));
        if (max < cand) {
            max = cand;
            best1[n] = i;
        }
    }
    return cache1[n] = max;
}

int dp2(int n) {
    if (n == 0) return 0;
    if (cache2[n]) return cache2[n];
    int max = 0;
    for (int i = 0; i < k; i++) if (a[i] <= n) {
        int cand = a[i] + dp2(n - a[i] - (n - a[i] == 0 ? 0 : a[best1[n - a[i]]]));
        if (max < cand) {
            max = cand;
            best2[n] = i;
        }
    }
    return cache2[n] = max;
}

int main() {
    FAST();

    int n;
    cin >> n >> k;

    a = vector<int>(k);
    for (auto& x : a) {
        cin >> x;
    }

    cache1 = vector<int>(n + 1);
    cache2 = vector<int>(n + 1);
    best1 = vector<int>(n + 1, -1);
    best2 = vector<int>(n + 1, -1);

    for (int i = 1; i <= n; i++) {
        dp1(i), dp2(i);
    }

    cout << dp1(n) << "\n";
}
