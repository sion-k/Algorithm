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

    int n;
    cin >> n;

    vector<vector<int>> adj('z' + 1, vector<int>('z' + 1));
    for (int i = 'a'; i <= 'z'; i++) {
        adj[i][i] = true;
    }

    for (int i = 0; i < n; i++) {
        string x, is, y;
        cin >> x >> is >> y;

        adj[x[0]][y[0]] = true;
    }

    for (int k = 'a'; k <= 'z'; k++) {
        for (int i = 'a'; i <= 'z'; i++) {
            for (int j = 'a'; j <= 'z'; j++) {
                adj[i][j] = adj[i][j] || adj[i][k] && adj[k][j];
            }
        }
    }

    int m;
    cin >> m;

    for (int i = 0; i < m; i++) {
        string x, is, y;
        cin >> x >> is >> y;

        cout << (adj[x[0]][y[0]] ? "T" : "F") << "\n";
    }
}
