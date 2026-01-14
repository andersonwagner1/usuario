package br.com.prefeitura.usuario.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;


public class LogInfo {
    private static final Logger LOGGER = Logger.getLogger(LogInfo.class.getName());

    private Long nrChamado;
    private Date dtAtualizacao;
    private String categoria;
    private String situacao;
    private String dado;

    public LogInfo(String processo, String descricao){    	
    	File file = new File("E:\\Documentos\\data.csv");
        //boolean arquivoVazio = file.exists() && file.length() == 0;

        try (FileWriter writer = new FileWriter(file, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Escrever os dados no formato CSV
            writer.append(String.valueOf(processo)).append(";");
            writer.append(dateFormat.format(new Date())).append(";");
            writer.append(descricao).append("\n");

            LOGGER.info("Dados gravados no arquivo info.csv");

        } catch (IOException e) {
            LOGGER.warning("Erro ao gravar o log: " + e.getMessage());
        }
    	
    }
   
    
   

    public LogInfo(String nrChamado,String categoria,	String situacao, String dado) {
    	if(nrChamado == null){
    		this.nrChamado = 0L;
    	}
		this.nrChamado = Long.parseLong(nrChamado);
		this.dtAtualizacao = new Date();
		this.categoria = categoria;
		this.situacao = situacao;
		this.dado = dado;
	}





	public void gravarLog() {
    	
    	
    	
        File file = new File("info.csv");
        boolean arquivoVazio = file.exists() && file.length() == 0;

        try (FileWriter writer = new FileWriter(file, true)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Escrever o cabeçalho se o arquivo estiver vazio
            if (arquivoVazio) {
                writer.append("Numero,Data,Categoria,Situacao,Resposta\n");
            }

            // Escrever os dados no formato CSV
            writer.append(String.valueOf(nrChamado)).append(";");
            writer.append(dateFormat.format(dtAtualizacao)).append(";");
            writer.append(categoria).append(";");
            writer.append(situacao).append(";");
            writer.append(dado).append("\n");

            LOGGER.info("Dados gravados no arquivo info.csv");

        } catch (IOException e) {
            LOGGER.warning("Erro ao gravar o log: " + e.getMessage());
        }
    }
}