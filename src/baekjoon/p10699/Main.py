A, B = map(int, input().split())
C = int(input())
minute = 60 * A + B + C
if minute >= (60 * 24) :
    minute -= 60 * 24
print("%d %d" % (minute // 60, minute % 60))
