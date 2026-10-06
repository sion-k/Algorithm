template <typename T>
struct vector {
    T* a;
    int s, c;

    vector() : a(new T[1]), s(0), c(1) {}

    vector(const vector& v) : a(new T[v.c]), s(v.s), c(v.c) {
        for (int i = 0; i < s; i++) {
            a[i] = v.a[i];
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
};
