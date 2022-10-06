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
    int n = SIZE(s);

    vector<int> p(n);
    for (int i = 1; i < n; i++) {
        if (s[i - 1] == '(' && s[i] == '(') {
            p[i]++;
        }
        p[i] += p[i - 1];
    }

    int sum = 0;
    for (int i = 1; i < n; i++) {
        if (s[i - 1] == ')' && s[i] == ')') {
            sum += p[i];
        }
    }
    cout << sum << "\n";
}
