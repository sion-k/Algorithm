#include <bits/stdc++.h>
using namespace std;

int n, k;
int s[1001];
// i번째 노드의 부모 번호 (0이면 없는 것)
int p[1001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    while (true) {
        cin >> n >> k;
        if (n == 0) break;
        for (int i = 1; i <= n; i++) cin >> s[i];
        memset(p, 0, sizeof(p));
        int t = 0;
        int head = 1; int tail = 1;
        while (head <= n) {
            while (tail + 1 <= n && s[tail] + 1 == s[tail + 1]) {
                tail++;
            }
            for (int here = head; here <= tail; here++) if (t != 0) { 
                p[here] = t;
            }
            t++;
            head = tail = tail + 1;
        }
        for (int i = 1; i <= n; i++) {
            if (s[i] == k) {
                k = i; break;
            }
        }
        int cnt = 0;
        for (int here = 1; here <= n; here++) {
            if (p[here] != 0 && p[k] != 0 && p[here] != p[k] && p[p[here]] == p[p[k]]) {
                cnt++;
            }
        }
        cout << cnt << "\n";
    }
}
