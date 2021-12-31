N = int(input())
S = input().split()
R = list(map(int, input().split()))
# 최대 최소
ret1 = -1000000000
ret2 = 1000000000
operator = [ "+", "-", "*", "//" ]

def btk(here, exp, opr):
    global ret1, ret2
    exp.append(S[here])
    if here == N - 1:
        val = eval("".join(exp))
        ret1 = max(ret1, val)
        ret2 = min(ret2, val)
    else:
        for i in range(4):
            if opr[i]:
                opr[i] -= 1
                exp.append(operator[i])
                btk(here + 1, exp, opr)
                opr[i] += 1
                exp.pop()
    exp.pop()

btk(0, [], R)
print(ret1)
print(ret2)