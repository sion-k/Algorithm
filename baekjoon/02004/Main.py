def f(x):
    p = [0, 0]
    a, b = 2, 5
    while a <= x:
        p[0] += x // a
        a *= 2
    while b <= x:
        p[1] += x // b
        b *= 5
    return p

n, m = map(int, input().split())
a = f(n)
b = f(n - m)
c = f(m)
a[0] -= b[0]
a[0] -= c[0]
a[1] -= b[1]
a[1] -= c[1]

print(min(a))
