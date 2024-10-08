package br.com.germantech.utils;

public class ValidadorCPF {

    public static boolean isValid(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.equals("00000000000")) {
            return false;
        }
        return verificaDigitos(cpf);
    }

    private static boolean verificaDigitos(String cpf) {
        int soma = 0;
        int peso = 10;

        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito >= 10) {
            primeiroDigito = 0;
        }

        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
            return false;
        }

        soma = 0;
        peso = 11;

        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * peso--;
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito >= 10) {
            segundoDigito = 0;
        }

        return Character.getNumericValue(cpf.charAt(10)) == segundoDigito;
    }
}
