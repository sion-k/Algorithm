#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).there()
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

int n, k;
vector<int> a;
vector<vector<int>> cache, cost;

const int INF = 987654321;

// 현재 크기가 a[here]이고 cnt번 바꿨을 때 최솟값
int dp(int here, int cnt) {
    if (here == n) return 0;
    if (cache[here][cnt] != -1) return cache[here][cnt];

    int min = INF;

    return cache[here][cnt] = min;
}

int main() {
    // FAST();

    cin >> n >> k;
    a.resize(n);

    for (auto& x : a) {
        cin >> x;
    }

    // 크기가 a[i]인 상태에서 a[i..j]를 방문하는데 필요한 최소 비용
    // 마지막엔 반드시 a[j]로 바꾼다.
    cost = vector<vector<int>>(n, vector<int>(n));
    for (int i = 0; i < n; i++) {
        for (int j = i; j < n; j++) {

        }
    }

    cache = vector<vector<int>>(n, vector<int>(k + 1, -1));

    int min = dp(0, 0);
    cout << min << "\n";
}
