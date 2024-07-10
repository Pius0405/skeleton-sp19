import java.util.ArrayList;
public class UnionFind {

    private final int[] parents;
    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < parents.length; ++i) {
            parents[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parents.length || vertex < 0) {
            throw new IllegalArgumentException(vertex + " is not in the disjoint sets.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int parentV = parent(v1);
        while (parentV >= 0) {
            parentV = parent(parentV);
        }
        return -parentV;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        return rootV1 == rootV2;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (! connected(v1, v2)) {
            int v1SetSize = sizeOf(v1);
            int v2SetSize = sizeOf(v2);
            int rootV1 = find(v1);
            int rootV2 = find(v2);
            if (v1SetSize == v2SetSize || v1SetSize > v2SetSize) {
                parents[rootV1] -= v2SetSize;
                parents[rootV2] = rootV1;
            }
            else {
                parents[rootV2] -= v1SetSize;
                parents[rootV1] = rootV2;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        ArrayList<Integer> known = new ArrayList<>();
        while (true) {
            if (parent(vertex) < 0) {
                break;
            }
            known.add(vertex);
            vertex = parent(vertex);
        }
        for (int v : known) {
            parents[v] = vertex;
        }
        return vertex;
    }
}
