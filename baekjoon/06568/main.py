import sys

class Basic_Computer:
    INSTRUCTIONS = ['sta', 'lda', 'beq', 'nop', 'dec', 'inc', 'jmp', 'hlt']

    def __init__(self, m):
        self.m = m
        self.ac = 0
        self.pc = 0
        self.halt = False

    def run(self):
        while not self.halt:
            opcode = self.fetch()
            op, opr = self.decode(opcode)
            self.pc = (self.pc + 1) % 32
            self.excute(op, opr)

    def fetch(self):
        return self.m[self.pc]

    def decode(self, opcode):
        op = self.INSTRUCTIONS[int(opcode[0:3], 2)]
        opr = int(opcode[3:], 2)
        return op, opr

    def excute(self, op, opr):
        getattr(self, op)(opr)

    def sta(self, x):
        self.m[x] = f"{self.ac:08b}"

    def lda(self, x):
        self.ac = int(self.m[x], 2)
    
    def beq(self, x):
        if self.ac == 0:
            self.pc = x
    
    def nop(self, x):
        pass

    def dec(self, x):
        self.ac = (self.ac - 1 + 256) % 256

    def inc(self, x):
        self.ac = (self.ac + 1 + 256) % 256

    def jmp(self, x):
        self.pc = x

    def hlt(self, x):
        self.halt = True

m = sys.stdin.readlines()

for i in range(0, len(m), 32):
    bc = Basic_Computer(m[i:i + 32])
    bc.run()

    print(f"{bc.ac:08b}")
