import sys
from collections import deque
dq = deque()
N = int(sys.stdin.readline())
for i in range(N) :
    com = sys.stdin.readline().split()
    if com[0] == "push_front" :
        dq.appendleft(com[1])
        continue
    if com[0] == "push_back"  :
        dq.append(com[1])
        continue
    if com[0] == "pop_front"  : sys.stdout.write(str(dq.popleft()) if dq else "-1")
    if com[0] == "pop_back"   : sys.stdout.write(str(dq.pop()) if dq else "-1")
    if com[0] == "size"       : sys.stdout.write(str(len(dq)))
    if com[0] == "empty"      : sys.stdout.write("0" if dq else "1")
    if com[0] == "front"      : sys.stdout.write(str(dq[0]) if dq else "-1")
    if com[0] == "back"       : sys.stdout.write(str(dq[-1]) if dq else "-1")
    sys.stdout.write("\n")
