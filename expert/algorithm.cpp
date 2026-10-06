template <typename T>
void swap(T& u, T& v) {
    auto t = u;
    u = v;
    v = t;
}

template <typename T>
T max(const T& u, const T& v) {
    return u < v ? v : u;
}

template <typename T>
T min(const T& u, const T& v) {
    return u < v ? u : v;
}
