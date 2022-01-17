s = input()
def f(start, end):
    length = end - start + 1
    if length < 4 : return False
    if length % 4 == 0:
        cmp = ""
        for x in "wolf" : cmp += x * (length // 4)
        if cmp == s[start:end + 1] : return True
    for here in range(start, end + 1, 4):
        if f(start, here - 1) and f(here, end) : return True
    return False
print(1 if f(0, len(s) - 1) else 0)