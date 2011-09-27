package com.teracode.prototipogwt.backend.extras.config;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * Like {@link ImprovedNamingStrategy}, except foreign keys use the format "name_id".
 * @author Andran
 */
public class NamingStrategy extends ImprovedNamingStrategy {

	private static final long serialVersionUID = 1L;

	/**
	 * A convenient singleton instance
	 */
	public static final NamingStrategy INSTANCE = new NamingStrategy();

	/**
	 * Return the property name or propertyTableName
	 */
	@Override
	public String foreignKeyColumnName(String propertyName, 
									   String propertyEntityName, 
									   String propertyTableName, 
									   String referencedColumnName) {
		
		String column = super.foreignKeyColumnName(propertyName, propertyEntityName, propertyTableName, referencedColumnName);
		return column + "_id";
	}
}
