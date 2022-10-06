from re import T


def prev(s):
    ret = []
    if len(s) % 2 != 0 : return ""
    for i in range(0, len(s), 2):
        a, b = s[i], s[i + 1]
        ret.append(b * int(a))
    t = "".join(ret)
    return t

def next(s):
    ret = []
    cnt = 1
    for i in range(len(s)):
        if i == len(s) - 1 or s[i] != s[i + 1]:
            ret.append(str(cnt) + s[i])
            cnt = 1
        else:
            cnt += 1
    return "".join(ret)

tc = 1
while True:
    n = input()
    if n == "0" : break
    while True:
        p = prev(n)
        if not p or next(p) != n or p == n : break
        n = p
    print(f'Test {tc}: {n}')
    tc += 1
