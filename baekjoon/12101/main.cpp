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
vector<vector<int>> s;

void reconstruct(int sum, vector<int>& pick) {
    if (sum == 0) {
        s.push_back(pick);
        return;
    }
    for (int i = 1; i <= 3; i++) {
        if (sum - i >= 0) {
            pick.push_back(i);
            reconstruct(sum - i, pick);
            pick.pop_back();
        }
    }
}

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    int n, k;
    cin >> n >> k;
    vector<int> pick;
    reconstruct(n, pick);
    if (k > (int)(s.size())) {
        cout << -1 << "\n";
        return 0;
    }
    int range = (int)(s[k - 1].size());
    for (int i = 0; i < range; i++) {
        cout << s[k - 1][i];
        if (i != range - 1) {
            cout << "+";
        }
    }
    cout << "\n";
}
