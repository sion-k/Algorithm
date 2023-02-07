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

// 가장 오래 기다리는 소가 x초 이하 기다리게 하려면 필요한 버스의 개수
int f(int n, vector<int>& a, int c, int x) {
    int m = 0;
    int last = -1;

    for (int i = 0; i < n; i++) {
        if (last == -1 || i - last + 1 > c || a[i] - a[last] > x) {
            last = i;
            m++;
        }
    }

    return m;
}

int main() {
    FAST();

    int n, m, c;
    cin >> n >> m >> c;

    vector<int> a(n);
    for (auto& x : a) {
        cin >> x;
    }

    sort(ALL(a));

    int lo = -1, hi = 1e9;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;

        if (f(n, a, c, mid) <= m) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    cout << hi << "\n";
}
