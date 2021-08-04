import sys
input = sys.stdin.readline
N = int(input())
S = [int(input()) for i in range(N)]
m = max(S)
s = sum(S)
print(m - (s - m) if m > s - m else s % 2)
