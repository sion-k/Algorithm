#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) \
    cout << "["; \
    for (int __i = 0; __i < SIZE(s); __i++) { \
        cout << s[__i]; \
        if (__i != SIZE(s) - 1) cout << ", "; \
    } \
    cout << "]\n";

using namespace std;

map<string, int> DEG;

double f(string s) {
    if (DEG.count(s)) {
        return DEG[s];
    }

    string suffix = s.substr(s.length() - 2, 2);
    double ret = DEG[suffix];
    double inc = 22.5;

    for (int i = s.length() - 3; i >= 0; i--, inc /= 2) {
        bool clockwise = false;

        if (suffix == "NE" && s[i] == 'E') {
            clockwise = true;
        } else if (suffix == "SE" && s[i] == 'S') {
            clockwise = true;
        } else if (suffix == "SW" && s[i] == 'W') {
            clockwise = true;
        } else if (suffix == "NW" && s[i] == 'N') {
            clockwise = true;
        }

        if (clockwise) {
            ret += inc;
        } else {
            ret -= inc;
        }
    }

    return ret;
}

int main() {
    FAST();
    cout << fixed;
    cout.precision(9);

    DEG["N"] = 0;
    DEG["E"] = 90;
    DEG["S"] = 180;
    DEG["W"] = 270;
    DEG["NE"] = 45;
    DEG["SE"] = 135;
    DEG["SW"] = 225;
    DEG["NW"] = 315;

    string x, y;
    cin >> x >> y;

    double a = f(x), b = f(y);
    if (a < b) swap(a, b);

    cout << min(a - b, b + 360 - a) << "\n";
}
