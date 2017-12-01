package br.com.sisinfra.automacao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class ExecutaComando {

	
	public static void main(String[] args) {
		
		System.out.println("TESTE");
	}
//
//		public static void main(String[] args) {
//			try {
//				String line;
//
//				// (System.getenv("windir") = pega o diretório de instalacao do windows
//				// Runtime executa um comando no windows (O mesmo utilizado no prompt do MSDOS)
//				// metodo exec colocar o comando que deseja executar
//				Process p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe -v");
//
//				// Coleta o retorno da execucao do comando 
//				BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream(), Charset.forName("ISO-8859-1")));
//
//				// Faz um loop para cada linha do retorno da execucao
//				while ((line = input.readLine()) != null) {
//					// Mostra o retorno do comando linha por linha, podendo
//					// implementar alguma regra aqui
//					System.out.println(line);
//				}
//
//				// Fecha stream
//				input.close();
//				
//			} catch (Exception err) {
//				err.printStackTrace();
//			}
//
//		}
	
}
