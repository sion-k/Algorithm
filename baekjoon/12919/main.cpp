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

string s;

bool btk(string& t) {
    if (s.length() == t.length()) {
        return s == t;
    }

    bool flag = false;

    if (t.front() == 'A' && t.back() == 'A' || t.front() == 'B' && t.back() == 'A') {
        char temp = t.back();
        t.pop_back();

        flag |= btk(t);

        t.push_back(temp);
    }

    if (t.front() == 'A' && t.back() == 'B') {
        return false;
    }

    if (t.front() == 'B' && t.back() == 'B' || t.front() == 'B' && t.back() == 'A') {
        reverse(ALL(t));
        char temp = t.back();
        t.pop_back();

        flag |= btk(t);

        t.push_back(temp);
        reverse(ALL(t));
    }

    return flag;
}

int main() {
    FAST();

    string t;
    cin >> s >> t;

    cout << btk(t) << "\n";
}
