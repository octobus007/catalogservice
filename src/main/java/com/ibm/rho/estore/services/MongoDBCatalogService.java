package com.ibm.rho.estore.services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.bson.Document;

import com.ibm.rho.estore.model.Product;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;


@ApplicationScoped
public class MongoDBCatalogService {

    //@Inject
    private MongoClient mc;

    //@Inject
    Logger log;

    private MongoCollection productCollection;

public MongoDBCatalogService() {
}

public List<Product> getProducts() {
	
	MongoClient mc = new MongoClient(new ServerAddress("mongbdb.inmbzicp6144.in.dst.ibm.com",27017), Arrays.asList(MongoCredential.createCredential("cataloguser", "productsDB", "catalogpwd".toCharArray())));
	List<Product> productList = new ArrayList();
	MongoDatabase db = mc.getDatabase("productsDB");
	productCollection = db.getCollection("products");
	FindIterable<Document> findIterable = productCollection.find();
	
	MongoCursor<Document> cursor = findIterable.iterator();
	    try {
	        while (cursor.hasNext()) {
	            Document document = cursor.next();
	            Product product = toProduct(document);
	            productList.add(product);
	        }
	    } finally {
	        cursor.close();
	    }
	    return productList;
    }


    public void add(Product product) {
        productCollection.insertOne(toDocument(product));
    }

    public void addAll(List<Product> products) {
        
        //productCollection.insertMany(documents);
    }

    @PostConstruct
    protected void init() {
        log.info("@PostConstruct is called...");

        String dbName = System.getenv("DB_NAME");
        if(dbName==null || dbName.isEmpty()) {
            log.info("Could not get environment variable DB_NAME using the default value of 'CatalogDB'");
            dbName = "productsDB";
        }

        MongoDatabase db = mc.getDatabase(dbName);


        productCollection = db.getCollection("products");

        // Drop the collection if it exists and then add default content
        productCollection.drop();
        addAll(DEFAULT_PRODUCT_LIST);

    }

    @PreDestroy
    protected void destroy() {
        log.info("Closing MongoClient connection");
        if(mc!=null) {
            mc.close();
        }
    }

    /**
     * This method converts Product POJOs to MongoDB Documents, normally we would place this in a DAO
     * @param product
     * @return
     */
    private Document toDocument(Product product) {
        return new Document()
                .append("productId",product.getProductId())
                .append("productName",product.getProductName())
                .append("partNo",product.getPartNo())
                .append("imageLink",product.getImageLink())
        		.append("productShortDesc",product.getProductShortDesc());
    }

   
    /**
     * This method converts MongoDB Documents to Product POJOs, normally we would place this in a DAO
     * @param document
     * @return
     */
    private Product toProduct(Document document) {
        Product product =  new Product();
        product.setProductId(document.getString("productId"));
        product.setPartNo(document.getString("partNo"));
        product.setProductName(document.getString("productName"));
        product.setProductShortDesc(document.getString("productShortDesc"));
        return product;
    }



    private static List<Product> DEFAULT_PRODUCT_LIST = new ArrayList<>();
    static {
    	
        DEFAULT_PRODUCT_LIST.add(new Product(new Long(1234),"TSHIRT-10", "Men T-Shirt", "HZ201SOLV-BLACK","Lightblue Textured Mesh Men T-Shirt", "South India SHOPPING MALL Lightblue Textured Mesh Men T-Shirt "));
        DEFAULT_PRODUCT_LIST.add(new Product(new Long(5643),"BT-Shirt-20", " Blue T-Shirt", "SL201SOLV-BLUE","Castle Buck Cotton Navy Blue T-Shirt", "Castle Buck Cotton Navy Blue T-Shirt T-Shirt"));
        
    }

}
