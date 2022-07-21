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

// x 길이의 수의 개수
long long f(int x) {
    return 9 * pow(10LL, x - 1);
}

// x 길이 이하의 수를 순서대로 나열했을 때 길이
long long g(int x) {
    long long sum = 0;
    for (int i = 1; i <= x; i++) {
        sum += i * f(i);
    }
    return sum;
}

// g(j) < k 를 만족하는 최대 j를 구하고
// j + 1과 k 에서 g(j)를 빼준 값을 반환
pair<int, int> h(int k) {
    int j = 0;
    while (g(j + 1) < k) {
        j++;
    }
    pair<int, int> ret(j + 1, k - g(j) - 1);
    return ret;
}

int main() {
    FAST();
    int n, k;
    cin >> n >> k;
    auto [p, q] = h(k);
    int m = pow(10, p - 1) + q / p;
    if (m > n) {
        cout << -1 << "\n";
    } else {
        string s = to_string(m);
        cout << s[q % p] << "\n";
    }
}
