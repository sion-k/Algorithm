#include <bits/stdc++.h>
using namespace std;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int n;
    cin >> n;
    vector<int> s(n);
    for (int i = 0; i < n; i++) cin >> s[i];
    sort(s.begin(), s.end());
    int ret = 0;
    int head = 0; int tail = n - 1;
    while (head < tail) {
        ret += 2 * s[tail];
        head++; tail--;
    }
    if (head == tail) ret += s[tail];
    cout << ret << "\n";
}
