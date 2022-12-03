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
vector<int> cache;

int dp(long long i) {
    if (i > k) return 0;
    if (cache[i] != -1) return cache[i];

    for (long long j = 1; j * j <= i; j++) {
        if (i % j == 0 && (i + j <= k && dp(i + j) == 0 || i + i / j <= k && dp(i + i / j) == 0)) {
            return cache[i] = true;
        }
    }

    return cache[i] = false;
}

int main() {
    FAST();

    cin >> k;
    cache = vector<int>(k + 1, -1);

    cout << (dp(1) ? "Kali" : "Ringo") << "\n";
}
