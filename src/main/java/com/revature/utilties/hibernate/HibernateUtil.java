package com.revature.utilties.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.*;

/**
 * A collection of utilities for using the Hibernate API. Contains methods to easily 
 * configure a singleton SessionFactory and hide sensitive information.
 * 
 * A DatabaseDetails.info file can be placed in the src/main/resources folder, containing the database URL,
 * user name, and password to avoid having these details directly in the Hibernate configuration file.
 * @author Gavin Moore
 * @version 1.1
 */
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	/**
	 * Initialize the singleton SessionFactory if necessary, otherwise return it.
	 * @return the single SessionFactory, configured according to the hibernate.cfg.xml file.
	 * @since 1.0
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			initSessionFactoryFromEnv();
		}
		return sessionFactory;
	}
	
	/**
	 * Instantiate and configure the SessionFactory object.
	 * @since 1.1
	 */
	private static void initSessionFactoryFromEnv() {
		// Configure base details from hibernate.cfg.xml
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure();

		// Complete SessionFactory instantiation.
		MetadataSources sources = new MetadataSources(registryBuilder.build());
		Metadata metadata = sources.getMetadataBuilder().build();
		sessionFactory = metadata.getSessionFactoryBuilder().build();
	}
}
