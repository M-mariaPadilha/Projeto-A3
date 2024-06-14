import java.util.Collections;
import java.util.List;

public class Ordenar {

    // Insertion Sort
    public static void insertionSort(List<String> numeros) {
        for (int i = 1; i < numeros.size(); i++) {
            String key = numeros.get(i); // Armazena o elemento atual para inserção ordenada
            int j = i - 1; // Inicializa o índice do elemento anterior ao atual
            // Move os elementos maiores que key uma posição à frente
            while (j >= 0 && numeros.get(j).compareTo(key) > 0) {
                numeros.set(j + 1, numeros.get(j));
                j = j - 1;
            }
            numeros.set(j + 1, key); // Insere key na posição correta
        }
    }

    // Selection Sort
    public static void selectionSort(List<String> numeros) {
        for (int i = 0; i < numeros.size() - 1; i++) {
            int minIndex = i; // Assume que o primeiro elemento não ordenado é o mínimo
            // Encontra o menor elemento no restante da lista não ordenada
            for (int j = i + 1; j < numeros.size(); j++) {
                if (numeros.get(j).compareTo(numeros.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            // Troca o elemento mínimo com o primeiro elemento não ordenado
            Collections.swap(numeros, i, minIndex);
        }
    }

    // Merge Sort
    public static void mergeSort(List<String> numeros) {
        if (numeros.size() > 1) {
            int mid = numeros.size() / 2; // Calcula o ponto médio da lista
            List<String> left = numeros.subList(0, mid); // Divide a lista em duas metades
            List<String> right = numeros.subList(mid, numeros.size());

            mergeSort(left); // Chama mergeSort recursivamente para ordenar as metades
            mergeSort(right);

            merge(numeros, left, right); // Combina as duas metades ordenadas
        }
    }
    
    // Método privado para combinar duas listas ordenadas (parte do Merge Sort)
    private static void merge(List<String> numeros, List<String> left, List<String> right) {
        int leftIndex = 0, rightIndex = 0, mainIndex = 0;

        // Combina os elementos de left[] e right[] em numeros[]
        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex).compareTo(right.get(rightIndex)) < 0) {
                numeros.set(mainIndex++, left.get(leftIndex++));
            } else {
                numeros.set(mainIndex++, right.get(rightIndex++));
            }
        }

        // Adiciona os elementos restantes de left[], se houver
        while (leftIndex < left.size()) {
            numeros.set(mainIndex++, left.get(leftIndex++));
        }

        // Adiciona os elementos restantes de right[], se houver
        while (rightIndex < right.size()) {
            numeros.set(mainIndex++, right.get(rightIndex++));
        }
    }

    // Quick Sort
    public static void quickSort(List<String> numeros) {
        quickSort(numeros, 0, numeros.size() - 1); // Método de inicialização para Quick Sort
    }

    // Método privado para Quick Sort com particionamento
    private static void quickSort(List<String> numeros, int low, int high) {
        if (low < high) {
            int pi = partition(numeros, low, high); // Obtém o índice do pivô
            quickSort(numeros, low, pi - 1); // Ordena os elementos antes do pivô
            quickSort(numeros, pi + 1, high); // Ordena os elementos após o pivô
        }
    }

    // Método privado para particionamento usado no Quick Sort
    private static int partition(List<String> numeros, int low, int high) {
        String pivot = numeros.get(high); // Seleciona o último elemento como pivô
        int i = (low - 1); // Índice do menor elemento

        // Percorre todos os elementos, exceto o último
        for (int j = low; j < high; j++) {
            // Se o elemento atual for menor ou igual ao pivô
            if (numeros.get(j).compareTo(pivot) <= 0) {
                i++;
                Collections.swap(numeros, i, j); // Troca elementos menores com o pivô
            }
        }
        Collections.swap(numeros, i + 1, high); // Coloca o pivô na posição correta
        return i + 1; // Retorna a posição do pivô
    }

    // Heap Sort
    public static void heapSort(List<String> numeros) {
        int n = numeros.size(); // Tamanho da lista

        // Constrói um heap máximo
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(numeros, n, i);
        }

        // Extrai elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            Collections.swap(numeros, 0, i); // Move o maior elemento para o final
            heapify(numeros, i, 0); // Recria o heap reduzido
        }
    }

    // Método privado para ajustar um heap subárvore
    private static void heapify(List<String> numeros, int n, int i) {
        int largest = i; // Inicializa o maior como raiz
        int left = 2 * i + 1; // Filho esquerdo de i
        int right = 2 * i + 2; // Filho direito de i

        // Se o filho esquerdo é maior que a raiz
        if (left < n && numeros.get(left).compareTo(numeros.get(largest)) > 0) {
            largest = left;
        }

        // Se o filho direito é maior que a raiz
        if (right < n && numeros.get(right).compareTo(numeros.get(largest)) > 0) {
            largest = right;
        }

        // Se o maior não é a raiz
        if (largest != i) {
            Collections.swap(numeros, i, largest); // Troca raiz com maior filho
            heapify(numeros, n, largest); // Recursivamente ajusta o subárvore afetada
        }
    }
}
