#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

#define pii pair<int, int>

using namespace std;

pii s, e;

int dist(pii u, pii v) {
    return abs(u.first - v.first) + abs(u.second - v.second);
}

long long f(vector<pair<pii, pii>>& a) {
    pii here = s;
    long long sum = 0;

    long long min = dist(s, e);

    for (auto& [u, v] : a) {
        // 포탈을 이용하는 경우
        sum += dist(here, u);
        here = u;

        sum += 10;
        here = v;

        // 더 이상 이용하지 않고 도착지로 이동하는 경우
        min = ::min(min, sum + (long long)dist(here, e));
    }

    return min;
}

int main() {
    FAST();

    cin >> s.first >> s.second >> e.first >> e.second;

    vector<pair<pii, pii>> a;

    for (int i = 0; i < 3; i++) {
        pii u, v;
        cin >> u.first >> u.second >> v.first >> v.second;

        a.emplace_back(u, v);
        a.emplace_back(v, u);
    }

    sort(ALL(a));

    long long min = f(a);

    while (next_permutation(ALL(a))) {
        min = ::min(min, f(a));
    }

    cout << min << "\n";
}
