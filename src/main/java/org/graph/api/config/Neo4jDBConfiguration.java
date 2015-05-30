//package org.graph.api.config;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.neo4j.graphdb.GraphDatabaseService;
//import org.neo4j.kernel.DefaultGraphDatabaseDependencies;
//import org.neo4j.kernel.EmbeddedGraphDatabase;
//import org.neo4j.kernel.GraphDatabaseDependencies;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.neo4j.config.EnableNeo4jRepositories;
//import org.springframework.data.neo4j.config.Neo4jConfiguration;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableNeo4jRepositories("org.graph.api.graph.repository")
//public class Neo4jDBConfiguration extends Neo4jConfiguration {
//
//	@Autowired
//	private Environment env;
//	
//	@Value("${neo4j.db.location:D:\\Prakash\\neo4j\\graphapi\\graph.db}")
//	private String dbLocation;
//	
//	//@Override
//	public void setEnvironment(Environment env) {
//		this.env = env;
//	}
//
//	public Neo4jDBConfiguration() {
//		setBasePackage(new String[] { "org.graph.api.neo4j.domain" });
//	}
//
////	@Bean
////	public Neo4jConfiguration neo4jConfiguration() {
////		Neo4jConfiguration neo4jConfig = new Neo4jDBConfig();
////		neo4jConfig.setBasePackage(new String[] { "org.graph.api.neo4j.domain" });
////		neo4jConfig.setGraphDatabaseService(graphDatabaseService());
////		return neo4jConfig;
////	}
////	
////
//	@Bean
//	public GraphDatabaseService graphDatabaseService() {
//		//dbLocation = env.getProperty("neo4j.db.location");
//		// if you want to use Neo4j as a REST service
//		// return new SpringRestGraphDatabase("http://localhost:7474/db/data/");
//		// Use Neo4j as Odin intended (as an embedded service)
//		// GraphDatabaseService service = new
//		// GraphDatabaseFactory().newEmbeddedDatabase(dbLocation);
//		dbLocation = "D:\\Prakash\\neo4j\\graphapi\\graph.db";
//		@SuppressWarnings("deprecation")
//		GraphDatabaseService service = new EmbeddedGraphDatabase(dbLocation,
//				getConfig(), graphDatabaseDependencies());
//		return service;
//	}
////
////	@Bean
////	public GraphDatabaseFactory graphDbFactory() {
////		return new GraphDatabaseFactory();
////	}
////	
////	@Bean(name={"neo4jTemplate"})
////	public Neo4jOperations neo4jOperations() {
////		return new Neo4jTemplate(graphDatabaseService()); 
////	}
////
//	private Map<String, String> getConfig() {
//		Map<String, String> configMap = new HashMap<String, String>();
//		configMap.put("remote_shell_enabled", "true");
//		configMap.put("store_dir", "D:\\Prakash\\neo4j\\graphapi\\graph.db");
//		return configMap;
//	}
//
//	public GraphDatabaseDependencies graphDatabaseDependencies() {
//		return new DefaultGraphDatabaseDependencies();
//	}
////
////	class Neo4jDBConfig extends Neo4jConfiguration {
////		
////		
////	}
//	
//}
