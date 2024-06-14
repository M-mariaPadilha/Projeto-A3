import java.util.Collections;
import java.util.List;

public class Ordenar {

    // Insertion Sort
    public static void insertionSort(List<String> numeros) {
        for (int i = 1; i < numeros.size(); i++) {
            String key = numeros.get(i);
            int j = i - 1;
            while (j >= 0 && numeros.get(j).compareTo(key) > 0) {
                numeros.set(j + 1, numeros.get(j));
                j = j - 1;
            }
            numeros.set(j + 1, key);
        }
    }

    // Selection Sort
    public static void selectionSort(List<String> numeros) {
        for (int i = 0; i < numeros.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(j).compareTo(numeros.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            Collections.swap(numeros, i, minIndex);
        }
    }

    // Merge Sort
    public static void mergeSort(List<String> numeros) {
        if (numeros.size() > 1) {
            int mid = numeros.size() / 2;
            List<String> left = numeros.subList(0, mid);
            List<String> right = numeros.subList(mid, numeros.size());

            mergeSort(left);
            mergeSort(right);

            merge(numeros, left, right);
        }
    }
    
    private static void merge(List<String> numeros, List<String> left, List<String> right) {
        int leftIndex = 0, rightIndex = 0, mainIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                numeros.set(mainIndex++, left.get(leftIndex++));
            } else {
                numeros.set(mainIndex++, right.get(rightIndex++));
            }
        }

        while (leftIndex < left.size()) {
            numeros.set(mainIndex++, left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            numeros.set(mainIndex++, right.get(rightIndex++));
        }
    }

    // Quick Sort
    public static void quickSort(List<String> numeros) {
        quickSort(numeros, 0, numeros.size() - 1);
    }

    private static void quickSort(List<String> numeros, int low, int high) {
        if (low < high) {
            int pi = partition(numeros, low, high);
            quickSort(numeros, low, pi - 1);
            quickSort(numeros, pi + 1, high);
        }
    }

    private static int partition(List<String> numeros, int low, int high) {
        String pivot = numeros.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (numeros.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(numeros, i, j);
            }
        }
        Collections.swap(numeros, i + 1, high);
        return i + 1;
    }

    // Heap Sort
    public static void heapSort(List<String> numeros) {
        int n = numeros.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(numeros, n, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Collections.swap(numeros, 0, i);
            heapify(numeros, i, 0);
        }
    }

    private static void heapify(List<String> numeros, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && numeros.get(left).compareTo(numeros.get(largest)) > 0) {
            largest = left;
        }

        if (right < n && numeros.get(right).compareTo(numeros.get(largest)) > 0) {
            largest = right;
        }

        if (largest != i) {
            Collections.swap(numeros, i, largest);
            heapify(numeros, n, largest);
        }
    }
}
