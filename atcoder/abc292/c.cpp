#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

int f(int x) {
    int cnt = 0;

    for (int i = 1; i * i <= x; i++) {
        if (x % i == 0) {
            cnt++;
        }
    }

    return cnt;
}

bool g(int x) {
    for (int i = 1; i * i <= x; i++) {
        if (i * i == x) {
            return true;
        }
    }

    return false;
}

int main() {
    FAST();

    int n;
    cin >> n;

    long long ret = 0;


    for (int x = 1; x <= n - 1; x++) {
        int y = n - x;

        int ab = 2 * f(x);
        if (g(x)) {
            ab--;
        }

        int cd = 2 * f(y);
        if (g(y)) {
            cd--;
        }

        ret += (long long)ab * cd;
    }

    cout << ret << "\n";
}
