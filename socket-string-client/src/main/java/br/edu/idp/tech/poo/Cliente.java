package br.edu.idp.tech.poo;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    //A1: 10%
    //Criou Eru

    public void iniciar(String endereco, int porta) {
        //Saida
        ObjectOutputStream saida;
        //Entrada
        ObjectInputStream entrada;
        Socket conexao;
        //Scanner para ler mensagem
        Scanner ler = new Scanner(System.in);

        String mensagem = "";
        //testar metodo para excecao
        try {
            //Socket para entender a conexao do projeto
            conexao = new Socket(endereco, porta);
            System.out.println("Conectado ao servidor " + endereco + ", na porta: " + porta);
            System.out.println("Digite: FIM para encerrar a conexao");

            // ligando as conexoes de saida e de entrada
            saida = new ObjectOutputStream(conexao.getOutputStream());
            saida.flush();
            entrada = new ObjectInputStream(conexao.getInputStream());


            //lendo a mensagem enviada pelo servidor
            mensagem = (String) entrada.readObject();
            System.out.println("Melkor>> "+mensagem);

            //enquanto nao for o fim da conversa, faz o ping pong
            do {
                System.out.println("Eru é você(Cliente) e Melkor é seu amigo(Servidor)");
                System.out.print("Eru::: ");
                mensagem = ler.nextLine();
                saida.writeObject(mensagem);
                saida.flush();
                System.out.println("A sua mensagem foi enviada para Melkor e ele respondeu: ");
                //lendo a mensagem enviada pelo servidor
                mensagem = (String) entrada.readObject();
                System.out.println(">> "+mensagem);
                System.out.println("A mensagem original foi: " + '"' +mensagem+ '"');
            } while (!mensagem.equals("FIM"));

            saida.close();
            entrada.close();
            conexao.close();

        } catch (Exception e) {
            System.err.println("erro: " + e.toString());
        }

    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: java tcp.Cliente <endereco-IP> <porta>");
            System.exit(1);
        }

        Cliente c = new Cliente();
        c.iniciar(args[0], Integer.parseInt(args[1]));
    }

}
