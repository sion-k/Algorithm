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

int n;
vector<vector<vector<double>>> cache;

double dp(int a, int b, int c) {
    if (a + b + c == 0) return 0;
    if (cache[a][b][c]) return cache[a][b][c];
    double avg = 0;
    if (a >= 1) {
        avg += (double)a / n * (1 + dp(a - 1, b, c));
    }
    if (b >= 1) {
        avg += (double)b / n * (1 + dp(a + 1, b - 1, c));
    }
    if (c >= 1) {
        avg += (double)c / n * (1 + dp(a, b + 1, c - 1));
    }
    double y = (double)(n - (a + b + c)) / n;
    return cache[a][b][c] = (avg + y) / (1 - y);
}

int main() {
    FAST();
    cin >> n;
    vector<int> abc(4);

    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        abc[x]++;
    }

    int a = abc[1], b = abc[2], c = abc[3];
    cache = vector<vector<vector<double>>>(n + 1, vector<vector<double>>(n + 1, vector<double>(n + 1)));
    cout.precision(15);
    cout << dp(a, b, c) << "\n";
}
