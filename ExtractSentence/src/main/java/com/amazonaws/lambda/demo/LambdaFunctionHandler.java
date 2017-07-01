package com.amazonaws.lambda.demo;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
	 private DynamoDB dynamoDb;
     private String DYNAMODB_TABLE_NAME = "translation";
     private Regions REGION=Regions.US_EAST_1;
    



    @Override
    public String handleRequest(translate t, Context context) {
    	 int min=1;
         int max=20;
         int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
         System.out.println(randomNum);
     System.out.println("Starting");
   	 this.initDynamoDbClient(); 	
 	Table table = dynamoDb.getTable(DYNAMODB_TABLE_NAME);	
    Index index = table.getIndex("translated-index");
   //	PutItemOutcome outcome=persistData(t);
    String retrieved="";
	 QuerySpec query = new QuerySpec();
	query.withKeyConditionExpression("translated = :v_translated")
	.withValueMap(new ValueMap()
			.withString(":v_translated", "false")
			);
	ItemCollection<QueryOutcome> items = null;
    Iterator<Item> iterator = null;
    Item item = null;
    try {
        System.out.println("Starting to Query");
        items = index.query(query);

        iterator = items.iterator();
       for(int i=0;i<randomNum;i++)
        {
    	   if (iterator.hasNext()) {
        
            item = iterator.next();
          //  System.out.println("Line before display");
         retrieved=item.getString("sourceText");
        }
        }

    }
    catch (Exception e) {
        System.err.println("Unable to query");
        System.err.println(e.getMessage());
    }
    return retrieved;

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
