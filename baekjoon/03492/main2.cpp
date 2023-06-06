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

int n;
string s;
rabin_karp h("");

// s[u:u + t] != s[v:v + t]를 만족하는 가장 작은 t반환
int upper_bound(int u, int v) {
    int lo = -1, hi = n;

    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (h.sub_hash(u, u + mid) == h.sub_hash(v, v + mid)) {
            lo = mid;
        } else {
            hi = mid;
        }
    }

    return hi;
}

// s[u:u + n]보다 s[v:v + n]이 사전순으로 작다면 true
bool compare(int u, int v) {
    int t = upper_bound(u, v);
    return t != n && s[u + t] > s[v + t];
}

int solve() {
    cin >> s;

    n = SIZE(s);
    s = s + s;
    h = rabin_karp(s);

    int min = 0;
    for (int cand = 0; cand < n; cand++) {
        if (compare(min, cand)) {
            min = cand;
        }
    }

    return min + 1;
}

int main() {
    FAST();

    int tc;
    cin >> tc;

    while (tc--) {
        cout << solve() << "\n";
    }
}
