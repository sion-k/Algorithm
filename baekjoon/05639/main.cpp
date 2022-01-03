#include <iostream>

int s[10001];

// s[left, right]에서 key보다 큰 최초의 지점 반환
int binsearch(int left, int right, int key) {
    int lo = left; int hi = right;
    if (s[lo] > key) return left;
    if (s[hi] < key) return right + 1;
    while (lo + 1 < hi) {
        int mid = (lo + hi) / 2;
        if (s[mid] > key) hi = mid;
        else lo = mid;
    }
    return hi;
}

void post(int left, int right) {
    if (left != right) {
        int mid = binsearch(left + 1, right, s[left]);
        if (mid != left + 1) post(left + 1, mid - 1);
        if (mid != right + 1) post(mid, right);
    }
    printf("%d\n", s[left]);
}

int main() {
    int i = 0;
    int x;
    while (scanf("%d", &x) != EOF) {
        s[i++] = x;
    }
    post(0, i - 1);
}