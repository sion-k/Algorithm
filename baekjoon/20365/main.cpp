#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int n;
    cin >> n;
    char s[500001];
    cin >> s;
    int b = 0; int r = 0;
    for (int i = 0; i < n - 1; i++) {
        if (s[i] == 'B' && s[i + 1] == 'R') {
            b++;
        }
        if (s[i] == 'R' && s[i + 1] == 'B') {
            r++;
        }
    }
    if (s[n - 1] == 'R') r++;
    if (s[n - 1] == 'B') b++;
    cout << min(1 + r, 1 + b) << "\n";
}
