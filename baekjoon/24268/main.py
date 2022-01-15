from itertools import permutations
s = []
n, d = map(int, input().split())
for p in permutations(range(d), d):
    if p[0] != 0:
        s.append(int("".join(map(str, p))))
s = sorted(s)
ret = -1
for x in s:
    if int(str(x), d) > n:
        ret = x
        break
print(int(str(ret), d))
