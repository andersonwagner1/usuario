package br.com.prefeitura.usuario.dao.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import br.com.prefeitura.usuario.enumerador.ETipoDados;

public class Oracle {
	private  String password ="";
	private  String user = "";
	private  String SERVER = "10.1.2.252";
	private  String PORT ="1521";
	private  String DATABESE="siap";
	private Connection connection;
	
	public Oracle(String user, String password){
		this.password = password;
		this.user = user;
	}
	
	public Oracle(String user, String password, String ip, String database){
		this.password = password;
		this.user = user;
		this.SERVER = ip;
		this.DATABESE = database;
	}
	
	
	public Integer execute(String sql) throws SQLException{
		if(connection == null){
			connection(user,password);
		}
		Statement stmt = connection.createStatement();		
		int x = stmt.executeUpdate (sql);
		stmt.close();
		return x;
	}
	
	public void close() throws SQLException{
		connection.close();
		connection = null;
		
	}
	
	public Connection conexao3 (){
		connection(user,password);
		return connection;
	}
	
	private Statement stmtRs;
	public ResultSet executeQuery(String sql) throws SQLException{
		if(connection == null){
			connection(user,password);
		}
		stmtRs = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet res = stmtRs.executeQuery(sql);
		
		
		return res;
	}
	
	
	private java.sql.Date converterPara(java.util.Date dataUtil){
		
		  java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());
		  
		  return dataSql;
	}
	
	private java.sql.Date converterPara(String dataString){
			
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        try {
            // Parsing da string para um objeto java.util.Date
            java.util.Date dataUtil = formato.parse(dataString);

            // Convertendo para java.sql.Date
            java.sql.Date dataSql = new java.sql.Date(dataUtil.getTime());

         //   System.out.println("Data em java.sql.Date: " + dataSql);
            return dataSql;
        } catch (ParseException e) {
            e.printStackTrace();
        }
		  
		  return null;
	}
	
	
	public ResultSet executarQueryNativo(String executarSq, Object... parmetros) throws SQLException {
		ETipoDados[] e = new ETipoDados[parmetros.length];
		for(int i = 0; i < parmetros.length -1 ; i++){
			e[i] = ETipoDados.TEXTO;
		}
		return executarQueryNativo(executarSq,e,parmetros);
	}
	
	
	public ResultSet executarQueryNativo(String executarSq, ETipoDados[] tipo, Object... parmetros) throws SQLException {
		if(connection == null){
			connection(user,password);
		}
		
		PreparedStatement stmt = connection.prepareStatement(executarSq);
		
		if(parmetros != null  && !(parmetros.length == 0)){
			int i = 0;
			for(Object o : parmetros){
				switch (tipo[i]) {
				case NUMERO:
					stmt.setLong(i+1, (Long) o);
					break;
			//	case INTE
				
				case DATA:
					if (o instanceof java.util.Date) {
						stmt.setDate(i+1, converterPara((java.util.Date) o));
					}else{
						stmt.setDate(i+1, converterPara(o.toString()));
					}
					break;
				default:
					stmt.setString(i+1, o.toString());
					break;
				}
				i++;
			}
			
		}
		ResultSet resultSet = stmt.executeQuery();
		return resultSet;
	}
	
	
	
	/*
	private java.sql.Date stringToSQLDate(String dateString) throws ParseException {
        // Define o formato da data esperado na string
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        // Faz o parsing da string para um objeto java.util.Date
        java.util.Date parsedDate = dateFormat.parse(dateString);
        // Converte java.util.Date para java.sql.Date
        return new java.sql.Date(parsedDate.getTime());
    }*/
	
	/*
	public ResultSet executeQuery(String sql, ETipoDados[] tipo, Object... valores) throws SQLException{
		if(connection == null){
			connection(user,password);
		}
		PreparedStatement  stmtRs = connection.prepareStatement(sql);
		
		
		int i = 0;
		for(Object v : valores){
			
			switch (tipo[i]) {
			case NUMERO:
				stmtRs.setLong(i+1, (Long) v);
				break;
			case DATA:						
				
					 
					 java.sql.Date d = Date.valueOf("2024-01-01");
					
					stmtRs.setDate(i+1, d );
				
				break;
				
			case DECIMAL:
				stmtRs.setDouble(i+1, (Double) v);
				break;

			default:
				stmtRs.setString(i+1, v.toString());
				break;
			}
			i++;
		}
		
		ResultSet res = stmtRs.executeQuery(sql);
		
		
		
		return res;
	}*/
	
	
	
	
	public void fecharStatement(){
		if(stmtRs == null){
			return;
		}
		try {
			stmtRs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void executeUpdate(){
		if(connection == null){
			connection(user,password);
		}
	}
	
	private void connection(String user, String passwd){
		 try {
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			 String url = "jdbc:oracle:thin:@" + SERVER + ":" + PORT + ":" + DATABESE;
			 System.out.println(url);
			 connection = DriverManager.getConnection(url, user, passwd);
		 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
	}
}
