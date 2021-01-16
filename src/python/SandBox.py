import re
file = open('ck_vip1.txt', 'rt', encoding='UTF8')
html = file.read().lower()
p = re.compile("bmw=.*[\[\(].*[\]\)]")
m = p.findall(html)
S = str(m[0])
if S.find('[') != -1 :
    S = S[S.find('[') + 1 : S.find(']')]
else :
    S = S[S.find('(') + 1: S.find(')')]
ret = S.split(",")
print(ret)
for i in ret :
    print(chr(int(i) - 159), end="")
