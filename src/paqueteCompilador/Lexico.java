/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paqueteCompilador;

/**
 *
 * @author David de la Luz
 */
public class Lexico {

    String lexema;
    String nombre;
    int numero;

    String diccionario[][] = {
        // Palabras reservadas
        {"break", "Palabra reservada", "1"},
        {"default", "Palabra reservada", "2"},
        {"func", "Palabra reservada", "3"},
        {"interface", "Palabra reservada", "4"},
        {"select", "Palabra reservada", "5"},
        {"case", "Palabra reservada", "6"},
        {"defer", "Palabra reservada", "7"},
        {"go", "Palabra reservada", "8"},
        {"map", "Palabra reservada", "9"},
        {"struct", "Palabra reservada", "10"},
        {"chan", "Palabra reservada", "11"},
        {"else", "Palabra reservada", "12"},
        {"goto", "Palabra reservada", "13"},
        {"package", "Palabra reservada", "14"},
        {"switch", "Palabra reservada", "15"},
        {"const", "Palabra reservada", "16"},
        {"fallthrough", "Palabra reservada", "17"},
        {"if", "Palabra reservada", "18"},
        {"range", "Palabra reservada", "19"},
        {"type", "Palabra reservada", "20"},
        {"continue", "Palabra reservada", "21"},
        {"for", "Palabra reservada", "22"},
        {"import", "Palabra reservada", "23"},
        {"return", "Palabra reservada", "24"},
        {"var", "Palabra reservada", "25"},
        {"main", "Palabra reservada función principal", "26"},

        // Tipos de datos
        {"int", "Tipo de dato entero", "27"},
        {"int8", "Tipo de dato entero", "28"},
        {"int16", "Tipo de dato entero", "29"},
        {"int32", "Tipo de dato entero", "30"},
        {"int64", "Tipo de dato entero", "31"},
        {"uint", "Tipo de dato entero sin signo", "32"},
        {"uint8", "Tipo de dato entero sin signo", "33"},
        {"uint16", "Tipo de dato entero sin signo", "34"},
        {"uint32", "Tipo de dato entero sin signo", "35"},
        {"uint64", "Tipo de dato entero sin signo", "36"},
        {"float32", "Tipo de dato flotante", "37"},
        {"float64", "Tipo de dato flotante", "38"},
        {"complex64", "Tipo de dato complejo", "39"},
        {"complex128", "Tipo de dato complejo", "40"},
        {"byte", "Alias uint8", "41"},
        {"rune", "Alias int32", "42"},
        {"string", "Tipo de dato cadena", "43"},
        {"bool", "Tipo de dato booleano", "44"},
        {"true", "Valor booleano verdadero", "45"},
        {"false", "Valor booleano falso", "46"},

        // Operadores
        {"+", "Operador aritmético", "47"},
        {"-", "Operador aritmético", "48"},
        {"*", "Operador aritmético", "49"},
        {"/", "Operador aritmético", "50"},
        {"%", "Operador aritmético", "51"},
        {"++", "Incremento", "52"},
        {"--", "Decremento", "53"},
        {"==", "Operador relacional", "54"},
        {"!=", "Operador relacional", "55"},
        {"<", "Operador relacional", "56"},
        {">", "Operador relacional", "57"},
        {"<=", "Operador relacional", "58"},
        {">=", "Operador relacional", "59"},
        {"&&", "Operador lógico", "60"},
        {"||", "Operador lógico", "61"},
        {"!", "Negación lógica", "62"},
        {"=", "Asignación", "63"},
        {":=", "Asignación corta", "64"},
        {"+=", "Asignación compuesta", "65"},
        {"-=", "Asignación compuesta", "66"},
        {"*=", "Asignación compuesta", "67"},
        {"/=", "Asignación compuesta", "68"},
        {"%=", "Asignación compuesta", "69"},
        {"&", "Operador bit a bit", "70"},
        {"|", "Operador bit a bit", "71"},
        {"^", "Operador bit a bit", "72"},
        {"<<", "Operador bit a bit", "73"},
        {">>", "Operador bit a bit", "74"},
        {"&^", "Operador bit a bit", "75"},

        // Símbolos especiales
        {"(", "Paréntesis que abre", "76"},
        {")", "Paréntesis que cierra", "77"},
        {"[", "Corchete que abre", "78"},
        {"]", "Corchete que cierra", "79"},
        {"{", "Llave que abre", "80"},
        {"}", "Llave que cierra", "81"},
        {",", "Coma", "82"},
        {";", "Punto y coma", "83"},
        {".", "Punto", "84"},
        {":", "Dos puntos", "85"},
        // Nuevos tokens fuera de Go estándar
        {"Decimal", "Número decimal", "86"},
        {"Char", "Caracter individual", "87"},
        {"RawString", "Cadena multilínea (raw string)", "88"},
        {"ComentarioLinea", "Comentario de una línea", "89"},
        {"ComentarioBloque", "Comentario de bloque", "90"}
    };


