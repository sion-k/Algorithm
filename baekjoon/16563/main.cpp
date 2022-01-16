#include <bits/stdc++.h>
using namespace std;

int n;
int fpr[5000001];

void f(int x) {
    while (x != 1) {
        cout << fpr[x] << " ";
        x /= fpr[x];
    }
    cout << "\n";
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 2; i <= 5000000; i++) {
        if (!fpr[i]) {
            for (int j = i; j <= 5000000; j += i) {
                if (!fpr[j]) fpr[j] = i;
            }
        }
    }
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        f(x);
    }
}
