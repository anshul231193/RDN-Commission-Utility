package com.jwtweb.configurations.hibernate;

import java.io.Serializable;
import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl  implements Serializable{
    
	@Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
      return Identifier.toIdentifier(name.getText().toString());
    }
	
    @Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return addUnderscores(name);
	}

    protected static Identifier addUnderscores(Identifier name) {
        StringBuilder buf = new StringBuilder(name.getText().replace('.', '_'));
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1))
                    && Character.isUpperCase(buf.charAt(i))
                    && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return Identifier.toIdentifier(buf.toString().toLowerCase(Locale.ROOT));
    }
}
