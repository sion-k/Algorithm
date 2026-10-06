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

bool f;
vector<vector<vector<vector<vector<int>>>>> d;

bool btk(int a, int b, int c, int p1, int p2, string& r) {
    if (f) return true;
    if (a + b + c == 0) {
        cout << r << '\n';
        f = true;
        return true;
    }

    if (d[p1][p2][a][b][c] != -1) return d[p1][p2][a][b][c];

    bool ret = false;

    if (a) {
        r.push_back('A');
        ret |= btk(a - 1, b, c, p2, 0, r);
        r.pop_back();
    }

    if (b && p2 != 1) {
        r.push_back('B');
        ret |= btk(a, b - 1, c, p2, 1, r);
        r.pop_back();
    }

    if (c && p1 != 2 && p2 != 2) {
        r.push_back('C');
        btk(a, b, c - 1, p2, 2, r);
        r.pop_back();
    }

    return d[p1][p2][a][b][c] = ret;
}

int main() {
    FAST();

    string s;
    cin >> s;

    if (SIZE(s) == 1) {
        cout << s << '\n';
        return 0;
    }

    map<int, int> c;
    for (int i = 0; i < SIZE(s); i++) {
        c[s[i]]++;
    }

    d.resize(3,
        vector<vector<vector<vector<int>>>>(3,
            vector<vector<vector<int>>>(c['A'] + 1,
                vector<vector<int>>(c['B'] + 1,
                    vector<int>(c['C'] + 1, -1)
                )
            )
        )
    );

    string r;
    for (int i = 'A'; i <= 'C'; i++) {
        if (c[i] == 0) continue;
        c[i]--;
        for (int j = 'A'; j <= 'C'; j++) {
            if (c[j] == 0) continue;
            if (i == j && (i == 'B' || i == 'C')) {
                continue;
            }
            c[j]--;

            r.push_back(i);
            r.push_back(j);

            btk(c['A'], c['B'], c['C'], i - 'A', j - 'A', r);

            if (f) {
                return 0;
            }

            r.pop_back();
            r.pop_back();

            c[j]++;
        }
        c[i]++;
    }

    cout << -1 << '\n';
}
