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

vector<vector<char>> a;

char flip(char x) {
    return x == 'H' ? 'T' : 'H';
}

const int INF = 9;
int btk(int k) {
    if (k == 8) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] != a[0][0]) {
                    return INF;
                }
            }
        }
        return 0;
    }

    int min = btk(k + 1);

    if (k <= 2) {
        for (int j = 0; j < 3; j++) {
            a[k][j] = flip(a[k][j]);
        }
        min = ::min(min, 1 + btk(k + 1));
        for (int j = 0; j < 3; j++) {
            a[k][j] = flip(a[k][j]);
        }
    } else if (k <= 5) {
        for (int i = 0; i < 3; i++) {
            a[i][k - 3] = flip(a[i][k - 3]);
        }
        min = ::min(min, 1 + btk(k + 1));
        for (int i = 0; i < 3; i++) {
            a[i][k - 3] = flip(a[i][k - 3]);
        }
    } else if (k == 6) {
        for (int i = 0; i < 3; i++) {
            a[i][i] = flip(a[i][i]);
        }
        min = ::min(min, 1 + btk(k + 1));
        for (int i = 0; i < 3; i++) {
            a[i][i] = flip(a[i][i]);
        }
    } else {
        for (int i = 0; i < 3; i++) {
            a[i][2 - i] = flip(a[i][2 - i]);
        }
        min = ::min(min, 1 + btk(k + 1));
        for (int i = 0; i < 3; i++) {
            a[i][2 - i] = flip(a[i][2 - i]);
        }
    }
    return min;
}

void solve() {
    a = vector<vector<char>>(3, vector<char>(3));

    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> a[i][j];
        }
    }

    int min = btk(0);
    cout << (min == INF ? -1 : min) << "\n";
}

int main() {
    FAST();

    int tc;
    cin >> tc;
    while (tc--) {
        solve();
    }
}
