#include <iostream>
using std::ostream;

template <typename... Ts>
struct tuple;

template <>
struct tuple<> {
    bool operator<(const tuple&) {
        return false;
    }

    void print(ostream& os) {}
};

template <typename T, typename... Ts>
struct tuple<T, Ts...> {
    T head;
    tuple<Ts...> tail;

    tuple() : head(), tail() {}
    tuple(T x, Ts... xs) : head(x), tail(xs...) {}

    bool operator<(const tuple& o) {
        if (head < o.head) return true;
        if (o.head < head) return false;
        return tail < o.tail;
    }

    void print(ostream& os) {
        os << head;
        if (sizeof...(Ts) > 0) {
            os << ", ";
        }
        tail.print(os);
    }
};

template <typename... Ts>
ostream& operator<<(ostream& os, tuple<Ts...>& t) {
    os << "(";
    t.print(os);
    os << ")";
    return os;
}

template <int I>
struct tuple_get {
    template <typename... Ts>
    static auto& get(tuple<Ts...>& t) {
        return tuple_get<I - 1>::get(t.tail);
    }
};

template <>
struct tuple_get<0> {
    template <typename... Ts>
    static auto& get(tuple<Ts...>& t) {
        return t.head;
    }
};

template <int I, typename... Ts>
auto& get(tuple<Ts...>& t) {
    return tuple_get<I>::get(t);
}
