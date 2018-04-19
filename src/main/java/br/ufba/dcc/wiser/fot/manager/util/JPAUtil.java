package br.ufba.dcc.wiser.fot.manager.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Class with utilitarian methods for accessing the bank.
 * 
 * @author Nilson Rodrigues Sousa
 */
public class JPAUtil {

	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("person");

	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
