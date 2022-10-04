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
    vector<vector<int>> a;
    for (int i = 0; i < n; i++) {
        int l;
        cin >> l;
        a.push_back(vector<int>(l));
        for (int j = 0; j < l; j++) {
            cin >> a[i][j];
        }
    }
    for (int i = 0; i < q; i++) {
        int s, t;
        cin >> s >> t;
        cout << a[s - 1][t - 1] << "\n";
    }
}
