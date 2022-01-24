#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

map<string, int> m;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    
    int cnt = 0;
    cout << fixed;
    cout.precision(4);
    string x;
    while (getline(cin, x)) {
        if (m.find(x) != m.end()) {
            m.find(x)->second++;
        }
        else {
            m.insert({x, 1});
        }
        cnt++;
    }
    for (auto& x : m) {
        cout << x.first << " " << (double)(x.second) / cnt * 100.0 << "\n";
    }
}
