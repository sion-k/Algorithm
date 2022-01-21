#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

int n;
char s[21];
char h[20];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    for (int i = 0; i < 2 * n - 1; i++) {
        cin >> s;
        for (int j = 0; s[j] != '\0'; j++) {
            h[j] ^= s[j];
        }
    }
    cout << h << "\n";
}
