import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class PesquisaBinaria {

    // Método público para iniciar a pesquisa binária
    public static int pesquisaBinaria(List<String> numeros, String chave) {
        long chaveLong = Long.parseLong(chave); // Converte a chave para long

        // Ordena a lista de números usando um Comparator para comparar como longs
        Collections.sort(numeros, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Long.compare(Long.parseLong(s1), Long.parseLong(s2));
            }
        });

        // Chama o método de busca binária recursiva para encontrar a chave
        return buscaBinariaRecursiva(numeros, 0, numeros.size() - 1, chaveLong);
    }

    // Método privado para realizar a busca binária de forma recursiva
    private static int buscaBinariaRecursiva(List<String> numeros, int inicio, int fim, long chave) {
        if (inicio > fim) {
            return -1; // Retorna -1 se a chave não for encontrada
        }

        int meio = inicio + (fim - inicio) / 2; // Calcula o meio da lista
        long valorMeio = Long.parseLong(numeros.get(meio)); // Obtém o valor no meio como long

        if (valorMeio == chave) {
            return meio; // Retorna o índice se a chave for encontrada
        }

        // Decide em qual metade continuar buscando com base na comparação
        if (valorMeio < chave) {
            return buscaBinariaRecursiva(numeros, meio + 1, fim, chave); // Busca na metade direita
        } else {
            return buscaBinariaRecursiva(numeros, inicio, meio - 1, chave); // Busca na metade esquerda
        }
    }
}
