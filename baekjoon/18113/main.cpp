#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, k, m;
int s[1000000];

bool f(int p) {
    long long cnt = 0;
    for (int i = 0; i < n; i++) {
        cnt += s[i] / p;
        if (cnt >= m) return true;
    }
    return cnt >= m;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> k >> m;
    for (int i = 0; i < n; i++) {
        cin >> s[i];
        if (s[i] <= k) s[i] = 0;
        else if (s[i] < 2 * k) {
            s[i] -= k;
        } else {
            s[i] -= 2 * k;
        }
    }
    int lo = 1; int hi = 1e9;
    if (!f(lo)) {
        cout << -1 << "\n";
        return 0;
    }
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (f(mid)) lo = mid;
        else hi = mid;
    }
    cout << lo << "\n";
}
