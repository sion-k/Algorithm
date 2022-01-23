#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
int s[10000];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }
    int u = -1;
    for (int i = 0; i < n - 1; i++) {
        if (s[i] > s[i + 1]) {
            u = i;
        }
    }
    if (u == -1) {
        cout << -1;
        return 0;
    } else {
        int v = -1;
        for (int i = u + 1; i < n; i++) {
            if (s[u] > s[i]) {
                v = i;
            }
        }
        swap(s[u], s[v]);
        reverse(s + u + 1, s + n);
        for (int i = 0; i < n; i++ ) {
            cout << s[i] << " ";
        }
    }
    cout << "\n";
}
