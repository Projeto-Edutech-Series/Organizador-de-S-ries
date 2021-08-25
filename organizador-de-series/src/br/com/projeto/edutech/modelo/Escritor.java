package br.com.projeto.edutech.modelo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 * Classe usada para escrever as informa��es fornecidas pelo usu�rio em um arquivo txt.
 * @author Jo�o Gabriel N Silva
 */
public class Escritor {
	
	private String status;
	private String nome;
	private int temporadas;
	private int episodios;
	
	
	/**
	 * Adiciona a s�rie com as informa��es fornecidas nos campos de texto e RadioButton a um arquivo txt.
	 * 
	 * @param campoNome Campo de texto do nome da s�rie.
	 * @param campoTemporadas Campo de texto do n�mero de temporadas.
	 * @param campoEpisodios Campo de texto do n�mero de epis�dios.
	 * @param grupoAlternativas Grupo de bot�es com os bot�es de status.
	 * 
	 * @return Retorna true se passar pelo verifica��o e a s�rie for adicionada sem problemas.
	 */
	public boolean adiciona(JTextField campoNome, JTextField campoTemporadas, JTextField campoEpisodios, ButtonGroup grupoAlternativas) {
		try {
			if(verificaInformacoes(campoNome, campoTemporadas, campoEpisodios, grupoAlternativas)) {
				return false;
			}
			
	        FileWriter writer;

	        try {
	            writer = new FileWriter("series.txt", true);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }

	        PrintWriter saida = new PrintWriter(writer, true);
	        saida.println(nome + ";" + temporadas + ";" + episodios + ";" + status);	        
	        saida.close();
	        writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * Faz a verifica��oo das informa��es recebidas dos campos de texto e RadioButton.
	 * 
	 * @param campoNome Campo de texto do nome da s�rie.
	 * @param campoTemporadas Campo de texto do n�mero de temporadas.
	 * @param campoEpisodios Campo de texto do n�mero de epis�dios.
	 * @param grupoAlternativas Grupo de bot�es com os bot�es de status.
	 * 
	 * @return Retorna true se houver algo de errado com os dados fornecidos. Por exemplo nome em branco, ou uma letra 
	 * onde deveria ser um n�mero.
	 */
	private boolean verificaInformacoes(JTextField campoNome, JTextField campoTemporadas, JTextField campoEpisodios, ButtonGroup grupoAlternativas) {		
		if(!campoNome.getText().strip().isEmpty()) {
			nome = campoNome.getText().strip();
			
		} else {
			JOptionPane.showMessageDialog(null, "Nenhum nome informado", mudaEncode("Informa��o n�o fornecida"), 1);
			campoNome.setText(null);
			return true;
		}
				// a verifica��o do status precisa ser refeita
//		grupoAlternativas.getElements().asIterator().forEachRemaining(botao -> {
//			System.out.println(botao.getClass());
//			if(botao.isSelected()) {
//				status = botao.getText();
//			} else {
//				
//				JOptionPane.showMessageDialog(null, "Nenhum status selecionado", mudaEncode("Informa��o n�o fornecida"), 1);
//				return;
//			}		
//		});

		
		if(!campoTemporadas.getText().strip().isEmpty()) {
			try {
				temporadas = Integer.parseInt(campoTemporadas.getText().strip());							
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, mudaEncode("O campo 'temporadas' deve conter um n�mero"), mudaEncode("Informa��o incorreta"), 1);
				campoTemporadas.setText(null);
			}
		} else {
			JOptionPane.showMessageDialog(null, mudaEncode("N�mero de temporadas n�o informado"), mudaEncode("Informa��o n�o fornecida"), 1);
			campoTemporadas.setText(null);
			return true;
		}
		
		if(!campoEpisodios.getText().strip().isEmpty()) {
			try {
				episodios = Integer.parseInt(campoEpisodios.getText().strip());							
			} catch(Exception e) {
				JOptionPane.showMessageDialog(null, mudaEncode("O campo 'epis�dios' deve conter um n�mero"), mudaEncode("Informa��o incorreta"), 1);
				campoEpisodios.setText(null);
			}
		} else {		
			JOptionPane.showMessageDialog(null, mudaEncode("N�mero de epis�dios n�o informado"), mudaEncode("Informac�o n�o fornecida"), 1);
			campoEpisodios.setText(null);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Muda o encode da String recebida para UTF-8.
	 * 
	 * @param str String que tera seu encode alterado.
	 */
	public String mudaEncode(String str) {
		return new String(str.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
	}
}

