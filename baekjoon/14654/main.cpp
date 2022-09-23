#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
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

bool fight(pair<int, int> u, pair<int, int> v) {
    if (u.first == v.first) return u.second < v.second;
    switch (u.first) {
    case 1:
        return v.first == 3;
    case 2:
        return v.first == 1;
    case 3:
        return v.first == 2;
    }
    return 0;
}

int main() {
    FAST();
    int n;
    cin >> n;
    vector<int> a(n), b(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    for (int i = 0; i < n; i++) {
        cin >> b[i];
    }
    int max = 0;
    pair<int, int> cnt;
    for (int i = 0; i < n; i++) {
        if (fight({a[i], cnt.first}, {b[i], cnt.second})) {
            cnt.first++;
            cnt.second = 0;
        } else {
            cnt.first = 0;
            cnt.second++;
        }
        max = ::max(max, cnt.first);
        max = ::max(max, cnt.second);
    }
    cout << max << "\n";
}
