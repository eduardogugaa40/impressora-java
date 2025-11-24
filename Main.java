import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.FileInputStream;

public class Main {

    // Interface que representa a DLL, usando JNA
    public interface ImpressoraDLL extends Library {

        // Caminho completo para a DLL
        ImpressoraDLL INSTANCE = (ImpressoraDLL) Native.load(
                "C:\\Users\\eduardo_silva02\\Desktop\\Java-Aluno Graduacao\\E1_Impressora01.dll",
                ImpressoraDLL.class
        );


        private static String lerArquivoComoString(String path) throws IOException {
            FileInputStream fis = new FileInputStream(path);
            byte[] data = fis.readAllBytes();
            fis.close();
            return new String(data, StandardCharsets.UTF_8);
        }


        int AbreConexaoImpressora(int tipo, String modelo, String conexao, int param);
        int FechaConexaoImpressora();
        int ImpressaoTexto(String dados, int posicao, int estilo, int tamanho);
        int Corte(int avanco);
        int ImpressaoQRCode(String dados, int tamanho, int nivelCorrecao);
        int ImpressaoCodigoBarras(int tipo, String dados, int altura, int largura, int HRI);
        int AvancaPapel(int linhas);
        int StatusImpressora(int param);
        int AbreGavetaElgin();
        int AbreGaveta(int pino, int ti, int tf);
        int SinalSonoro(int qtd, int tempoInicio, int tempoFim);
        int ModoPagina();
        int LimpaBufferModoPagina();
        int ImprimeModoPagina();
        int ModoPadrao();
        int PosicaoImpressaoHorizontal(int posicao);
        int PosicaoImpressaoVertical(int posicao);
        int ImprimeXMLSAT	(String dados, int param);
        int ImprimeXMLCancelamentoSAT(String dados, String assQRCode, int param);
    }

    private static boolean conexaoAberta = false;
    private static int tipo;
    private static String modelo;
    private static String conexao;
    private static int parametro;

    private static final Scanner scanner = new Scanner(System.in);

    private static String capturarEntrada(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static void configurarConexao() {
        System.out.println("--- CONFIGURAÇÃO DE CONEXÃO ---");

        tipo = 1;
        modelo = "i9";
        conexao = "USB";
        parametro = 0;
        System.out.println("Configuração salva!\n");
    }

    public static void abrirConexao() {
        if (conexaoAberta) {
            System.out.println("Conexão já está aberta.");
        } else {
            int resultado = ImpressoraDLL.INSTANCE.AbreConexaoImpressora(tipo,modelo,conexao,parametro);
            if(resultado == 0){
                conexaoAberta = true;
                System.out.println("Conexão aberta com sucesso!");

            }else{
                System.out.println("Erro ao abrir conexão. Código "+resultado);
            }

        }
    }

    public static void fecharConexao() {
        if (!conexaoAberta) {
            System.out.println("Nenhuma conexão para fechar.");
        } else {
            conexaoAberta = false;
            int resultado = ImpressoraDLL.INSTANCE.FechaConexaoImpressora();
            System.out.println("Conexão fechada com sucesso!");
        }
    }

    public static void impressaoTexto() {
        if(conexaoAberta){
            System.out.println("Digite um texto abaixo: ");
            String dados = scanner.nextLine();
            ImpressoraDLL.INSTANCE.ImpressaoTexto(dados,1,4,0);
        }else{
            System.out.println("A conexão não está aberta!");
        }
    }

    public static void corte() {
        int resultado = ImpressoraDLL.INSTANCE.Corte(2);
        if (resultado == 0){
            System.out.println("O corte foi realizado com sucesso!");
        }else{
            System.out.println("Houve um erro. Código: "+resultado);
        }
    }

    public static void impressaoQRCode() {
        if(conexaoAberta){
            String dados = scanner.nextLine();
            int resultado = ImpressoraDLL.INSTANCE.ImpressaoQRCode(dados,6,4);
            if (resultado != 0){
                System.out.println("Houve um erro. Código: "+resultado);
            }
        } else {
            System.out.println("A conexão não está aberta!");
        }
    }

    public static void avancaPapel() {
        int resultado = ImpressoraDLL.INSTANCE.AvancaPapel(2);
        if (resultado != 0){
            System.out.println("Houve um erro. Código: "+resultado);
        }
    }

    public static void sinalSonoro() {
        int resultado = ImpressoraDLL.INSTANCE.SinalSonoro(4,50,5);
        if (conexaoAberta) {
            if (resultado != 0){
                System.out.println("Houve um erro. Código: "+resultado);
            }
        } else {
            System.out.println("A conexão não está aberta!");
        }
    }

    public static void impressaoCodBarra() {
        if (conexaoAberta) {
            String dados = scanner.nextLine();
            int resultado = ImpressoraDLL.INSTANCE.ImpressaoCodigoBarras(8, dados, 100, 2, 3);
            if (resultado != 0) {
                System.out.println("Houve um erro. Código: "+resultado);
            }
        } else {
            System.out.println("A conexão não está aberta!");
        }
    }

    public static void impressaoXMLSAT() {
        if (conexaoAberta) {
            System.out.println("Digite o conteudo do XML: ");
            String dados = scanner.nextLine();
            System.out.println("Digite o parametro que modifica o cupom a ser impresso: ");
            int param = scanner.nextInt();
            int resultado = ImpressoraDLL.INSTANCE.ImprimeXMLSAT(dados, param);
            if (resultado != 0) {
                System.out.println("Houve um erro. Código: "+resultado);
            }
        } else {
            System.out.println("A conexão não está aberta!");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n*************************************************");
            System.out.println("**************** MENU IMPRESSORA *******************");
            System.out.println("*************************************************\n");

            System.out.println("1  - Configurar Conexao");
            System.out.println("2  - Abrir Conexao");
            System.out.println("3  - Impressao Texto");
            System.out.println("4  - Impressao QRCode");
            System.out.println("5  - Impressao Cod Barras");
            System.out.println("6  - Impressao XML SAT");
            System.out.println("7  - Impressao XML Canc SAT");
            System.out.println("8  - Abrir Gaveta Elgin");
            System.out.println("9  - Abrir Gaveta");
            System.out.println("10 - Sinal Sonoro");
            System.out.println("0  - Fechar Conexao e Sair");
            System.out.println("--------------------------------------");

            //String escolha = capturarEntrada("\nDigite a opção desejada: ");
            System.out.println("Digite a opção desejada: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); //limpar o buffer
            if (escolha!=0) {
                switch (escolha) {
                    case 1:
                        configurarConexao();
                        break;
                    case 2:
                        abrirConexao();
                        break;
                    case 3:
                        impressaoTexto();
                        avancaPapel();
                        corte();
                        break;

                    case 4:
                        impressaoQRCode();
                        avancaPapel();
                        corte();
                        break;

                    case 5:
                        impressaoCodBarra();
                        avancaPapel();
                        corte();
                        break;

                    case 6:
                        impressaoXMLSAT();
                        avancaPapel();
                        corte();
                        break;

                    case 7:
                        break;

                    case 8:
                        break;

                    case 9:
                        break;

                    case 10:
                        sinalSonoro();
                        break;

                    default:
                        System.out.println("Número inválido. Selecione conforme o menu");
                }
            }else{
                fecharConexao();
            }
            System.out.println("Você saiu do sistema.");
            scanner.close();

        }

    }



}