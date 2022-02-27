#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) \
         << ", " << (z) << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

int n;
vector<tuple<string, int, int>> query;

// 정답이 u라고 가정했을 때 쿼리 (v, c, w)가 타당한지 반환
bool g(string u, string v, int c, int w) {
    pair<int, int> cnt;
    for (int i = 0; i < 4; i++) {
        if (u[i] == v[i]) cnt.first++;
        else {
            bool flag = false;
            for (int j = 0; j < 4; j++) if (u[j] != v[j]) {
                if (u[j] == v[i]) {
                    flag = true;
                }
            }
            if (flag) {
                cnt.second++;
            }
        }
    }
    return cnt.first == c && cnt.second == w;
}

// 정답이 u라고 가정했을 때, 모든 쿼리가 타당한지 반환
bool f(string u) {
    for (auto& q : query) {
        if (!g(u, get<0>(q), get<1>(q), get<2>(q))) {
            return false;
        }
    }
    return true;
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        string x;
        int c, w;
        cin >> x >> c >> w;
        query.emplace_back(x, c, w);
    }
    for (int i = 1000; i <= 9999; i++) {
        if (f(to_string(i))) {
            cout << i << "\n";
            return 0;
        }
    }
    cout << "NONE" << "\n";
}
