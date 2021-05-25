import sys
input = sys.stdin.readline
N = int(input())
S = list(map(int, input().rstrip().split()))
B, C = map(int, input().split())
sum = len(S)
for x in S:
    x = max(0, x - B)
    sum += (x - 1) // C + 1
print(sum)
