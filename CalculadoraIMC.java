import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CalculadoraIMC {

    private static final double IMC_FEM_NORMAL_MIN = 19.0;
    private static final double IMC_FEM_OBESIDADE_LEVE_MIN = 24.0;
    private static final double IMC_FEM_OBESIDADE_MODERADA_MIN = 29.0;
    private static final double IMC_FEM_OBESIDADE_MORBIDA_MIN = 39.0;

    private static final double IMC_MASC_NORMAL_MIN = 20.0;
    private static final double IMC_MASC_OBESIDADE_LEVE_MIN = 25.0;
    private static final double IMC_MASC_OBESIDADE_MODERADA_MIN = 30.0;
    private static final double IMC_MASC_OBESIDADE_MORBIDA_MIN = 40.0;

    public static void main(String[] args) {
        try (Scanner lerTeclado = new Scanner(System.in)) {

            limparTela();
            System.out.print("Digite seu nome: ");
            String nome = lerTeclado.nextLine();
            
            System.out.print("Digite sua idade: ");
            int idade = lerTeclado.nextInt();
            lerTeclado.nextLine(); // Consome a quebra de linha

            System.out.print("Digite seu gênero (M/F): ");
            char genero = Character.toUpperCase(lerTeclado.nextLine().charAt(0));
            
            System.out.print("Digite sua altura em metros (ex: 1.75): ");
            double altura = lerTeclado.nextDouble();

            System.out.print("Digite seu peso em kg (ex: 70.5): ");
            double peso = lerTeclado.nextDouble();

            limparTela();
            
            double imc = calcularIMC(peso, altura);
            String classificacao = classificarIMC(imc, genero);

            System.out.println("--- Resultado ---");
            System.out.println("Nome: " + nome);
            System.out.println("Idade: " + idade + " anos");
            System.out.println("Gênero: " + genero);
            System.out.printf("IMC: %.2f\n", imc);
            System.out.println("Classificação: " + classificacao);
            System.out.println("-----------------");

        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. Por favor, digite valores numéricos para altura e peso.");
        } catch (IOException | InterruptedException e) {
            System.err.println("Ocorreu um erro ao tentar limpar a tela.");
        }
    }

    private static double calcularIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private static String classificarIMC(double imc, char genero) {
        if (genero == 'M') {
            return classificarMasculino(imc);
        } else if (genero == 'F') {
            return classificarFeminino(imc);
        } else {
            return "Gênero inválido!";
        }
    }

    private static String classificarMasculino(double imc) {
        if (imc >= IMC_MASC_OBESIDADE_MORBIDA_MIN) {
            return "Obesidade Mórbida";
        } else if (imc >= IMC_MASC_OBESIDADE_MODERADA_MIN) {
            return "Obesidade Moderada";
        } else if (imc >= IMC_MASC_OBESIDADE_LEVE_MIN) {
            return "Obesidade Leve";
        } else if (imc >= IMC_MASC_NORMAL_MIN) {
            return "Normal";
        } else {
            return "Abaixo do Normal";
        }
    }

    private static String classificarFeminino(double imc) {
        if (imc >= IMC_FEM_OBESIDADE_MORBIDA_MIN) {
            return "Obesidade Mórbida";
        } else if (imc >= IMC_FEM_OBESIDADE_MODERADA_MIN) {
            return "Obesidade Moderada";
        } else if (imc >= IMC_FEM_OBESIDADE_LEVE_MIN) {
            return "Obesidade Leve";
        } else if (imc >= IMC_FEM_NORMAL_MIN) {
            return "Normal";
        } else {
            return "Abaixo do Normal";
        }
    }
    
    private static void limparTela() throws IOException, InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
    }
}