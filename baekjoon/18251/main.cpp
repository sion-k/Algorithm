#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(taaaaaa) freopen("data.txt", (taaaaaa), (taaaaaa == "r" ? stdin : stdout))
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

int n;
int last_index = 0;
vector<int> a;
vector<vector<pair<int, int>>> t;

void inorder(int here, int depth) {
    int left = 2 * here;
    if (left <= n) {
        inorder(left, depth + 1);
    }
    t[depth].emplace_back(last_index++, a[here]);
    int right = 2 * here + 1;
    if (right <= n) {
        inorder(right, depth + 1);
    }
}

// [start, end] 높이의 노드들을 x축 순서대로 반환
vector<pair<int, int>> range(int start, int end) {
    vector<pair<int, int>> ret;
    for (int i = start; i <= end; i++) {
        ret.insert(ret.end(), ALL(t[i]));
    }
    sort(ALL(ret));
    return ret;
}

// n길이 배열 a에서 최대 연속 부분합을 반환
long long f(int n, vector<pair<int, int>> &a) {
    vector<long long> dp(n);
    for (int i = 0; i < n; i++) {
        dp[i] = a[i].second;
        if (i - 1 >= 0) {
            dp[i] = max(dp[i], dp[i - 1] + a[i].second);
        }
    }
    return *max_element(ALL(dp));
}

int main() {
    FAST();
    cin >> n;
    a = vector<int>(n + 1);
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    t = vector<vector<pair<int, int>>>(18);
    inorder(1, 0);
    long long max = a[1];
    for (int i = 0; i < 18; i++) {
        for (int j = i; j < 18; j++) {
            vector<pair<int, int>> c = range(i, j);
            if (c.empty()) continue;
            max = ::max(max, f(SIZE(c), c));
        }
    }
    cout << max << "\n";
}
