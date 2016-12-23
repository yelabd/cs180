import java.io.File;
import java.lang.reflect.Array;

/**
 * Created by youssefelabd on 12/2/16.
 */
public class Recursion {

    public static int determinant(int[][] matrix){
        if (matrix.length == 1 && matrix[0].length == 1) {
            return matrix[0][0];
        } else {
            int d = 0;
            for (int i = 0; i < matrix.length;i++){
                int[][] temp = new int[matrix.length-1][matrix.length-1];
                for(int j = 0; j < temp.length;j++){
                    boolean shift = false;
                    for(int b = 0;b < temp.length;b++){
                        if(b != i && shift) {
                            temp[j][b] = matrix[j+1][b+1];
                        }else if(b != i){
                                temp[j][b] = matrix[j+1][b];
                                //System.out.println("norm: " +b +": " + matrix[j+1][b]);
                            }else{
                                temp[j][b] = matrix[j+1][++b];
                                //System.out.println("else: " +b +": "+ matrix[j+1][b]);
                                b--;
                                shift = true;
                            }
                        }
                    }
                if (i%2 != 0){
                    d -= (matrix[0][i]*determinant(temp));
                }else {
                    d += (matrix[0][i]*determinant(temp));
                }
            }
            return d;
        }
    }
    public static int filecount(File f){
        if (f.isFile()){
            return 1;
        }else if (f.isDirectory()){
            File[] fileArray = f.listFiles();
            int j = 0;
            for (File aFileArray : fileArray) {
                j += filecount(aFileArray);
            }
            return j;
        }

        return 0;
    }
}
