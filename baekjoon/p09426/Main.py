import sys
input = sys.stdin.readline
class FenwickTree:
    def __init__(self):
        self.tree = [0] * 65538

    def add(self, pos, val):
        pos += 1
        while pos < len(self.tree):
            self.tree[pos] += val
            pos += (pos & -pos)

    def sum(self, pos):
        pos += 1
        ret = 0
        while pos > 0:
            ret += self.tree[pos]
            pos -= (pos & -pos)
        return ret

    def median(self):
        # 자기 자신보다 작은 수의 개수가 (K + 1) / 2가 되는 hi반환
        lo = -1; hi = 65536
        while lo + 1 < hi:
            mid = (lo + hi) // 2
            if self.sum(mid) >= (K + 1) // 2 : hi = mid
            else : lo = mid
        return hi

N, K = map(int, input().split())
S = [int(input()) for i in range(N)]
t = FenwickTree()
ret = 0
for i in range(N):
    t.add(S[i], 1)
    if K <= i + 1:
        ret += t.median()
        t.add(S[i - K + 1], -1)
print(ret)
