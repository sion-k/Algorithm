#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

bool compare(pair<int, int> u, pair<int, int> v) {
    if (u.first == v.first) {
        return u.second < v.second;
    } else {
        return u.first > v.first;
    }
}

int main() {
    FAST();

    int n;
    cin >> n;

    vector<pair<int, int>> p(n);
    for (int i = 0; i < n; i++) {
        cin >> p[i].first >> p[i].second;
    }

    sort(ALL(p), compare);

    int cnt = 0;
    for (int i = 5; i < n; i++) {
        if (p[i].first == p[4].first) {
            cnt++;
        }
    }
    cout << cnt << "\n";
}
