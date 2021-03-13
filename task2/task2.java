package package2;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class class2 {
    public static void main(String[] args) throws IOException{

        String addr1 = args[0];
        if(!(addr1.endsWith(".txt"))){
            addr1 = addr1+".txt";
        }

        String addr2 = args[1];
        if(!(addr2.endsWith(".txt"))){
            addr2 = addr2+".txt";
        }

        Path path1 = Paths.get(addr1);
        Scanner skan1 = new Scanner(path1);

        Path path2 = Paths.get(addr2);
        Scanner skan2 = new Scanner(path2);

        int x = 0;
        int y = 1;
//считывает координаты фигуры в стринг массив
        int i = 0;
        String[][] data = new String[4][2];
        while (skan1.hasNextLine()){
            data[i]  = ((skan1.nextLine()).split("[:\\ ]"));
            i++;
        }
//переводит стринг в дабл
        Double[][] figure = new Double[4][2];
        for (int b =0; b<4 ; b++ ){
            figure[b][x] = Double.parseDouble(data[b][x]);
            figure[b][y] = Double.parseDouble(data[b][y]);
        }
//считывает точки в стринг массив
        int l = 0;
        String[][] datapoint = new String[1000][2];
        while (skan2.hasNextLine()){
            datapoint[l]  = ((skan2.nextLine()).split("[:\\ ]"));
            l++;
        }
//переводит точки в дабл
        Double[][] point = new Double[l][2];
        for (int b =0; b<l ; b++ ){
            point[b][x] = Double.parseDouble(datapoint[b][x]);
            point[b][y] = Double.parseDouble(datapoint[b][y]);
        }
//считает площадь четырехугольника SF
        Double AB = Math.sqrt(Math.pow((figure[1][x]-figure[0][x]), 2)+Math.pow((figure[1][y]-figure[0][y]), 2));
        Double BC = Math.sqrt(Math.pow((figure[2][x]-figure[1][x]), 2)+Math.pow((figure[2][y]-figure[1][y]), 2));
        Double CD = Math.sqrt(Math.pow((figure[3][x]-figure[2][x]), 2)+Math.pow((figure[3][y]-figure[2][y]), 2));
        Double DA = Math.sqrt(Math.pow((figure[0][x]-figure[3][x]), 2)+Math.pow((figure[0][y]-figure[3][y]), 2));
        Double P = (AB + BC + CD+ DA)/ 2.0;
        Double SF = Math.sqrt((P-AB)*(P-BC)*(P-CD)*(P-DA));

//прогоняет точки
        for (int m=0; m<l; m++){
            //проверяет на совпадение с вершиной
            Boolean bool = false;
            for(int t = 0; t<4 ; t++){
                if(((point[m][x]-figure[t][x])==0) && ((point[m][y]-figure[t][y])==0)){
                    bool = true;
                }
            }
            if(bool){
                //вершина
                System.out.println("0");
                continue;
            }
//считает площади треугольников
            Double SS1= Math.abs(( ((figure[0][x])-(point[m][x]))*((figure[1][y])-(point[m][y])) - ((figure[1][x])-(point[m][x]))*((figure[0][y])-(point[m][y])) )/2);
            Double SS2= Math.abs(( ((figure[1][x])-(point[m][x]))*((figure[2][y])-(point[m][y])) - ((figure[2][x])-(point[m][x]))*((figure[1][y])-(point[m][y])) )/2);
            Double SS3= Math.abs(( ((figure[2][x])-(point[m][x]))*((figure[3][y])-(point[m][y])) - ((figure[3][x])-(point[m][x]))*((figure[2][y])-(point[m][y])) )/2);
            Double SS4= Math.abs(( ((figure[3][x])-(point[m][x]))*((figure[0][y])-(point[m][y])) - ((figure[0][x])-(point[m][x]))*((figure[3][y])-(point[m][y])) )/2);
//сумма площадей треугольников
            Double SUM = SS1+SS2+SS3+SS4;
//сравнивает сумму площ треуг с площ фигуры
            if(Math.abs(SUM-SF)<0.00001){
                //проверяет не на ребре ли
                if( (Math.abs(SS1-0)<0.00001) || (Math.abs(SS2-0)<0.00001) ||(Math.abs(SS3-0)<0.00001) ||(Math.abs(SS4-0)<0.00001) ){
                    //ребро
                    System.out.println("1");
                }else
                    //прин
                    System.out.println("2");
            } else
                //не прин
                System.out.println("3");
        }
    }
}
