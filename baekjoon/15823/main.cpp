#include <bits/stdc++.h>
using namespace std;

int n, m;
vector<int> s;

// 한 팩당 k개씩 담아서
// m개 이상의 카드팩을 구매 가능한지 여부
bool f(int k) {
    int cnt = 0;
    set<int> set;
    // [head, tail)
    int head = 0; int tail = 0;
    while (head < n) {
        while (tail < n && set.size() < k && set.find(s[tail]) == set.end()) {
            set.insert(s[tail]);
            tail++;
        }
        if (set.size() == k) {
            cnt++;
            head = tail;
            set.clear();
        } else {
            set.erase(s[head]);
            head++;
        }
    }
    return cnt >= m;
}

int main() {
    cin.tie(0);
    cout.tie(0);
    ios_base::sync_with_stdio(0);
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        int x;
        cin >> x;
        s.push_back(x);
    }
    int lo = 1; int hi = n + 1;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (f(mid)) lo = mid;
        else hi = mid;
    }
    cout << lo << "\n";
}
