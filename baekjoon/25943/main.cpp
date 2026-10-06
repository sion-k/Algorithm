#include <iostream>

using namespace std;

int main() {

    int n;
    cin >> n;

    int a[n];
    for (int i = 0; i < n; i++) {
        cin >> a[i];
    }

    int left = 0;
    int right = 0;

    left += a[0];
    right += a[1];

    for (int i = 2; i < n; i++) {
        if (left == right) {
            left += a[i];
        } else {
            if (left < right) {
                left += a[i];
            } else {
                right += a[i];
            }
        }
    }

    int diff = abs(left - right);

    int w[7] = { 100, 50, 20, 10, 5, 2, 1 };
    int cnt = 0;

    for (int i : w) {
        cnt += diff / i;
        diff = diff % i;
    }

    cout << cnt << endl;
}
