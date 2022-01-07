#include <bits/stdc++.h>
using namespace std;
const int INF = 987654321;

int n;
vector<int> s;

int cache[10000];

int dp(int k) {
    if (k == 0) return 0;
    if (cache[k]) return cache[k];
    int ret = INF;
    for (int i = 0; i < n; i++) {
        if (k - s[i] >= 0) {
            ret = min(ret, 1 + dp(k - s[i]));
        }
    }
    return cache[k] = ret;
}

int main() {
    int k;
    cin >> k >> n;
    vector<int> t;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        t.push_back(x);
    }
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (t[i] + t[j] <= k) {
                t.push_back(t[i] + t[j]);
            }
        }
    }
    set<int> temp;
    for (auto& x : t) {
        temp.insert(x);
    }
    for (auto& x : temp) {
        s.push_back(x);
    }
    n = s.size();
    int ret = dp(k);
    if (ret == INF) ret = -1;
    cout << ret << endl;
}
