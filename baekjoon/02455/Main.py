sum = 0
ret = 0
for i in range(4):
    dec, inc = map(int, input().split())
    sum -= dec
    sum = min(10000, sum + inc)
    ret = max(ret, sum)
print(ret)
