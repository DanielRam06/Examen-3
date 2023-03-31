import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Lector {
    // Ruta del archivo CSV y archivo de salida
    private String archivoCSV = "emails.csv";
    private String archivoSalida = "172020.txt";
    // Número de columnas
    private int numColumnas = 3002;
    private int[] sumas = new int[numColumnas];

    public void ejecutar(String id) {
        // Calcular filas de inicio y fin
        int filaInicio = Integer.parseInt(id.substring(3)) % 1000 -1; //restandole 1 de la fila de eneunciados
        int filaFin = filaInicio + 49;//TOMANDO la logica que la fila inicio ya es 1 + 49 = 50
        // Leer y escribir archivos
        try (BufferedReader lector = new BufferedReader(new FileReader(archivoCSV));
             BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoSalida))) {
            // Escribir título
            escritor.write("ESTE ES EL CONTEO DE PALABRAS EN LOS EMAILS PARA 50 FILAS TOMANDO EL ID");
            escritor.newLine();
            escritor.newLine();
            // Leer títulos de columnas
            String[] titulos = lector.readLine().split(",");
            String linea;
            // Procesar líneas del CSV
            for (int numFila = 0; (linea = lector.readLine()) != null; numFila++) {
                if (numFila >= filaInicio && numFila <= filaFin) {
                    // Procesar columnas y sumar valores
                    String[] columnas = linea.split(",");
                    for (int i = 0; i < columnas.length && i < numColumnas; i++) {
                        try {
                            // Cambiar a Integer.parseInt()
                            sumas[i] += Integer.parseInt(columnas[i]);
                        } catch (NumberFormatException e) {
                            // Ignorar si no se puede convertir a Integer
                        }
                    }
                }
            }
            // Escribir resultados
            for (int i = 0; i < titulos.length; i++) {
                escritor.write(String.format("%-20s", titulos[i]));
                if (i < sumas.length) {
                    // No es necesario redondear, ya que sumas[i] es un entero
                    escritor.write(String.format("%20d", sumas[i]));
                }
                escritor.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}