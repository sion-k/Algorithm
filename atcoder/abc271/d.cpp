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

int n, s;
vector<int> a, b;

vector<vector<int>> cache;
vector<char> choice;

int dp(int here, int sum) {
    if (here == n) return sum == s;
    if (cache[here][sum] != -1) return cache[here][sum];
    bool flag = false;
    if (sum + a[here] <= s && dp(here + 1, sum + a[here])) {
        flag = true;
    }
    if (sum + b[here] <= s && dp(here + 1, sum + b[here])) {
        flag = true;
    }
    return cache[here][sum] = flag;
}

void construct(int here, int sum) {
    if (here == n) return;
    if (dp(here + 1, sum + a[here])) {
        cout << 'H';
        construct(here + 1, sum + a[here]);
    } else {
        cout << 'T';
        construct(here + 1, sum + b[here]);
    }
}

int main() {
    FAST();
    cin >> n >> s;

    a = vector<int>(n), b = vector<int>(n);
    for (int i = 0; i < n; i++) {
        cin >> a[i] >> b[i];
    }

    cache = vector<vector<int>>(n, vector<int>(s + 1, -1));
    choice = vector<char>(n);
    if (dp(0, 0)) {
        cout << "Yes" << "\n";
        construct(0, 0);
        cout << "\n";
    } else {
        cout << "No" << "\n";
    }

}
