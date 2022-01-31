#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n, s;
int a[40];
map<int, int> cnt;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> s;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }
    if (n == 1) {
        cout << (a[0] == s) << "\n";
        return 0;
    }
    for (int i = 0; i < (1 << (n / 2)); i++) {
        int sum = 0;
        for (int j = 0; j < n / 2; j++) {
            if (i & (1 << j)) {
                sum += a[j];
            }
        }
        map<int, int>::iterator found = cnt.find(sum);
        if (found != cnt.end()) {
            found->second++;
        } else {
            cnt.insert({sum, 1});
        }
    }
    long long ret = 0;
    for (int i = 0; i < (1 << (n - n / 2)); i++) {
        int sum = 0;
        for (int j = 0; j < n - n / 2; j++) {
            if (i & (1 << j)) {
                sum += a[n / 2 + j];
            }
        }
        map<int, int>::iterator found = cnt.find(s - sum);
        if (found != cnt.end()) {
            ret += found->second;
        }
    }
    if (s == 0) ret--;
    cout << ret << "\n";
}
