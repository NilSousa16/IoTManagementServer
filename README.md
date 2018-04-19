# IoTManagementServer
Project IoTManagement - Server

Configuração para o ServiceMix

feature:repo-add cellar 4.0.4
feature:install cellar
feature:install webconsole

bundle:install mvn:org.apache.servicemix.bundles/org.apache.servicemix.bundles.commons-httpclient/3.1_7

bundle:install mvn:com.google.code.gson/gson/2.8.2

feature:install transaction jndi pax-jdbc-h2 pax-jdbc-config pax-jdbc-pool-dbcp2 jpa/1.0.4 hibernate/4.3.6.Final

cat https://raw.githubusercontent.com/cschneider/Karaf-Tutorial/master/db/org.ops4j.datasource-person.cfg | tac -f etc/org.ops4j.datasource-person.cfg