    public Lexico Etiquetar(String palabra) {
        Lexico objLexico = new Lexico();
        objLexico.lexema = palabra;
        
        
        // --- Detectar comentarios primero --- (ESTA PARTE YA LA TIENES BIEN)
        if (palabra.startsWith("//")) { // Comentario de línea
            objLexico.nombre = "ComentarioLinea";
            objLexico.numero = 89;
            return objLexico; // Salimos inmediatamente, no procesamos más
        }

        if (palabra.startsWith("/*") && palabra.endsWith("*/")) { // Comentario de bloque
            objLexico.nombre = "ComentarioBloque";
            objLexico.numero = 90;
            return objLexico; // Salimos inmediatamente
        }
        

        // 1️ Revisar diccionario
        boolean bandera = false;
        int index = 0;
        while (index < objLexico.diccionario.length) {
            if (palabra.equals(diccionario[index][0])) {
                bandera = true;
                objLexico.nombre = diccionario[index][1];
                objLexico.numero = Integer.parseInt(diccionario[index][2]);
                break;
            }
            index++;
        }
        if (bandera) {
            return objLexico;
        }

        // 2️⃣ Autómata extendido: Decimal, Char, RawString y Comentarios
        boolean esDecimal = false;
        boolean esChar = false;
        boolean esRawString = false;
        

        if (palabra.matches("\\d+\\.\\d+")) { // decimal, ej: 3.14
            objLexico.nombre = "Decimal";
            objLexico.numero = 86;
            esDecimal = true;
        } else if (palabra.matches("'(.)'")) { // char, ej: 'a'
            objLexico.nombre = "Char";
            objLexico.numero = 87;
            esChar = true;
        } else if (palabra.startsWith("`") && palabra.endsWith("`")) { // raw string
            objLexico.nombre = "RawString";
            objLexico.numero = 88;
            esRawString = true;
        } 
        
        

        // 3️⃣ Si no es ninguno de los anteriores, ejecutar matriz de transiciones
        if (!esDecimal && !esChar && !esRawString) {
            int matTransiciones[][] = {
                {2,1,-1,4},
                {-1,1,-1,-1},
                {3,2,2,2},
                {-1,-1,-1,-1},
                {-1,4,-1,4},
            };
            String nombres[] = {"errorDesconocido", "Entero", "errorCadena", "Cadena", "Variable"};
            int numeros[] = {100, 50, 102, 51, 52};
            String posiblesErrores[] = {"errorDesconocido", "errorNumero", "errorCadena", "errorCadena", "errorVariable"};
            int numerosError[] = {100, 101, 102, 102, 104};

            int estado = 0;
            int pos = 0;
            char arrCar[] = palabra.toCharArray();
            boolean bandera2 = true;

            for (int i = 0; i < arrCar.length; i++) {
                if (arrCar[i] == '"') {
                    pos = 0;
                } else if (Character.isDigit(arrCar[i])) {
                    pos = 1;
                } else if (isPI(arrCar[i])) {
                    pos = 2;
                } else if (Character.isUpperCase(arrCar[i]) || Character.isLowerCase(arrCar[i])) {
                    pos = 3;
                } else {
                    bandera2 = false;
                    break;
                }

                if (matTransiciones[estado][pos] != -1) {
                    estado = matTransiciones[estado][pos];
                } else {
                    bandera2 = false;
                    break;
                }
            }

            if (bandera2) {
                objLexico.nombre = nombres[estado];
                objLexico.numero = numeros[estado];

                // Validación adicional: si es Variable, debe iniciar con letra
                if (objLexico.nombre.equals("Variable")) {
                    char first = palabra.charAt(0);
                    if (!Character.isLetter(first)) {
                        objLexico.nombre = "errorIdentificador";
                        objLexico.numero = 105;
                    }
                }
            } else {
                objLexico.nombre = posiblesErrores[estado];
                objLexico.numero = numerosError[estado];
            }
        }

        return objLexico;
    }


    public boolean isPI(char caracter) {
        char[] PI = {
             '%',  '[', ']',  '_', '!', '´','\'','=','@','?','¿'
        };
        boolean bandera = false;

        for (int i = 0; i < PI.length; i++) {
            if (caracter == PI[i]) {
                bandera = true;
            }
        }
        return bandera;
    }
}
