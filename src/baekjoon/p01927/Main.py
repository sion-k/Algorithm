import sys
from queue import PriorityQueue
input = sys.stdin.readline
print = sys.stdout.write
pq = PriorityQueue()
N = int(input())
for i in range(N):
    x = int(input())
    if x : pq.put(x)
    else :
        print(str(0 if pq.empty() else pq.get()))
        print("\n")
