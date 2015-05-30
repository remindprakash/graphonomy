package org.graph.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.aspects.config.Neo4jAspectConfiguration;

@Configuration
@ImportResource({"classpath*:neo4j-ctx.xml"})
@PropertySource({"classpath:config/dev/db.properties"})
public class Neo4jConfig  {


}
