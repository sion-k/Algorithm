template <typename T>
struct vector {
    T* a;
    int s, c;

    vector() : a(new T[1]), s(0), c(1) {}
    vector(int n) : a(new T[2 * n]{}), s(n), c(2 * n) {}
    vector(int n, T x) : a(new T[2 * n]{}), s(n), c(2 * n) {
        for (int i = 0; i < s; i++) {
            a[i] = x;
        }
    }
    vector(const vector& o) : a(new T[o.c]{}), s(o.s), c(o.c) {
        for (int i = 0; i < s; i++) {
            a[i] = o.a[i];
        }
    }

    ~vector() {
        delete[] a;
    }

    void push_back(T x) {
        if (s == c) {
            c *= 2;
            auto b = new T[c];
            for (int i = 0; i < s; i++) {
                b[i] = a[i];
            }
            delete[] a;
            a = b;
        }
        a[s++] = x;
    }

    void pop_back() {
        s--;
    }

    T* begin() {
        return a;
    }

    T* end() {
        return a + s;
    }

    T& back() {
        return a[s - 1];
    }

    T& operator[](int i) {
        return a[i];
    }

    vector& operator=(const vector& o) {
        if (this == &o) return *this;

        T* b = new T[o.c]{};
        for (int i = 0; i < o.s; i++) {
            b[i] = o.a[i];
        }
        delete[] a;

        a = b;
        s = o.s;
        c = o.c;
        return *this;
    }

    int size() const {
        return s;
    }
};
