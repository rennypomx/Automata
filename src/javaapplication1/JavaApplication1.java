/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;
import java.io.BufferedReader; 
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.IOException; 
import java.util.concurrent.ExecutionException;
/**
 *
 * @author renny
 */
public class JavaApplication1 {
        static int estado = 0;

    
    public static String muestraContenido(String archivo) throws FileNotFoundException, IOException { 
    	String cadena; 
        FileReader f = new FileReader(archivo); 
        BufferedReader b = new BufferedReader(f); 
        while((cadena = b.readLine())!=null) { 
        	System.out.println(cadena); 
                return(cadena);
        } 
        b.close(); 
        return(null);
	} 
    
    
   /* public static void main(String[] args) throws IOException {
    	String codigo = muestraContenido("C:\\Users\\renny\\OneDrive\\Documentos\\datos.txt"); 
        char[] aCaracteres = codigo.toCharArray();
        System.out.println(aCaracteres.length);
        
        for (char aCaractere : aCaracteres) {
            System.out.println(aCaractere);
            switch (estado) {
                case 0:
                    
                    if(Character.isLetter(aCaractere) ){
                        estado = 1;
                        System.out.println("Es letra");
                        
                    }else{
                        System.out.println("Es digito");
                     estado = 2;
                        
                    }
        
                    break;
                    
                case 1:
                     if(Character.isLetter(aCaractere) ){
                        estado = 0;
                        System.out.println("Es letra");
                       
                               
                        
                    }else{
                    throw new Error("No es reconocido");
                    }
                   
                    break;
                        
                        
                
                    
                default:
                    throw new AssertionError();
            }
            
        }*/
public static void main(String[] args) throws IOException {
    String codigo = muestraContenido("C:\\Users\\renny\\OneDrive\\Documentos\\datos.txt"); 
    char[] aCaracteres = codigo.toCharArray();
    System.out.println(aCaracteres.length);
    int estado = 0;
    for (char aCaractere : aCaracteres) {
        System.out.println(aCaractere);
        switch (estado) {
            case 0:
                if(Character.isLetter(aCaractere)){
                    estado = 1;
                    System.out.println("Es letra");
                }else if(Character.isDigit(aCaractere)){
                    estado = 2;
                    System.out.println("Es digito");
                }else if(aCaractere == '{' || aCaractere == '}'){
                    estado = 3;
                    System.out.println("Es llave");
                }else{
                    System.out.println("Caracter no reconocido");
                    estado = 0;
                }
                break;
            case 1:
                if(Character.isLetter(aCaractere)){
                    estado = 1;
                    System.out.println("Es letra");
                }else{
                    System.out.println("Fin de secuencia de letras");
                    estado = 0;
                }
                break;
            case 2:
                if(Character.isDigit(aCaractere)){
                    estado = 2;
                    System.out.println("Es digito");
                }else{
                    System.out.println("Fin de secuencia de digitos");
                    estado = 0;
                }
                break;
            case 3:
                if(aCaractere == '{' || aCaractere == '}'){
                    estado = 3;
                    System.out.println("Es llave");
                }else{
                    System.out.println("Fin de secuencia de llaves");
                    estado = 0;
                }
                break;
            default:
                throw new AssertionError();
        }
    }
    }
    
}
    