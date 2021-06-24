import sys
input = sys.stdin.readline
import heapq
heap = []
N = int(input())
for i in range(N) : heapq.heappush(heap, int(input()))
ret = 0
while len(heap) > 1 :
    p = heapq.heappop(heap)
    q = heapq.heappop(heap)
    ret += (p + q)
    heapq.heappush(heap, p + q)
print(ret)
