package Library;

import java.util.ArrayList;

/**
 * Generified.
 * Created by Yuya on 2015/07/15.
 */
public class UnionFindTree<T extends Comparable<? super T>> {
    class Node<E extends Comparable<? super E>> {
        private E data;
        private E parent;
        private ArrayList<E> children;
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

        public E getParent() {
            return parent;
        }

        public void setParent(E parent) {
            this.parent = parent;
        }

        public ArrayList<E> getChildren() {
            return children;
        }

        public void setChildren(ArrayList<E> children) {
            this.children = children;
        }

        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
    }
}
