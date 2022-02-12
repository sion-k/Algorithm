#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int s[367];

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        int u, v;
        cin >> u >> v;
        s[u]++;
        s[v + 1]--;
    }
    for (int i = 1; i <= 366; i++) {
        s[i] += s[i - 1];
    }
    int ret = 0;
    int start = 0, height = 0;
    for (int i = 1; i <= 366; i++) {
        if (s[i]) {
            if (start == 0) start = i;
            height = max(height, s[i]);
        } else {
            ret += (i - start) * height;
            start = 0;
            height = 0;
        }
    }
    cout << ret << "\n";
}
