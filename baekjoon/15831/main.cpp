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

    int n, b, w;
    cin >> n >> b >> w;

    string s;
    cin >> s;

    int head = 0, tail = 0;
    pair<int, int> c;
    int max = 0;

    while (head < n) {
        while (tail < n && c.first + (s[tail] == 'B') <= b) {
            c.first += s[tail] == 'B';
            c.second += s[tail] == 'W';
            tail++;
        }

        if (c.second >= w) {
            max = ::max(max, tail - head);
        }

        c.first -= s[head] == 'B';
        c.second -= s[head] == 'W';

        head++;
    }

    cout << max << "\n";
}
