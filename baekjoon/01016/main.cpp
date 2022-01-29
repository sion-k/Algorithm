#include <bits/stdc++.h>
#define ALL(x) (x).begin(), (x).end()
using namespace std;

long long s, e;
bool sieve[1000001];

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> s >> e;
    for (long long i = 2; i * i <= e; i++) {
        for (long long j = max(i * i, (s / (i * i)) * (i * i)); j <= e; j += (i * i)) {
            if (s <= j && j <= e) {
                sieve[j - s] = true;
            }
        }
    }
    int cnt = 0;
    for (int i = 0; i <= e - s; i++) {
        if (!sieve[i]) cnt++;
    }
    cout << cnt << "\n";
}
