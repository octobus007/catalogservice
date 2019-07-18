package com.ibm.rho.estore.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.ibm.rho.estore.model.Product;
import com.ibm.rho.estore.services.MongoDBCatalogService;
 
@Path("/products")
public class CatalogApplicationRestEndPoint  {
 
	
	@Inject
	private MongoDBCatalogService catalogService;
	
	@GET
	@Path("/")
	public Response findAll() {
		catalogService = new MongoDBCatalogService();
		List<Product> productList = catalogService.getProducts();
		GenericEntity<List<Product>> list = new GenericEntity<List<Product>>(productList) {        };
		String output = "Jersey say : ";
		return Response.status(200).entity(list).build();
 
	}
 
}

