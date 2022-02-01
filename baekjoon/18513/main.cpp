#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, k;
long long ret;
int max;
vector<int> s;
const int INF = 987654321;

// 가장 가까운 샘터까지의 거리가 dist인
// 후보 지점의 개수만큼 뺀다
// k가 0이면 중단
void f(int dist) {
    if (dist > ::max / 2 + 1) {
        k--;
        ret += dist;
        if (k == 0) return;
        k--;
        ret += dist;
        return;
    }
    for (int i = 1; i <= n; i++) {
        if (k == 0) return;
        if (s[i - 1] + dist <= s[i] - dist) {
            k--;
            ret += dist;
        }
        if (k == 0) return;
        if (s[i] + dist < s[i + 1] - dist) {
            k--;
            ret += dist;
        }
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> k;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        s.push_back(x);
    }
    s.push_back(-INF);    
    s.push_back(INF);
    sort(ALL(s));
    for (int i = 2; i <= n; i++) {
        ::max = std::max(::max, s[i] - s[i - 1]);
    }
    int dist = 1;
    while (k > 0) {
        f(dist);
        dist++;
    }
    cout << ret << "\n";
}
