template<typename T, typename C>
void sort(T* a, int l, int r, C compare) {
    if (r - l <= 1) {
        return;
    }

    int m = (l + r) / 2;
    sort(a, l, m);
    sort(a, m, r);

    T* b = new T[r - l];
    int i = l, j = m, k = 0;
    while (i < m and j < r) {
        b[k++] = compare(a[i], a[j]) ? a[i++] : a[j++];
    }
    while (i < m) {
        b[k++] = a[i++];
    }
    while (j < r) {
        b[k++] = a[j++];
    }

    for (int i = l, j = 0; i < r; i++, j++) {
        a[i] = b[j];
    }
}
