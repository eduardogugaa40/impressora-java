# Projeto Java – Integração com Impressora Elgin via DLL (JNA)

Este projeto demonstra como realizar a comunicação entre uma aplicação Java e a impressora Elgin por meio de uma DLL nativa, utilizando a biblioteca JNA (Java Native Access).
Ele inclui funções de conexão, impressão de texto, QRCode, código de barras, XML SAT, sinal sonoro, avanço de papel e corte.

Funcionalidades

O programa possui um menu interativo no terminal, permitindo executar:

🔧 Conexão

Configurar conexão com a impressora

Abrir conexão

Fechar conexão

🖨️ Impressões

Impressão de texto

Impressão de QRCode

Impressão de código de barras

Impressão de XML SAT

🔔 Outras Funções

Avançar papel

Corte de papel

Estrutura do Projeto
Main.java
|
|-- Interface ImpressoraDLL
|       - Mapeia todas as funções da DLL usando JNA
|
|-- Menu principal com opções de impressão e controle da impressora

🧩 Dependências

Você precisará instalar:

✔️ JNA (Java Native Access)

Adicione ao seu projeto:

Maven

<dependency>
    <groupId>net.java.dev.jna</groupId>
    <artifactId>jna</artifactId>
    <version>5.13.0</version>
</dependency>


Ou baixe o JAR manualmente:
JNA no Maven Central

✔️ DLL da Impressora Elgin

A DLL utilizada no código:

C:\Users\...\E1_Impressora01.dll


Certifique-se de ajustar o caminho da DLL para o seu ambiente.

⚙️ Configuração da Conexão

A configuração atual utiliza:

tipo = 1;
modelo = "i9";
conexao = "USB";
parametro = 0;


Modifique conforme necessário para:

Serial

TCP/IP

Diferentes modelos de impressoras Elgin

▶️ Como Executar

Instale o JDK 11+

Inclua a biblioteca JNA

Ajuste o caminho da DLL no código

Compile:

javac Main.java


Execute:

java Main

🧭 Menu do Sistema

Ao executar, será apresentado:

1  - Configurar Conexao
2  - Abrir Conexao
3  - Impressao Texto
4  - Impressao QRCode
5  - Impressao Cod Barras
6  - Impressao XML SAT
7  - Impressao XML Canc SAT
8  - Abrir Gaveta Elgin
9  - Abrir Gaveta
10 - Sinal Sonoro
0  - Fechar Conexao e Sair

⚠️ Observações Importantes

Lembre-se de sempre abrir a conexão antes de tentar imprimir.

O programa automaticamente avança papel e corta após cada impressão.

O menu atual ainda possui opções sem implementação completa (ex.: XML Cancelamento e gaveta).

O uso da DLL requer permissões adequadas do Windows.

🛠️ Melhorias Futuras

Implementar as funções de XML Cancelamento SAT e abertura de gaveta

Criar interface gráfica (JavaFX ou Swing)

Adicionar testes automatizados

Parametrizar modelos e tipos de conexão via arquivo externo

📄 Licença

Este projeto pode ser utilizado livremente para fins educacionais e integração com impressoras Elgin.
Sinal sonoro

Abertura de gaveta (Elgin e padrão)
