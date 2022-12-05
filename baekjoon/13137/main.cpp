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

int n;
vector<int> a;

vector<int> cache1, cache2;

int naive(int x) {
    if (x == 0) return 0;
    if (cache1[x]) return cache1[x];

    int max = 0;
    for (int i = 0; i < n; i++) if (a[i] <= x) {
        max = a[i];
    }

    return cache1[x] = 1 + naive(x - max);
}

int dp(int x) {
    if (x == 0) return 0;
    if (cache2[x]) return cache2[x];

    int min = x;
    for (int i = 0; i < n; i++) if (a[i] <= x) {
        min = ::min(min, 1 + dp(x - a[i]));
    }

    return cache2[x] = min;
}

int main() {
    FAST();

    cin >> n;

    a = vector<int>(n);
    for (auto& x : a) {
        cin >> x;
    }

    cache1 = vector<int>(200001);
    cache2 = vector<int>(200001);

    bool flag = true;

    for (int i = 0; i < n; i++) {
        int here = a[i];
        int there = i == n - 1 ? 2 * a[n - 1] : a[i + 1];

        for (int x = here + 1; x < there; x++) {
            if (naive(x) != dp(x)) {
                flag = false;
            }
        }
    }

    cout << (flag ? "Yes" : "No") << "\n";
}
