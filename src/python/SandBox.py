# 리모컨
_now = 100
N = list(input().strip())
_N = int(''.join(N))
M = int(input())
b = [True for i in range(10)]
_input = ([] if M==0 else list(map(int, input().split())))
_min = 10
_max  = -1

for i in _input:
    b[i%10] = False

for i in range(1, 10):
    if b[i]:
        if _min>i:
            _min = i
        if _max<i:
            _max = i

if _now==_N or M==10:               # only use +, - buttons
    print(abs(_now-_N))
elif M==0:                          # use all buttons
    print(min((len(N)), abs(_now-_N)))
else:                               # use some number buttons and +, - buttons
    _next = 0
    if N[0]=='1':       # have to start low digit
        if len(N)==1:
            _next = _min
        else:
            for i in range(len(N)-1):
                _next *= 10
                _next += _max
    else:
        _next = _min
        for i in range(len(N)-1):
            _next *= 10
            if not b[0]:
                _next += _min

    # if only use +, - buttons is more effective than use number buttons
    if abs(_now-_N)<=(abs(_next-_N)+len(str(_next))):
        print(abs(_now-_N))
    else:
        while abs(_now-_N)>abs(_next-_N):
            _now = _next
            _temp = _next
            _count = True
            _next = 0
            ten = 1
            for i in range(len(str(_now))):
                index = _temp%10
                _temp //= 10
                if index==_max:
                    _next += (0 if b[0] else _min)*ten
                else:
                    for j in range(index+1, 10):
                        if b[j]:
                            index = j
                            break
                    _next += index*ten
                    _next += _temp*ten*10
                    _count = False
                    break
                ten *= 10
            if _count:
                _next += ten*_min

        print(abs(_now-_N)+len(str(_now)))