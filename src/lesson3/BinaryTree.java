package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
@SuppressWarnings("WeakerAccess")
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        }
        else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        }
        else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */

    //Трудоёмкость:    O(n) n - высота дерева
    //Ресурсоёмкость:  O(1)

    @Override
    public boolean remove(Object o) {
        if (root == null) {
            return false;
        } else {
            size--;
            root = removingOfNode(root, (T) o);
            return true;
        }
    }

    public Node<T> removingOfNode(Node<T> root, T value) {
        int comparison = value.compareTo(root.value);

        if (size == 1) {
            return null;
        }

        if (comparison > 0) {
            root.right = removingOfNode(root.right, value);
        } else if (comparison < 0) {
            root.left = removingOfNode(root.left, value);
        } else if (root.left != null && root.right != null) {

            Node<T> node = new Node<>(minValueOfSubTree(root.right).value);
            node.left = root.left;
            node.right = root.right;
            root = node;
            root.right = removingOfNode(root.right, root.value);

        } else if (root.left != null) {
            root = root.left;
        } else {
            root = root.right;
        }
        return root;
    }


    public Node<T> minValueOfSubTree(Node<T> node) {
        if (node.left == null) {
            return node;
        } else {
            return minValueOfSubTree(node.left);
        }
    }





    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }



    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        }
        else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        }
        else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {
        private Stack<Node<T>> stack = new Stack<>();

        private Node<T> current;



        private BinaryTreeIterator() {
            current = root;

            while (current != null) {

                stack.push(current);
                current = current.left;

            }
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        //Трудоёмкость:    O(n)
        //Ресурсоёмкость:  O(n)
        //     n - кол-во узлов

        private Node<T> findNext() {
            current = stack.pop();

            return current;
        }

        @Override
        public boolean hasNext() {
            return !stack.empty();
        }

        @Override
        public T next() {
            current = findNext();
            if (current == null) {
                throw new NoSuchElementException();
            }

            Node<T> update = current;
            if (update.right != null) {
                update = update.right;
                while (update != null) {
                    stack.push(update);
                    update = update.left;
                }
            }
            return current.value;
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        //Трудоёмкость:    O(n)
        //Ресурсоёмкость:  O(1)

        @Override
        public void remove() {
            size--;
            root = BinaryTree.this.removingOfNode(root, current.value);
        }
    }



    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
