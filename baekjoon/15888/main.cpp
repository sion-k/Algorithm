#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define FAST() cin.tie(0)->sync_with_stdio(0)
#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y)                                                         \
    cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z)                                                   \
    cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", "    \
         << (y) << ", " << (z) << ")\n"
#define deb_tuple(s)                                                           \
    for (int i = 0; i < SIZE(s); i++)                                          \
        cout << s[i] << " \n"[i == SIZE(s) - 1];
using namespace std;

int f(int a, int b, int c, int x) {
    return a * x * x + b * x + c;
}

int g(int x) {
    if (x <= 1) return false;
    return x == (x & -x);
}

int main() {
    FAST();
    int a, b, c;
    cin >> a >> b >> c;
    for (int i = -100; i <= 100; i++) {
        for (int j = -100; j <= 100; j++) {
            if (f(a, b, c, i) == 0 && f(a, b, c, j) == 0) {
                if (g(i) && g(j) && i != j) {
                    cout << "이수근" << "\n";
                    return 0;
                } else if (i != j) {
                    cout << "정수근" << "\n";
                    return 0;
                }
            }
        }
    }
    cout << "둘다틀렸근" << "\n";
}
