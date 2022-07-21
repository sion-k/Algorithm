#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

// based on dotorya's source code
// https://www.acmicpc.net/source/584569

// 1부터 x까지 나열했을 때의 길이
int f(int x) {
    int sum = 0;
    for (int i = 1; i <= x; i *= 10) {
        sum += x - i + 1;
    }
    return sum;
}

// k <= f(hi)를 만족하는 최소의 hi 반환
int g(int n, int k) {
    int lo = 0, hi = n + 1;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (f(mid) >= k) hi = mid;
        else lo = mid;
    }
    return hi;
}

int main() {
    FAST();
    int n, k;
    cin >> n >> k;
    int t = g(n, k);
    if (t == n + 1) {
        cout << -1 << "\n";
    } else {
        string s = to_string(t);
        cout << s[k - f(t - 1) - 1] << "\n";
    }
}
