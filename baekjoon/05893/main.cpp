#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

#define deb(x) cout << #x << " : " << (x) << "\n"
#define deb_pair(x, y) cout << "(" << #x << ", " << #y << ") : (" << (x) << ", " << (y) << ")\n"
#define deb_triplet(x, y, z) cout << "(" << #x << ", " << #y << ", " << #z << ") : (" << (x) << ", " << (y) << ", " << (z) << ")\n"
#define deb_tuple(s) for (int i = 0; i < SIZE(s); i++) cout << s[i] << " \n"[i == SIZE(s) - 1];

using namespace std;

int main() {
    FAST();

    string s;
    cin >> s;

    string t = s + "0000";
    s = "0000" + s;

    int n = SIZE(s);
    bool carry = false;

    string ret;

    for (int i = n - 1; i >= 0; i--) {
        int sum = s[i] - '0' + t[i] - '0' + carry;

        ret.push_back((sum % 2) + '0');

        if (sum >= 2) {
            carry = true;
        } else {
            carry = false;
        }
    }

    if (carry) ret.push_back('1');

    reverse(ALL(ret));

    cout << ret << "\n";
}
