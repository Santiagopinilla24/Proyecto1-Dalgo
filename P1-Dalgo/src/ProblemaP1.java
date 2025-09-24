// Nicolas Sanchez - 
// Santiago Pinilla Ruiz - 202411036

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProblemaP1 {
	public static void main(String[] args) throws Exception {
		ProblemaP1 instancia = new ProblemaP1();
		try ( 
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
		) { 
			String line = br.readLine();
			int casos = Integer.parseInt(line);
			line = br.readLine();
			String rStr = "";
			for(int i=0;i<casos && line!=null && line.length()>0 && !"0".equals(line);i++) {
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				final int nCeldas = numeros[0];
				final int energiaTotal = numeros[1];
				final int[] creatividad = Arrays.copyOfRange(numeros, 2, 7); 
				int respuesta = instancia.MaxCreatividad(nCeldas,energiaTotal,creatividad);
				
				if(i == casos-1) {
					rStr += respuesta;
				}
				else{
					rStr += respuesta+"\n";}
				
				line = br.readLine();
			}
			System.out.println(rStr);
		}
	}
	
    public int MaxCreatividad(int k, int n, int[] creatividad) {
        int[] cre = new int[n + 1];
        for (int x = 0; x <= n; x++) {
            cre[x] = NumCreatividad(x, creatividad);
        }
        
        int[][] m = new int[k + 1][n + 1];
        

        for (int j = 0; j <= n; j++) {
            m[0][j] = (j == 0) ? 0 : Integer.MIN_VALUE;
        }
        for (int i = 0; i <= k; i++) {
            m[i][0] = 0;
        }
      
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n; j++) {
                int maxVal = 0;
                for (int x = 0; x <= j; x++) {
                    if (i == 1) {
      
                        if (x == j) {
                            maxVal = Math.max(maxVal, cre[j]);
                        }
                    } else {
                        if (m[i - 1][j - x] >= 0) {
                            maxVal = Math.max(maxVal, m[i - 1][j - x] + cre[x]);
                        }
                    }
                }
                m[i][j] = maxVal;
            }
        }
        
        return m[k][n];
    }
    
    // Se halla la creatividad de un número en especifico.
    public int NumCreatividad(int num, int[] c) {
        int r = 0;
        int[] cCopia = c.clone(); 
        invertir(cCopia);
        
        if (num < 0 || num > 99999) {
            throw new IllegalArgumentException("El número debe estar entre 0 y 99999");
        }
        
        String numeroStr = String.format("%05d", num);
        List<Integer> digitos = new ArrayList<>();
        for (int i = 0; i < numeroStr.length(); i++) {
            char digitoChar = numeroStr.charAt(i);
            int digito = Character.getNumericValue(digitoChar);
            digitos.add(digito);
        }
        
        for(int i = 0; i < digitos.size(); i++) {
            if (digitos.get(i) == 3) {
                r += cCopia[i];
            }
            if (digitos.get(i) == 6) {
                r += cCopia[i] * 2;
            }
            if (digitos.get(i) == 9) {
                r += cCopia[i] * 3;
            }
        }
        return r;
    }
	
	 public static void invertir(int[] array) {
	        int inicio = 0;
	        int fin = array.length - 1;
	        
	        while (inicio < fin) {
	            int temp = array[inicio];
	            array[inicio] = array[fin];
	            array[fin] = temp;
	            
	            inicio++;
	            fin--;
	        }
	    }
	 
//	 public static void imprimir(int[][] matriz) {
//	        for (int i = 0; i < matriz.length; i++) {
//	            for (int j = 0; j < matriz[i].length; j++) {
//	                System.out.printf("%4d", matriz[i][j]); // 4 espacios de ancho
//	            }
//	            System.out.println();
//	        }
//	    }
	
}
