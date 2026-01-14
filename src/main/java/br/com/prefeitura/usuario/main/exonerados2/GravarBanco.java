package br.com.prefeitura.usuario.main.exonerados2;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import br.com.prefeitura.usuario.dto2.UsuarioExcluirAfastadoDto;
import br.com.prefeitura.usuario.model.UsuarioDesativado;

public class GravarBanco {
	private Session session = null;	
	private SessionFactory sessionFactory;
	
	public GravarBanco(){
		sessionFactory = new Configuration().configure().buildSessionFactory();
		session = sessionFactory.openSession();
		
	}
	
	public void fechar(){
		sessionFactory.close();
	}
	
	public void salvarUsuariosDesativado(List<UsuarioExcluirAfastadoDto> usuarios){
		session.getTransaction().begin();
        for(UsuarioExcluirAfastadoDto p : usuarios){
        	UsuarioDesativado usuarioDesativado = new UsuarioDesativado(p.getMatricula(),  p.getCpf(), "EXONERADOS",  new Date());
        	session.saveOrUpdate(usuarioDesativado);
        }
        session.getTransaction().commit();
        
        sessionFactory.close();
	}
	
	public void salvarRegistroUsuarioAfastadoRh(List<UsuarioExcluirAfastadoDto> usuarios){
		session.getTransaction().begin();
        for(UsuarioExcluirAfastadoDto p : usuarios){
        	UsuarioDesativado usuarioDesativado = new UsuarioDesativado(p.getMatricula(),  p.getCpf(), "AFASTADO",  new Date());
        	session.saveOrUpdate(usuarioDesativado);
        }
        session.getTransaction().commit();
        
        sessionFactory.close();
	}
	
	
	public void reativarUsuario(List<UsuarioExcluirAfastadoDto> usuarios) {
	    // Abre a sessão fora do loop
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;

	    try {
	        tx = session.beginTransaction();

	        for (UsuarioExcluirAfastadoDto p : usuarios) {
	            try {
	                // Usamos list() e pegamos o primeiro para evitar o erro de "NonUniqueResultException"
	                List<UsuarioDesativado> resultados = session.createQuery(
	                    "FROM UsuarioDesativado u WHERE u.nrCpf = :cpf AND u.icTipo = :tipo", UsuarioDesativado.class)
	                    .setParameter("cpf", p.getCpf())
	                    .setParameter("tipo", "EXONERADOS")
	                    .getResultList();

	                UsuarioDesativado usuarioExistente = resultados.isEmpty() ? null : resultados.get(0);

	                    usuarioExistente.setMatriculas(p.getMatricula());
	                    usuarioExistente.setDtAlterado(new Date());
	                    usuarioExistente.setIcTipo("REATIVADO");
	                    usuarioExistente.setNrCpf(p.getCpf());
	                    session.update(usuarioExistente);
	                

	                // Batch flush: opcional, melhora performance em listas grandes
	                // if (i % 50 == 0) { session.flush(); session.clear(); }

	            } catch (Exception e) {
	                System.err.println("Erro ao processar CPF: " + p.getCpf() + " - " + e.getMessage());	              
	            }
	        }

	        tx.commit();
	    } catch (Exception e) {
	        if (tx != null && tx.isActive()) {
	            tx.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	}
	

	public void removerUsuarioDaLista(Object object) {
		// TODO Auto-generated method stub
		
	}
}