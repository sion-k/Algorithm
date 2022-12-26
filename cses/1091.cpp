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

int main() {
    FAST();

    int n, m;
    cin >> n >> m;

    multiset<int> s;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;

        s.insert(x);
    }

    for (int i = 0; i < m; i++) {
        int x;
        cin >> x;

        auto it = s.upper_bound(x);

        if (it == s.begin() || s.empty()) {
            cout << -1;
        } else {
            it--;
            cout << *it;
            s.erase(it);
        }

        cout << "\n";
    }
}
