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
	
	/**
	 * Validate the location of the DatabaseDetails.info file and create a reader for it.
	 * @return BufferedReader to read the DatabaseDetails.info file.
	 * @throws FileNotFoundException when DatabaseDetails.info is not correctly located.
	 * @since 1.0
	 */
	private static BufferedReader getDatabaseDetailsFileReader() throws FileNotFoundException {
		final String FILENAME = "DatabaseDetails.info";
		
		File accountDetails = new File(FILENAME);
		
		if (!accountDetails.isFile()) {
			throw new FileNotFoundException("DatabaseDetails.info file not found in root folder. If URL, account"
					+ "name, and password are directly in the hibernate.cfg.xml folder, configuration will continue.");
		}
		
		return new BufferedReader(new FileReader(accountDetails));
		
	}
}
