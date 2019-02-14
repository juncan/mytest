package com.test.mongoDB;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wujc
 * @ClassName Client
 * @Description: TODO
 * @create 2018-12-01 23:25
 */
public class Client {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("192.168.11.129", 27017);
        MongoDatabase sang = client.getDatabase("sang");
        MongoCollection<Document> c = sang.getCollection("c1");

        //添加单条数据
        Document d1 = new Document();
        d1.append("name", "三国演义").append("author", "罗贯中");
        c.insertOne(d1);

        //添加多条数据
        List<Document> collections = new ArrayList<Document>();
        Document d2 = new Document();
        d2.append("name", "三国演义").append("author", "罗贯中");
        collections.add(d2);
        Document d3 = new Document();
        d3.append("name", "红楼梦").append("author", "曹雪芹");
        collections.add(d3);
        c.insertMany(collections);


    }
}
