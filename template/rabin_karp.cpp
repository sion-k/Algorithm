#include <bits/stdc++.h>

#define FAST() cin.tie(0)->sync_with_stdio(0)
#define OPEN(t) freopen("data.txt", (t), (t == "r" ? stdin : stdout))
#define ALL(x) (x).begin(), (x).end()
#define SIZE(x) (int)((x).size())

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
}
