template<typename T>
void swap(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

struct heap {
    int t[200001];
    int s;

    heap() : s(1) {}

    void push(int x) {
        int i = s++;
        t[i] = x;

        while (i != 1 and t[i / 2] < t[i]) {
            swap(t[i / 2], t[i]);
            i /= 2;
        }
    }

    void pop() {
        t[1] = t[s - 1];
        s--;

        int i = 1;
        while (true) {
            int j = i;
            if (2 * i < s and t[2 * i] > t[j]) {
                j = 2 * i;
            }
            if (2 * i + 1 < s and t[2 * i + 1] > t[j]) {
                j = 2 * i + 1;
            }

            if (i == j) {
                break;
            }

            swap(t[i], t[j]);
            i = j;
        }
    }

    int top() {
        return t[1];
    }

    bool empty() {
        return s == 1;
    }
};
