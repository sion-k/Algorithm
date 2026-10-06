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

mt19937 rng(chrono::steady_clock::now().time_since_epoch().count());
const long long MOD = 1e9 + 7;
const long long P = uniform_int_distribution<long long>(0, MOD - 1)(rng);

class rabin_karp {
public:
    vector<long long> pow, hash;

    rabin_karp(string s) {
        int n = SIZE(s);

        pow.push_back(1);
        for (int i = 1; i <= n - 1; i++) {
            pow.push_back(pow.back() * P % MOD);
        }

        hash.push_back(s[0]);
        for (int i = 1; i <= n - 1; i++) {
            hash.push_back((hash[i - 1] * P + s[i]) % MOD);
        }
    }

    long long sub_hash(int i, int j) {
        long long ret = hash[j];

        if (i - 1 >= 0) {
            ret = (ret - (hash[i - 1] * pow[j - i + 1]) % MOD + MOD) % MOD;
        }

        return ret;
    }
};

int main() {
    FAST();

    string s;
    cin >> s;

    string ns;
    for (auto x : s) {
        if (!('0' <= x && x <= '9')) {
            ns.push_back(x);
        }
    }

    s = ns;

    string t;
    cin >> t;

    rabin_karp hs(s), ht(t);

    bool flag = false;
    for (int i = 0; i + SIZE(t) - 1 < SIZE(s); i++) {
        if (hs.sub_hash(i, i + SIZE(t) - 1) == ht.sub_hash(0, SIZE(t) - 1)) {
            flag = true;
        }
    }

    cout << flag << "\n";
}
