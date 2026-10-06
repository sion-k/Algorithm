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

int n, m;
vector<string> a;

int btk(int i, int j, vector<vector<int>> b) {
    if (i == n) {
        return 0;
    }
    if (j == m) {
        return btk(i + 1, 0, b);
    }
    if (b[i][j]) {
        return btk(i, j + 1, b);
    }

    int max = 0;

    vector<vector<int>> nb = b;
    string v;
    for (int k = i; k <= n - 1; k++) {
        v.push_back(a[k][j]);
        nb[k][j] = true;
        max = ::max(max, stoi(v) + btk(i, j + 1, nb));
    }

    nb = b;
    string h;
    for (int k = j; k <= m - 1; k++) {
        if (!b[i][k]) {
            h.push_back(a[i][k]);
            nb[i][k] = true;
            max = ::max(max, stoi(h) + btk(i, k + 1, nb));
        } else {
            break;
        }
    }

    return max;
}

int main() {
    FAST();

    cin >> n >> m;
    a.resize(n);

    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    vector<vector<int>> b(n, vector<int>(m));
    cout << btk(0, 0, b) << '\n';
}
