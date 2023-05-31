/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        while ((cadena = b.readLine()) != null) {
            System.out.println(cadena);
            return (cadena);
        }
        b.close();
        return (null);
    }

    public static boolean containsCaracter(char[] array, char caracter) {
        for (char c : array) {
            if (c == caracter) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        String codigo = muestraContenido("C:\\Users\\H P\\Documents\\utpl\\ciclo6\\automatas\\file.txt");
        char[] aCaracteres = codigo.toCharArray();
        int estado = 0;
        String ins = "";
       

        List<Character> sep = new ArrayList<>();
        sep.add(' ');
        sep.add(';');
        sep.add(',');
        
        List<Character> ide = new ArrayList<>();
        ide.add('$');
        ide.add('#');
        ide.add('&');

        List<Character> grupos = new ArrayList<>();
        grupos.add('{');
        grupos.add('}');
        grupos.add('(');
        grupos.add(')');

        List<Character> operadores = new ArrayList<>();
        operadores.add('+');
        operadores.add('-');
        operadores.add('*');
        operadores.add('/');

        List<Character> relacionales = new ArrayList<>();
        relacionales.add('<');
        relacionales.add('>');
        relacionales.add(':');
        relacionales.add('!');

        for (char aCaractere : aCaracteres) {
            // System.out.println(aCaractere);
            switch (estado) {
                case 0:
                    // automata para palabras reservadas
                    if (Character.isLetter(aCaractere)) {
                        estado = 1;
                        //System.out.println("Es letra");

                        ins += String.valueOf(aCaractere);

                        // automara para identificadores    
                    } else if (ide.contains(aCaractere)) {
                        estado = 2;
                        

                        ins += String.valueOf(aCaractere);
                        // automata para numeros    
                    } else if (Character.isDigit(aCaractere)) {
                        estado = 4;
                        ins += String.valueOf(aCaractere);

                        // automata grupos ( "(", ")", "{2, "}" )
                    } else if (grupos.contains(aCaractere)) {
                        estado = 7;
                        ins += String.valueOf(aCaractere);

                        // automata operadores ( "+", "-", "*, "/" )
                    } else if (operadores.contains(aCaractere)) {
                        estado = 8;
                        ins += String.valueOf(aCaractere);

                        // automata relacionales ( <, >, ==, != )
                    } else if (relacionales.contains(aCaractere)) {
                        estado = 9;
                        ins += String.valueOf(aCaractere);

                    } else if (aCaractere == '=') {
                        estado = 10;
                        ins += String.valueOf(aCaractere);

                    } else if (aCaractere == '!') {
                        estado = 11;
                        ins += String.valueOf(aCaractere);

                    }  else if (sep.contains(aCaractere)) {
                        estado = 0;
                        ins = "";

                    }else {
                        System.out.println("El caracter no se reconoce. general" + ins+"  "+ aCaractere);
                        System.exit(41); // 
                    }
                    break;

                // automata de palabras reservadas
                case 1:
                    List<Character> separadores = new ArrayList<>();
                    separadores.add(';');
                    separadores.add(' ');
                    separadores.add(':');
                    if (Character.isLetter(aCaractere)) {
                        estado = 1;
                        ins += String.valueOf(aCaractere);
                        // System.out.println("Es letra");
                    } else if (separadores.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(palabra reservada): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. ca1" + ins);
                        System.exit(1); // 
                    }
                    break;

                case 2:

                    if (Character.isLetter(aCaractere)) {
                        ins += aCaractere;
                        estado = 3;

                    } else {
                        System.out.println("El caracter no se reconoce. c2" + ins);
                        System.exit(1); // 
                    }
                    break;

                case 3:
                    List<Character> sepId = new ArrayList<>();
                    sepId.add(';');
                    sepId.add(' ');
                    sepId.add(':');

                    if (Character.isLetter(aCaractere)) {
                        ins += aCaractere;
                        estado = 3;

                    } else if (sepId.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(identificador): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. c3" + ins);
                        System.exit(1); // 
                    }

                    break;
                case 4:

                    List<Character> sepNumeros = new ArrayList<>();
                    sepNumeros.add(';');
                    sepNumeros.add(' ');
                    sepNumeros.add(':');

                    if (Character.isDigit((aCaractere))) {
                        estado = 4;
                        ins += aCaractere;

                    } else if (aCaractere == '.') {
                        estado = 5;
                        ins += aCaractere;

                    } else if (sepNumeros.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(numero): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. c4" + ins);
                        System.exit(1); // 
                    }
                    
                    break;

                case 5:
                    if (Character.isDigit(aCaractere)) {
                        ins += aCaractere;
                        estado = 6;
                    } else {
                        System.out.println("El caracter no se reconoce. c5" + ins);
                        System.exit(1); // 
                    }

                    break;
                case 6:
                    List<Character> sepNumeros2 = new ArrayList<>();
                    sepNumeros2.add(';');
                    sepNumeros2.add(' ');
                    sepNumeros2.add(':');

                    if (Character.isDigit(aCaractere)) {
                        estado = 6;
                        ins += aCaractere;

                    } else if (sepNumeros2.contains((aCaractere))) {
                        System.out.println("La sintaxis analizada es(numero): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. c6" + ins);
                        System.exit(1); // 
                    }
                    
                    break;

                case 7:
                    List<Character> sepGrupos = new ArrayList<>();
                    sepGrupos.add(';');
                    sepGrupos.add(' ');
                    sepGrupos.add(':');
                    if (sepGrupos.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(grupos): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. grupos c7" + ins);
                        System.exit(1); // 
                    }
                    break;

                case 8:
                    List<Character> sepOp = new ArrayList<>();
                    sepOp.add(';');
                    sepOp.add(' ');
                    sepOp.add(':');
                    if (sepOp.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(operadores): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. operadores c8" + ins);
                        System.exit(1); // 
                    }

                case 9:
                    List<Character> seprel = new ArrayList<>();
                    seprel.add(';');
                    seprel.add(' ');
                    seprel.add(':');
                    if (seprel.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(relacionales): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. relacionales c9" + ins);
                        System.exit(1); // 
                    }
                    break;
                case 10:
                    List<Character> seigual = new ArrayList<>();
                    seigual.add(';');
                    seigual.add(' ');
                    seigual.add(':');
                    if (seigual.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(igual): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. relacionales c 10" + ins);
                        System.exit(1); // 
                    }

                    break;
                case 11:
                    List<Character> secoma = new ArrayList<>();
                    secoma.add(';');
                    secoma.add(' ');
                    secoma.add(':');
                    if (secoma.contains(aCaractere)) {
                        System.out.println("La sintaxis analizada es(coma): " + ins);
                        ins = "";
                        estado = 0;
                    } else {
                        System.out.println("El caracter no se reconoce. relacionales c11" + ins);
                        System.exit(1); // 
                    }

                    break;
                default:
                // throw new AssertionError();
            }
        }
    }

}
