class Calculator :
    def __init__(self, a, b):
        # 필드를 함수 내에서 정의할 수가 있다니
        self.u = a
        self.v = b
    # 메소드의 첫번째 매개변수로 인스턴스를 명시해줘야 한다
    def add(self) : return self.u + self.v

a = Calculator(1, 1)
print(a.add())