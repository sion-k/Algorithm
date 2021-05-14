import sys, heapq
input = sys.stdin.readline
print = sys.stdout.write
N = int(input())
h = []
for i in range(N):
    x = int(input())
    if x : heapq.heappush(h, (abs(x), x))
    else : print(str(heapq.heappop(h)[1] if h else 0) + "\n")
