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
			for(int i=0;i<casos && line!=null && line.length()>0 && !"0".equals(line);i++) {
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->Integer.parseInt(f)).toArray();
				final int nCeldas = numeros[0];
				final int energiaTotal = numeros[1];
				final int[] creatividad = Arrays.copyOfRange(numeros, 2, 7); 
				int respuesta = instancia.MaxCreatividad(nCeldas,energiaTotal,creatividad);
				System.out.println(respuesta);
				line = br.readLine();
			}
		}
	}
	
	public int MaxCreatividad(int k, int n,int[] creatividad) {
		int respuesta = 0;
		if(k == 1) {
			return n;
		}
		
		
		System.out.println(NumCreatividad(n,creatividad));
		return respuesta;
	}
	
	
	// Halla la creatividad de un número en especifico.
	public int NumCreatividad(int num, int[] c) {
		int r = 0;
		invertir(c);
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
        for(int i = 0;i<digitos.size();i++) {
        	if (digitos.get(i) == 3) {
        		r += c[i];
        	}
        	if (digitos.get(i) == 6) {
        		r += c[i]*2;
        	}
        	if (digitos.get(i) == 9) {
        		r += c[i]*3;
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
}
