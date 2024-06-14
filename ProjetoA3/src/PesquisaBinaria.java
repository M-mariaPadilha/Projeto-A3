import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class PesquisaBinaria {

    public static int pesquisaBinaria(List<String> numeros, String chave) {
        long chaveLong = Long.parseLong(chave); 
        
        Collections.sort(numeros, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Long.compare(Long.parseLong(s1), Long.parseLong(s2));
            }
        });
        
        return buscaBinariaRecursiva(numeros, 0, numeros.size() - 1, chaveLong);
    }

    private static int buscaBinariaRecursiva(List<String> numeros, int inicio, int fim, long chave) {
        if (inicio > fim) {
            return -1; 
        }

        int meio = inicio + (fim - inicio) / 2;
        long valorMeio = Long.parseLong(numeros.get(meio)); 

        if (valorMeio == chave) {
            return meio; 
        }

        if (valorMeio < chave) {
            return buscaBinariaRecursiva(numeros, meio + 1, fim, chave);
        } else {
            return buscaBinariaRecursiva(numeros, inicio, meio - 1, chave);
        }
    }
}
