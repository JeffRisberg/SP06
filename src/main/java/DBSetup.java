/**
 * Provides an improved version of database schema setup and initial population
 * loading, with multi-line SQL statements in the loading file.
 *
 * Copyright (C) 2006-2008 Jeffrey Risberg
 * @author Jeff Risberg
 * @since mid-2006
 */

import org.hibernate.JDBCException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.util.ConfigHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
    /**
     * Constructor
     */
    protected DBSetup(String[] args) {
        ApplicationContext appContext = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});

        LocalSessionFactoryBean lsfb = (LocalSessionFactoryBean) appContext
                .getBean("&sessionFactory");

        System.out.println("dropping schema");
        lsfb.dropDatabaseSchema();
        System.out.println("creating schema");
        lsfb.createDatabaseSchema();
        System.out.println("schema created");

        // dummy users
        loadSetupFile(appContext, "DB_Populate_10_Users.sql");
        loadSetupFile(appContext, "DB_Populate_20_Sites.sql");
        loadSetupFile(appContext, "DB_Populate_30_Rubrics.sql");
    }

    @SuppressWarnings("deprecation")
    protected void loadSetupFile(ApplicationContext appContext,
                                 String populateFileName) {
        try {
            System.out.println("Loading: " + populateFileName);

            InputStreamReader populateFileReader = null;
            InputStream stream = ConfigHelper
                    .getResourceAsStream(populateFileName);
            populateFileReader = new InputStreamReader(stream);

            SessionFactory sf = (SessionFactory) appContext
                    .getBean("sessionFactory");
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
            Connection connection = session.connection();
            Statement statement = connection.createStatement();

            BufferedReader reader = new BufferedReader(populateFileReader);
            String combinedSql = "";
            long lineNo = 0;

            String delimiter = ";";
            for (String sql = reader.readLine(); sql != null; sql = reader
                    .readLine()) {
                try {
                    lineNo++;
                    if ((lineNo % 10) == 0)
                        System.out.println("populate: line " + lineNo);

                    if (sql.startsWith("DELIMITER")) {
                        delimiter = sql.substring(10);
                    } else {
                        String trimmedSql = sql.trim();
                        if (trimmedSql.length() == 0
                                || trimmedSql.startsWith("--")
                                || trimmedSql.startsWith("//")
                                || trimmedSql.startsWith("/*")) {
                            continue;
                        } else {
                            // append it
                            combinedSql += " " + trimmedSql;

                            if (trimmedSql.endsWith(delimiter)) {
                                // process it
                                combinedSql = combinedSql
                                        .replace(delimiter, "");
                                statement.execute(combinedSql);
                                combinedSql = "";
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new JDBCException(
                            "Error during import script execution at line "
                                    + lineNo, e);
                }
            }
            transaction.commit();
            statement.close();
            connection.close();
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBSetup(args);
        System.out.println("DBSetup successfully completed");
    }
}