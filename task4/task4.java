package package4;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class class4 {
    public static void main(String[] args) throws IOException{

        String addr = args[0];
        if(!(addr.endsWith(".txt"))){
            addr = addr+".txt";
        }

        Path path = Paths.get(addr);
        Scanner skan = new Scanner(path);

        // массив для посетителей содержит (часприхода;минутыприхода;часухода;минутыухода)

        String[][] data = new String[10000][4];
        int q = 0;
        while (skan.hasNextLine()){
            data[q]  = ((skan.nextLine()).split("[:\\ ]"));
            q++;
        }

        //пересчитывает время в чистые минуты (минутыприхода;минутыухода)

        int[][] resultdata = new int[q][2];
        for (int i = 0; i<q ; i++){
            resultdata[i][0]= 60*Integer.parseInt(data[i][0])+Integer.parseInt(data[i][1]);
            resultdata[i][1]= 60*Integer.parseInt(data[i][2])+Integer.parseInt(data[i][3]);
        }

        //делает массивы в которых не только минуты прихода/ухода,
        // а все минуты, которые клиент был в банке например (410;411;412;413;414;415;416;417;418;419)
        int[][] resultdatafull = new int[q][];
        for(int i = 0; i<q; i++){
            int f = 0;
            resultdatafull[i] = new int[(resultdata[i][1])-(resultdata[i][0])];
            for (int t = 0; t< resultdatafull[i].length; t++){
                resultdatafull[i][t]=resultdata[i][0]+t;

            }
        }

        // каунт сделан чтобы в него записывать в какие минуты дня больше всего
        // нашлось совпадений с массивами посетителей. например 450 минута нашлась в 2 массиве
        // и в 3, значит в ячейке каунт(450) будет двойка
        int[] count = new int[1440];

        //берет по порядку все минуты дня (W) и смотрит в скольких массивах посещений
        // данная минута присутствует и соответственно записывает совпадения в каунт
        //переменная s - меняет номера массивов со временами клиентов
        for (int w = 1; w < 1440; w++) {

            //перебор массивов клиентов
            for (int s = 0; s<q; s++) {
                Arrays.sort(resultdatafull[s]);
                int index = Arrays.binarySearch(resultdatafull[s], w);
                if (!(index < 0)) {
                    count[w]=count[w]+1;
                }
            }
        }

        //сортирует и находит сколько максимум совпадений было у каунта
        int[] sort = new int[1440];
        sort = Arrays.copyOf(count, 1440);
        Arrays.sort(sort);

        // в переменную антворт записываются минуты когда каунт был максимальный
        //т.е. минуты когда посетителей было больше всего
        String antwort ="";
        for(int i = 0; i < 1440; i++){
            if(count[i] == sort[1439]){
                antwort = antwort + i +"\n";
            }
        }

        //считывает из антворта числа в массив тайм
        Scanner skan1 = new Scanner(antwort);
        int[] time = new int[1440];
        int k = 0;
        while (skan1.hasNextInt()){
            time[k] = skan1.nextInt();
            k++;
        }

        //обрезает в новом массиве тайм1 лишние пустые ячейки
        int[] time1 = new int[k];
        time1 = Arrays.copyOf(time, k);

        //преобразует промежутки когда покупателей больше всего из одного массива
        //от вида (410;411;412;413;414;415;416;417;418;419) в двухуровневый массив к виду (410;420)
        //в переменной таймкаунт кол-во промежутков
        int[][] time2 = new int[k][2];
        int timecount = 0;
        for(int i = 0; i<k; i++ ){
            time2[timecount][0]= time1[i];
            while (i<(k-1)){
                if(time1[i] == (time[i+1]-1)){
                    i++;
                }
                else break;
            }
            time2[timecount][1]= (time1[i]+1);
            //System.out.println("zapisan P" + timecount );
            timecount++;
        }

        //убирает лишние ячейки массива
        int[][] time3 = new int[timecount][2];
        time3 = Arrays.copyOf(time2, timecount);

        //преобразует чистые минуты в часы (410 = 6:50),
        // если нужно добавляет нули и выводит.

        for(int i =0 ; i< timecount; i++){
            int n = time3[i][0]/60;
            int n1 = time3[i][0] - n*60;
            int m = time3[i][1]/60;
            int m1 = time3[i][1] - m*60;
            if(n1==0 && m1==0){
                System.out.println(n+":"+n1+"0"+" "+m+":"+m1+"0");
            }
            if(n1==0 && !(m1==0)){
                System.out.println(n+":"+n1+"0"+" "+m+":"+m1);
            }
            if(!(n1==0) && m1==0){
                System.out.println(n+":"+n1+" "+m+":"+m1+"0");
            }
            if(!(n1==0) && !(m1==0)){
                System.out.println(n+":"+n1+" "+m+":"+m1);
            }
        }
    }
}

