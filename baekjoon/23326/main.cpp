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

    int n, q;
    cin >> n >> q;

    set<int> s;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;

        if (x) {
            s.insert(i);
        }
    }

    int p = 0;
    for (int i = 0; i < q; i++) {
        int op;
        cin >> op;

        if (op == 1) {
            int x;
            cin >> x;
            x--;

            auto it = s.find(x);

            if (it != s.end()) {
                s.erase(it);
            } else {
                s.insert(x);
            }
        } else if (op == 2) {
            int x;
            cin >> x;

            p = (p + x) % n;
        } else {
            auto it = s.lower_bound(p);

            if (it != s.end()) {
                cout << *it - p << "\n";
            } else if (s.begin() != s.end()) {
                cout << n - 1 - p + *s.begin() + 1 << "\n";
            } else {
                cout << -1 << "\n";
            }
        }
    }
}
