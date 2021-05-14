import sys, heapq
input = sys.stdin.readline
print = sys.stdout.write
N = int(input())
heap = []
for i in range(N):
    x = int(input())
    if x : heapq.heappush(heap, -x)
    else:
        print(str(-heapq.heappop(heap) if heap else 0))
        print("\n")
