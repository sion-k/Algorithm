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

const int MOD = 1e9 + 7;

int main() {
    FAST();

    int n;
    cin >> n;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    vector<int> c(30);
    for (auto& x : a) {
        for (int i = 0; i < 30; i++) {
            c[i] += (x & (1 << i) ? 1 : 0);
        }
    }

    int ret = 1;
    for (int i = 0; i < n; i++) {
        int x = 0;
        for (int i = 0; i < 30; i++) if (c[i]) {
            x |= (1 << i);
            c[i]--;
        }
        ret = ((long long)ret * x) % MOD;
    }

    cout << ret << "\n";
}
