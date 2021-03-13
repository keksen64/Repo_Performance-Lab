package package1;

import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.lang.Double;

public class Class1 {
    public static void main(String[] args) throws IOException {

        String addr1 = args[0];
        if(!(addr1.endsWith(".txt"))){
            addr1 = addr1+".txt";
        }

        Path path = Paths.get(addr1);
        Scanner skan = new Scanner(path);
        String[] data = new String[200000];
        int q = 0;
        //записывает числа в массив строк
        while (skan.hasNextLine()){
            data[q] = (skan.nextLine());
            q++;
        }
        //убирает пустые ячейки
        String[] Sresultdata = new String[q];
        Sresultdata = Arrays.copyOf(data, q);

        //преоразует текстовый массив в числовой
        Double[] resultdata = new Double[q];
        for(int u = 0; u < q; u++){
            resultdata[u] = Double.parseDouble(Sresultdata[u]);
        }

        Arrays.sort(resultdata);
        double sum = 0.0;
        for(int i=0; i<q; i++){
            sum = sum + resultdata[i];
        }
        double p = q;
        double average = (sum/p);

        double per = 0.9*(p-1);
        int perint = (int)per;
        double ostatok = per - (perint*1.0);
        double percentile =( resultdata[perint] + (resultdata[perint+1]-resultdata[perint]) * ostatok);

        double median;
        if(q%2==0){
            median = (resultdata[(q/2)-1] + resultdata[q/2])/2.0;
        } else
            median = resultdata[(q/2)];

        System.out.println(String.format("%.2f",percentile));
        System.out.println(String.format("%.2f",median));
        System.out.println(String.format("%.2f",resultdata[q-1]));
        System.out.println(String.format("%.2f",resultdata[0]));
        System.out.println(String.format("%.2f",average));
    }
}
