package com.amazonaws.lambda.demo;
import java.util.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.partitions.model.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.*;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Index;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.RequestHandler;



public class LambdaFunctionHandler implements RequestHandler<translate, String> {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	Date date = new Date();
	
	 Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	 String formattedDate = sdf.format(date);
	 private DynamoDB dynamoDb;
     private String DYNAMODB_TABLE_NAME = "translation";
     private Regions REGION=Regions.US_EAST_1;
     
    @Override
    public String handleRequest(translate t, Context context) {
    	System.out.println(timestamp);
     System.out.println("Starting");
   	 this.initDynamoDbClient(); 	
 	Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME);	
    Index index = table.getIndex("translated-index");
   	PutItemOutcome outcome=persistData(t);
    String retrieved="";
    return retrieved;
    }


    private PutItemOutcome persistData(translate t) 
    	      throws ConditionalCheckFailedException {
    	     System.out.println("In perisist data");
    	     Table table=this.dynamoDb.getTable(DYNAMODB_TABLE_NAME);
    	        PutItemOutcome outcome= table
    	          .putItem(
    	            new PutItemSpec().withItem(new Item()
    	            		.withString("sourceText", t.getSourceText())
    	              .withString("targetText", t.getTargetText())
    	            	.withString("translated",t.getTranslated())
    	            	.withString("mail",t.getMail())
    	            			.withString("timestamp",formattedDate)));
    	        	
    	        return outcome;
    	    }
    	 


	private void initDynamoDbClient() {
	 @SuppressWarnings("deprecation")
	AmazonDynamoDBClient client = new AmazonDynamoDBClient();
	 client.setSignerRegionOverride("us-east-1");
	//.setRegion(Region.getRegion(REGION));
     this.dynamoDb = new DynamoDB(client);
     System.out.println("In dd client");

		// TODO Auto-generated method stub
		
	}

}
