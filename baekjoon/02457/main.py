num_days = [0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

n = int(input())
a = []

for i in range(n):
    s1, e1, s2, e2 = map(int, input().split())

    s = sum(num_days[:s1]) + e1
    e = sum(num_days[:s2]) + e2

    if s <= sum(num_days[:11]) + 30 and e > sum(num_days[:3]) + 1:
        a.append((s, e))

a.append((sum(num_days[:12]) + 1, 366))
a.sort()

b = [False] * 366

last = sum(num_days[:3]) + 1
cand = 0
cnt = 0

for s, e in a:
    if s <= last:
        cand = max(cand, e)
    elif s <= cand:
        for i in range(last, cand):
            b[i] = True
        last = cand
        cand = e
        cnt += 1

print(cnt if all(b[sum(num_days[:3]) + 1:sum(num_days[:12]) + 1]) else 0)
