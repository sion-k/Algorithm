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

int n;
vector<vector<int>> c;
vector<int> x, y;

const int INF = 1e9 + 1;

int dp(int here, int life) {
    if (here == n) return 0;
    if (c[here][life] != -1) return c[here][life];

    int min = INF;

    // 오라로 받을 수 있는 경우
    if (x[here] != -1) {
        if (y[here] == -1) {
            min = ::min(min, dp(here + 1, life));
        } else {
            min = ::min(min, dp(here + 1, life) + x[here]);
        }
    }

    // 라이프로 받을 수 있는 경우
    if (y[here] != -1 && life > y[here]) {
        min = ::min(min, dp(here + 1, life - y[here]));
    }

    return c[here][life] = min;
}

void reconstruct(int here, int life, string& ans) {
    if (here == n) return;
    if (x[here] != -1 && (c[here][life] == dp(here + 1, life) + x[here] || c[here][life] == dp(here + 1, life))) {
        ans.push_back('A');
        reconstruct(here + 1, life, ans);
    } else {
        ans.push_back('L');
        reconstruct(here + 1, life - y[here], ans);
    }
}

int main() {
    FAST();

    int a, l;
    cin >> n >> a >> l;

    x.resize(n);
    y.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> x[i] >> y[i];
    }

    c.resize(n, vector<int>(l + 1, -1));

    if (dp(0, l) <= a) {
        cout << "YES" << "\n";

        string ans;
        reconstruct(0, l, ans);
        cout << ans << "\n";
    } else {
        cout << "NO" << "\n";
    }
}
