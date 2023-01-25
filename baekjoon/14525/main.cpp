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

const long long B = 1e9 + 7;
const long long A = uniform_int_distribution<long long>(0, B - 1)(rng);

int n, m;
vector<string> spotty, plain;
vector<vector<int>> spotty_hash, plain_hash;

vector<int> p;

vector<int> rolling_hash(string s) {
    vector<int> h;
    h.push_back(s[0]);

    for (int i = 1; i < m; i++) {
        h.push_back((h.back() * A + s[i]) % B);
    }

    return h;
}

int slice_hash(vector<int>& h, int start, int end) {
    long long ret = h[end];

    if (start - 1 >= 0) {
        ret = (ret - ((long long)h[start - 1] * p[end - start + 1]) % B + B) % B;
    }

    return ret;
}

bool f(int length) {
    for (int i = 0; i + length - 1 < m; i++) {
        int j = i + length - 1;

        set<int> s;

        for (int k = 0; k < n; k++) {
            int x = slice_hash(spotty_hash[k], i, j);
            s.insert(x);
        }

        bool flag = true;

        for (int k = 0; k < n; k++) {
            int x = slice_hash(plain_hash[k], i, j);
            if (s.count(x)) {
                flag = false;
            }
        }

        if (flag) {
            return true;
        }
    }

    return false;
}

int main() {
    FAST();

    cin >> n >> m;

    spotty.resize(n), plain.resize(n);
    spotty_hash.resize(n), plain_hash.resize(n);
    for (int i = 0; i < n; i++) {
        cin >> spotty[i];
        spotty_hash[i] = rolling_hash(spotty[i]);
    }

    for (int i = 0; i < n; i++) {
        cin >> plain[i];
        plain_hash[i] = rolling_hash(plain[i]);
    }

    p.push_back(1);
    for (int i = 1; i < m; i++) {
        p.push_back((p.back() * A) % B);
    }

    int lo = 0, hi = m;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;

        if (f(mid)) {
            hi = mid;
        } else {
            lo = mid;
        }
    }

    cout << hi << "\n";
}
