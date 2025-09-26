// Nicolas Sanchez - 
// Santiago Pinilla Ruiz - 202411036

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class ProblemaP1 {
	
	public int[] cre;
	
	public static void main(String[] args) throws Exception {
		ProblemaP1 instancia = new ProblemaP1();
		try ( 
			InputStreamReader is= new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(is);
		) { 
			String line = br.readLine();
			int casos = Integer.parseInt(line);
			line = br.readLine();
			
			for(int i=0;i<casos && line!=null && 
					line.length()>0 && !"0".equals(line);i++) {
				final String [] dataStr = line.split(" ");
				final int[] numeros = Arrays.stream(dataStr).mapToInt(f->
				Integer.parseInt(f)).toArray();
				final int nCeldas = numeros[0];
				final int energiaTotal = numeros[1];
				final int[] creatividad = Arrays.copyOfRange(numeros, 2, 7); 
				ExecutorService executor = Executors.newSingleThreadExecutor();
		            Future<Integer> future = executor.submit(() -> {
		                return instancia.MaxCreatividad(nCeldas, energiaTotal, creatividad);
		            });
				int respuesta;
	            try {
	                respuesta = future.get(180, TimeUnit.SECONDS);
	                System.out.println(respuesta);
	            } catch (TimeoutException e) {
	                future.cancel(true);
	                respuesta = NumCreatividad(energiaTotal,creatividad);
	                System.out.println(respuesta);
	            } finally {
	                executor.shutdownNow();
	            }
	         

	            line = br.readLine();
	        }
			
		}
	}
	
    public int MaxCreatividad(int k, int n, int[] c) {
    	cre = new int[n + 1];
       
        
        for (int num = 0; num <= n; num++) {
            cre[num] = NumCreatividad(num, c);
        }
        
        int[][] dp = new int[k + 1][n + 1];
        
        for (int j = 0; j <= n; j++) {
            dp[1][j] = cre[j]; 
        }
        
        for (int i = 2; i <= k; i++) {
            for (int j = 0; j <= n; j++) {
     
                int maxVal = dp[i][j];
                for (int x = 0; x <= j; x++) {
                    if (dp[i-1][j-x] + cre[x] > maxVal) {
                        maxVal = dp[i-1][j-x] + cre[x];
                    }
      
                    if (x > 1000 && cre[x] < maxVal / 100) break;
                }
                dp[i][j] = maxVal;
            }
        }
        
        return dp[k][n];
    }
    
    // Se halla la creatividad de un número en especifico.
    public static int NumCreatividad(int num, int[] c) {
         
        if (num < 0 || num > 100000) {
            throw new IllegalArgumentException("El número debe estar entre 0 y 100000");
        }
        int r = 0;
        int temp = num;
        
        for (int i = 0; i < 5 && temp > 0; i++) {
            int d = temp % 10;
            temp /= 10;
            if (d == 3) r += c[i];
            else if (d == 6) r += 2 * c[i];
            else if (d == 9) r += 3 * c[i];
        }
        return r;
    }	
}
