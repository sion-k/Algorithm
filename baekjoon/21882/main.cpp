#include <iostream>
#include <cstring>
#include <vector>
#include <queue>
using namespace std;

int a[100001];
vector<vector<int>> children(100001);
long long cache1[100001];
long long cache2[100001];

long long f(int here);
long long g(int here);

long long f(int here) {
    if (cache1[here] != -1) return cache1[here];
    long long ret = g(here);
    for (auto there : children[here]) {
        ret = max(ret, f(there));
    }
    return cache1[here] = ret;
}

long long g(int here) {
    if (a[here]) return a[here];
    if (cache2[here] != -1) return cache2[here];
    long long ret = 0;
    priority_queue<long long> pq;
    for (auto there : children[here]) {
        pq.push(f(there));
    }
    if (pq.size() >= 2) {
        long long a = pq.top(); pq.pop();
        long long b = pq.top(); pq.pop();
        if (a != 0 && b != 0) {
            ret = a + b;
        }
    }
    return cache2[here] = ret;
}

int main() {
    memset(cache1, -1, sizeof(cache1));
    memset(cache2, -1, sizeof(cache2));
    int n;
    cin >> n;
    for (int here = 2; here <= n; here++) {
        int there;
        cin >> there;
        children[there].push_back(here);
    }
    for (int i = 1; i <= n; i++) {
        cin >> a[i];
    }
    long long sum = 0;
    for (int here = 1; here <= n; here++) {
        sum += g(here);
    }
    cout << sum;
}
