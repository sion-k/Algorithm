unsigned int rng() {
    static unsigned int x = 123456789;
    x ^= x << 13;
    x ^= x >> 17;
    x ^= x << 5;
    return x;
}

struct item {
    int key, prior, size;
    item* l, * r;
    item(int key) : key(key), prior(rng()), size(1), l(nullptr), r(nullptr) {}
};

typedef item* pitem;

int get_size(pitem t) {
    return not t ? 0 : t->size;
}

void update_size(pitem t) {
    if (t) {
        t->size = 1 + get_size(t->l) + get_size(t->r);
    }
}

void split(pitem t, int key, pitem& l, pitem& r) {
    if (not t) {
        l = r = nullptr;
    } else if (t->key <= key) {
        split(t->r, key, t->r, r), l = t;
    } else {
        split(t->l, key, l, t->l), r = t;
    }
    update_size(t);
}

void insert(pitem& t, pitem x) {
    if (not t) {
        t = x;
    } else if (t->prior < x->prior) {
        split(t, x->key, x->l, x->r), t = x;
    } else {
        insert(t->key <= x->key ? t->r : t->l, x);
    }
    update_size(t);
}

void merge(pitem& t, pitem l, pitem r) {
    if (not l or not r) {
        t = l ? l : r;
    } else if (l->prior > r->prior) {
        merge(l->r, l->r, r), t = l;
    } else {
        merge(r->l, l, r->l), t = r;
    }
}

void erase(pitem& t, int key) {
    if (t->key == key) {
        pitem th = t;
        merge(t, t->l, t->r);
        delete th;
    } else {
        erase(key < t->key ? t->l : t->r, key);
    }
}

int kth(pitem t, int k) {
    int l = get_size(t->l);
    if (k < l) {
        return kth(t->l, k);
    } else if (k == l) {
        return t->key;
    } else {
        return kth(t->r, k - l - 1);
    }
}

int rank(pitem t, int key) {
    if (not t) {
        return 0;
    } else if (t->key < key) {
        return get_size(t->l) + 1 + rank(t->r, key);
    } else {
        return rank(t->l, key);
    }
}
