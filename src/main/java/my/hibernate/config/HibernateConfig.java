package my.hibernate.config;

import my.hibernate.entities.Foreignwords;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.backend.jta.internal.JtaTransactionCoordinatorBuilderImpl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class HibernateConfig {

//    private static HibernateConfig config = new HibernateConfig();
//    private Context context;
//    private PoolingDataSource datasource;
//
//    private HibernateConfig() {
//        try {
//            initDataSource();
//            initContext();
//            initTransactioanal();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static SessionFactory createSessionFactory() {
//        try {
//
//
//            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
//            serviceRegistryBuilder.applySettings(config.setHibernateProperties());
////        serviceRegistryBuilder.applySetting(
////                Environment.TRANSACTION_COORDINATOR_STRATEGY,
////                JtaTransactionCoordinatorBuilderImpl.class
////        );
//            org.hibernate.service.ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//            MetadataSources metadataSources = new MetadataSources(serviceRegistry);
//            metadataSources.addAnnotatedClass(Foreignwords.class);
//            //metadataSources.addPackage("my.hibernate.entities");
//            MetadataBuilder metadataBuilder = metadataSources.getMetadataBuilder();
//            Metadata metadata = metadataBuilder.build();
//            return metadata.buildSessionFactory();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
//
//    private Map setHibernateProperties() {
//        Map map = new HashMap();
//        map.put("hibernate.connection.datasource", "SS");
////        map.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
////        map.put("hibernate.connection.url", "jdbc:mysql://localhost/hibernate?sessionVariables=sql_mode='PIPES_AS_CONCAT'");
////        map.put("hibernate.connection.username", "hybris");
//        // map.put("hibernate.connection.password", "hybris");
//        //map.put("hibernate.connection.pool_size", "3");
//        map.put("hibernate.format_sql", "true");
//        map.put("hibernate.use_sql_comments", "true");
//        map.put("hibernate.hbm2ddl.auto", "create-drop");
//        map.put("hibernate.transaction.coordinator_class", JtaTransactionCoordinatorBuilderImpl.class);
//        // map.put("hibernate.current_session_context_class", "thread");
//        //map.put("hibernate.transaction.jta.platform", "org.hibernate.service.jta.platform.internal.SunOneJtaPlatform");
//
//        //  map.put("hibernate.connection.autocommit", "true");
//        return map;
//    }
//
//    public Context getNamingContext() {
//        return context;
//    }
//
//    public static UserTransaction getUserTransaction() {
//        try {
//            return (UserTransaction) config.getNamingContext()
//                    .lookup("java:comp/UserTransaction");
//        } catch (Exception ex) {
//            throw new RuntimeException(ex);
//        }
//    }
//
//    public void initDataSource() {
//        datasource = new PoolingDataSource();
//        datasource.setUniqueName("SS");
//        datasource.setMinPoolSize(1);
//        datasource.setMaxPoolSize(5);
//        datasource.setPreparedStatementCacheSize(10);
//        datasource.setIsolationLevel("READ_COMMITTED");
//        datasource.setAllowLocalTransactions(true);
//        datasource.setClassName("bitronix.tm.resource.jdbc.lrc.LrcXADataSource");
//        datasource.setDriverProperties(getDataProperties());
//        datasource.init();
//    }
//
//    private Properties getDataProperties() {
//        //Map<String,String> map =config.setHibernateProperties();
//        Properties properties = new Properties();
//        //properties.putAll(map);
//        properties.put("user", "hybris");
//        properties.put("password", "hybris");
//        properties.put("url", "jdbc:mysql://localhost/hibernate?sessionVariables=sql_mode='PIPES_AS_CONCAT'");
//        properties.put("driverClassName", "com.mysql.jdbc.Driver");
//        return properties;
//    }
//
//    public void initTransactioanal() {
//        if(TransactionManagerServices.getConfiguration().getServerId()==null) {
//            TransactionManagerServices.getConfiguration().setServerId("myServer1234");
//
//            TransactionManagerServices.getConfiguration().setDisableJmx(true);
//
//            TransactionManagerServices.getConfiguration().setJournal("null");
//
//            TransactionManagerServices.getConfiguration().setWarnAboutZeroResourceTransaction(false);
//        }
//    }
//
//    private void initContext() {
//        if (context == null) {
//            try {
//                context = new InitialContext();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void rollback() {
//        try {
//
//
//            UserTransaction tx = config.getUserTransaction();
//            try {
//                if (tx.getStatus() == Status.STATUS_ACTIVE ||
//                        tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
//                    tx.rollback();
//            } catch (Exception ex) {
//                System.err.println("Rollback of transaction failed, trace follows!");
//                ex.printStackTrace(System.err);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
