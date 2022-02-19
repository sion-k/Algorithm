#include <bits/stdc++.h>
#define deb(x) cout << #x << " : " << x << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << x << ", " << y << ")\n"
#define deb_tuple(x, y, z)                                                     \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << x << ", " << y \
         << ", " << z << ")\n"
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
using namespace std;

int n;
int a[100000];

int main() {
    cin.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n;
    int dist = 0, save = 0;
    int index = n, dept = 0;
    for (int i = 0; i < n; i++) {
        cin >> a[i];
        if (a[i] > 0) {
            save += a[i];
        } else {
            dept -= a[i];
            index = min(index, i);
        }
        if (dept && save >= dept) {
            dist += 2 * (i - index);
            index = n;
            save -= dept;
            dept = 0;
        }
        dist++;
    }
    if (dept) {
       dist += 2 * (n - 1 - index); 
    }
    cout << dist << "\n";
}
