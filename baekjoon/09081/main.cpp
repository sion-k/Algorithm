#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
string s;

void solve() {
    string temp(s);
    if (next_permutation(ALL(temp))) {
        cout << temp << "\n";
    } else {
        cout << s << "\n";
    }
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int TC;
    cin >> TC;
    while (TC--) {
        cin >> s;
        solve();
    }
}
