# 벽장문의 이동
import sys
input = sys.stdin.readline

N = int(input())
order = []
_open = list(map(int, input().split()))

_order = input()
while _order!='':
    order.append(int(_order))
    _order = input()

def dp(o1, o2, n):
    global order
    if len(order)==n:
        return [0]
    if o1==order[n] or o2==order[n]:
        return [min(dp(o1, o2, n+1))]
    result1 = 1
    result2 = 1
    o2_1 = o2 + (1 if o2<order[n] and o1<o2 else (-1 if o2>order[n] and o1>o2 else 0))
    o1_2 = o1 + (1 if o1<order[n] and o2<o1 else (-1 if o1>order[n] and o2>o1 else 0))
    if o2_1!=o2:
        result1 += 1
    if o1_2!=o1:
        result2 += 1
    return [result1+min(dp(order[n], o2_1, n+1)), result2+min(dp(o1_2, order[n], n+1))]
print(min(dp(_open[0], _open[1], 0)))