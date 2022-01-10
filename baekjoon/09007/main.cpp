#include <bits/stdc++.h>
using namespace std;

int k, n;
int s[4][1000];

const int INF = 987654321;

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    int tc;
    cin >> tc;
    while (tc--) {
        cin >> k >> n;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                cin >> s[i][j];
            }
        }
        vector<int> a;
        vector<int> b;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a.push_back(s[0][i] + s[1][j]);
                b.push_back(s[2][i] + s[3][j]);
            }
        }
        sort(a.begin(), a.end());
        sort(b.begin(), b.end());
        int ret = INF;
        int head = 0; int tail = b.size() - 1;
        while (head < (int)a.size() && tail >= 0) {
            int sum = a[head] + b[tail];
            ret = min(ret, abs(sum - k));
            if (sum < k) {
                head++;
            } else {
                tail--;
            }
        }
        // k - ret이 가능한지 확인
        bool flag = false;
        head = 0; tail = b.size() - 1;
        while (head < (int)a.size() && tail >= 0) {
            int sum = a[head] + b[tail];
            if (sum < k - ret) {
                head++;
            } else if (sum > k - ret) {
                tail--;
            } else {
                flag = true;
                break;
            }
        }
        cout << (flag ? k - ret : k + ret) << "\n";
    }
}
