package org.graph.api.web.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/graph")
public class SchemaResource {
	
	private static final String DATABASE_PATH = "/database";
	
	private static final String NODE_PATH = "/node";
	
	private static final String RELATIONSHIP_PATH = "/rel";
	
	private static final String DB_ID_PARAM = "{dbId}";

	/**
	 * POST /database - Create a new database.
	 */
	@RequestMapping(value = DATABASE_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createDatabase(HttpServletRequest httpRequest) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * POST /database/node - Create a new Node under given database.
	 */
	@RequestMapping(value = DATABASE_PATH + "/" + DB_ID_PARAM + NODE_PATH, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createNode(HttpServletRequest httpRequest) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * POST /database/rel - Create a new Relationship under given database.
	 */
	@RequestMapping(value = DATABASE_PATH + "/" + DB_ID_PARAM + RELATIONSHIP_PATH , method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createRelationship(HttpServletRequest httpRequest) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
