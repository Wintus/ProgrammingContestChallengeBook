package Library;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Generified.
 * Created by Yuya on 2015/07/15.
 */
public class UnionFindTree<T extends Comparable<? super T>> {
    private final ArrayList<Node<T>> trees;

    public UnionFindTree(Collection<T> collection) {
        trees = new ArrayList<>();
        collection.forEach(t -> trees.add(new Node<>(t)));
    }

    class Node<E extends Comparable<? super E>> {
        private E data;
        private Node<E> parent;
        private ArrayList<Node<E>> children;
        private int rank;

        public Node(E data) {
            this.data = data;
            parent = null;
            children = new ArrayList<>();
            rank = 0;
        }

        public E getData() {
            return data;
        }

        public void setData(E data) {
            this.data = data;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public ArrayList<Node<E>> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<Node<E>> children) {
            this.children = children;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        public boolean isParent() {
            return parent == null;
        }
    }

    private Node<T> findRoot(Node<T> node) {
        if (node.isParent())
            return node;
        else {
            Node<T> parent = findRoot(node.getParent());
            node.setParent(parent);
            return parent;
        }
    }

    private Node<T> getNode(T t) {
        return trees.stream().filter(n -> n.getData() == t).findFirst().get();
    }

    void unite(T x, T y) {
        Node<T> rootX = findRoot(getNode(x)), rootY = findRoot(getNode(y));
        if (rootX == rootY) return;
        if (rootX.rank < rootY.rank) {
            rootX.setParent(rootY);
            rootY.getChildren().add(rootX);
        } else {
            rootY.setParent(rootX);
            rootX.getChildren().add(rootY);
            if (rootX.rank < rootY.rank) ++rootX.rank;
        }
    }
}
