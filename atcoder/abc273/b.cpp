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

long long f(long long x, int p) {
    long long t = 1;
    for (int i = 0; i < p; i++) {
        t *= 10;
    }

    long long r = x % t;

    if (r >= t / 2) {
        x += t - r;
    } else {
        x -= r;
    }
    return x;
}

int main() {
    FAST();

    long long x, k;
    cin >> x >> k;

    for (int i = 1; i <= k; i++) {
        x = f(x, i);
    }

    cout << x << "\n";
}
