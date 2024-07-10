public class Main {
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(10);

        // Initial state: all elements are their own parents
        for (int i = 0; i < 10; i++) {
            System.out.println("Parent of " + i + ": " + uf.parent(i)); // Should print -1 for all
        }

        // Union operations
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);

        // Check if unions are successful
        System.out.println("Connected (0, 1): " + uf.connected(0, 1)); // Should print true
        System.out.println("Connected (2, 3): " + uf.connected(2, 3)); // Should print true
        System.out.println("Connected (4, 5): " + uf.connected(4, 5)); // Should print true
        System.out.println("Connected (6, 7): " + uf.connected(6, 7)); // Should print true
        System.out.println("Connected (8, 9): " + uf.connected(8, 9)); // Should print true

        // Check if elements not unioned are not connected
        System.out.println("Connected (0, 2): " + uf.connected(0, 2)); // Should print false

        // Union more elements
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(5, 6);

        // Check the size of sets
        System.out.println("Size of set containing 0: " + uf.sizeOf(0)); // Should reflect the size of the connected component

        // Check connections
        System.out.println("Connected (0, 3): " + uf.connected(0, 3)); // Should print true
        System.out.println("Connected (4, 6): " + uf.connected(4, 6)); // Should print true

        // Find root
        System.out.println("Root of 0: " + uf.find(0)); // Should return the root of the set containing 0

        // Union remaining elements
        uf.union(7, 8);
        uf.union(0, 9);

        // Final checks
        System.out.println("Connected (0, 9): " + uf.connected(0, 9)); // Should print true
        System.out.println("Size of set containing 0: " + uf.sizeOf(0)); // Should reflect the size of the entire set
    }
}