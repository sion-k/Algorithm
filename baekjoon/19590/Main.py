import sys
input = sys.stdin.readline
N = int(input())
S = [int(input()) for i in range(N)]
m = max(S)
x = sum(S)
ret = 0
if len(S) == 1 : ret = m
elif m >= x - m : ret = m - x + m
else : ret = x % 2
print(ret)