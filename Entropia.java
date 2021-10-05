import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Entropia {
    public static double log2(double N)
    {
        return Math.log(N) / Math.log(2);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("konyv_magyar.txt");
        Scanner reader = new Scanner(file);
        Map<String, Integer> hist = new TreeMap<String, Integer>();
        int S = 0;
        String string = "";
        while (reader.hasNextLine()) {
            string += reader.nextLine() + " ";
        }
        reader.close();

        String[] tokens = string.split(" ");
        for (String token: tokens) {
            String szo = token;
            szo = szo.toLowerCase();
            S++;
            if (hist.containsKey(szo)) {
                Integer j = hist.get(szo);
                hist.put(szo, j+1);
            } else {
                hist.put(szo, 1);
            }
        }
        Double H1 = 0.0;
        for (Map.Entry<String, Integer> kv: hist.entrySet()) {
            double p = kv.getValue().doubleValue();
            H1 += p/S * log2(S/p);
        }
        System.out.println("Unnigram: " + H1);

        Map<String, Integer> hist2 = new TreeMap<String, Integer>();
        S--;
        String szo1 = tokens[0];
        for (int i = 1; i < tokens.length; i++){
            String szo2 = tokens[i];
            String szo = szo1 + " " + szo2;
            szo = szo.toLowerCase();
            if (hist2.containsKey(szo)) {
                Integer j = hist2.get(szo);
                hist2.put(szo, j+1);
            } else {
                hist2.put(szo, 1);
            }
            szo1 = szo2;
        }
        Double H2 = 0.0;
        for (Map.Entry<String, Integer> kv: hist2.entrySet()) {
            double p = kv.getValue().doubleValue();
            H2 += p/S * log2(S/p);
        }
        System.out.println("Bigrammok: " + H2/2);

        Map<String, Integer> hist3 = new TreeMap<String, Integer>();
        S--;
        szo1 = tokens[0];
        String szo2 = tokens[1];
        for (int i = 2; i < tokens.length; i++) {
            String szo3 = tokens[i];
            String szo = szo1 + " " + szo2 + " " + szo3;
            szo = szo.toLowerCase();
            if (hist3.containsKey(szo)) {
                Integer j = hist3.get(szo);
                hist3.put(szo, j+1);
            } else {
                hist3.put(szo, 1);
            }
            szo1 = szo2;
            szo2 = szo3;
        }
        Double H3 = 0.0;
        for (Map.Entry<String, Integer> kv: hist3.entrySet()) {
            double p = kv.getValue().doubleValue();
            H3 += p/S * log2(S/p);
        }
        System.out.println("Trigrammok: " + H3/3);
    }
}
