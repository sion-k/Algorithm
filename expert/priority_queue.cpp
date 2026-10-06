template <typename T>
struct priority_queue {
    vector<T> t;

    priority_queue() {
        t.push_back(T{});
    }

    T top() {
        return t[1];
    }

    void push(T x) {
        t.push_back(x);
        int i = size();
        while (i != 1 && t[i / 2] < t[i]) {
            swap(t[i / 2], t[i]);
            i /= 2;
        }
    }

    void pop() {
        t[1] = t.back();
        t.pop_back();

        int i = 1;
        while (true) {
            int j = i;
            if (2 * i <= size() && t[j] < t[2 * i]) {
                j = 2 * i;
            }
            if (2 * i + 1 <= size() && t[j] < t[2 * i + 1]) {
                j = 2 * i + 1;
            }
            if (i == j) {
                break;
            }
            swap(t[i], t[j]);
            i = j;
        }
    }

    int size() const {
        return t.size() - 1;
    }
};
