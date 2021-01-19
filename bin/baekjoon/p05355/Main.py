T = int(input())
for c in range(T) :
    term = input().split()
    ret = float(term[0])
    for i in range(1, len(term)) :
        if term[i] == '@':
            ret *= 3
        elif term[i] == '%':
            ret += 5
        else :
            ret -= 7
    print("%0.2f" % ret)
