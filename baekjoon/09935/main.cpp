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

bool check(string& s, string& t) {
    if (SIZE(s) >= SIZE(t)) {
        for (int i = 0; i < SIZE(t); i++) {
            if (s[SIZE(s) - SIZE(t) + i] != t[i]) {
                return false;
            }
        }
        return true;
    }

    return false;
}

int main() {
    FAST();

    string s, t;
    cin >> s >> t;

    string st;

    for (int i = 0; i < SIZE(s); i++) {
        st.push_back(s[i]);

        if (check(st, t)) {
            for (int j = 0; j < SIZE(t); j++) {
                st.pop_back();
            }
        }
    }

    cout << (st.empty() ? "FRULA" : st) << "\n";
}
