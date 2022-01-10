#include <bits/stdc++.h>
using namespace std;

int n, m;
int s[100000];
int t[100000];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        cin >> s[i];
    }
    for (int i = 0; i < m; i++) {
        int a, b, k;
        cin >> a >> b >> k;
        a--; b--;
        t[a] += k;
        if (b + 1 < n) t[b + 1] -= k;
    }
    int add = 0;
    for (int i = 0; i < n; i++) {
        add += t[i];
        s[i] += add;
        cout << s[i] << " ";
    }
    cout << "\n";
}
