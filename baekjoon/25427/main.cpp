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
    int n;
    string s;
    cin >> n >> s;

    // dp[D | K | S | H] = 주어진 알파벳까지 DKSH를 만드는 경우의 수
    vector<long long> dp('Z' + 1);
    for (int i = 0; i < n; i++) {
        switch (s[i]) {
        case 'D':
            dp[s[i]]++;
            break;
        case 'K':
            dp[s[i]] += dp['D'];
            break;
        case 'S':
            dp[s[i]] += dp['K'];
            break;
        case 'H':
            dp[s[i]] += dp['S'];
            break;
        }
    }
    cout << dp['H'] << "\n";
}
