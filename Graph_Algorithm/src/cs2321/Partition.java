package cs2321;

import net.datastructures.Position;

public class Partition<E> {

    private class Locator<E> implements Position<E> {
        public E element;
        public int size;
        public Locator<E> parent;
        public Locator(E elem) {
            element = elem;
            size = 1;
            parent = this;
        }
        public E getElement() { return element; }

        private boolean validate(Partition<E> part) {
            return Partition.this == part;
        }
    }

    public Position<E> makeCluster(E e) {
        return new Locator<E>(e);
    }

    private Locator<E> validate(Position<E> pos) {
        if (!(pos instanceof Locator)) throw new IllegalArgumentException("Invalid Position");
        Locator<E> loc = (Locator<E>) pos;
        if (!loc.validate(this))
            throw new IllegalArgumentException("Position Not Found!");
        return loc;
    }

    public Position<E> find(Position<E> p) {
        Locator<E> loc = validate(p);
        if(loc.parent != loc)
            loc.parent = (Locator<E>) find(loc.parent);
        return loc.parent;
    }

    public void union(Position<E> p, Position<E> q) {
        Locator<E> a = (Locator<E>) find(p);
        Locator<E> b = (Locator<E>) find(q);
        if(a != b) {
            if(a.size > b.size) {
                b.parent = a;
                a.size += b.size;
            } else {
                a.parent = b;
                b.size += a.size;
            }
        }
    }
}
