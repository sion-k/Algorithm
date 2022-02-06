#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, m, k;
int s[10][10];
int cache[10][51][1 << 10];

const int INF = 987654321;

int btk(int, int, int, int, int);

int dp(int here, int pick, int mask) {
    if (here == n) return pick ? -INF : 0;
    int &ret = cache[here][pick][mask];
    if (ret != -INF) return ret;
    return ret = btk(here, 0, mask, 0, pick);
}

// (y, x)부터 순서대로 선택하면서 이전 선택 여부가 prev
// 현재 선택하는 여부가 here에 저장
int btk(int y, int x, int prev, int here, int pick) {
    if (x == m) return dp(y + 1, pick, here);
    int max = btk(y, x + 1, prev, here, pick);
    // 선택할 수 있는 경우
    if (pick && (prev & (1 << x)) == 0 && (x == 0 || (here & (1 << (x - 1))) == 0)) {
        max = std::max(max, s[y][x] + btk(y, x + 1, prev, here | (1 << x), pick - 1));
    }
    return max;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m >> k;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> s[i][j];
        }
    }
    for (int here = 0; here < n; here++) {
        for (int pick = 0; pick <= k; pick++) {
            for (int mask = 0; mask < (1 << m); mask++) {
                cache[here][pick][mask] = -INF;
            }
        }
    }
    cout << dp(0, k, 0) << "\n";
}
