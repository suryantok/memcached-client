package com.suryanto.app;

import java.io.IOException;
import java.net.InetSocketAddress;

import net.spy.memcached.MemcachedClient;

public class App {
   public static void main(String[] args) throws IOException {
      
      // Connecting to Memcached server on localhost
      boolean localMemcached = false;
      System.out.println("argument received: " + args[0]);
      if (args.length >= 1){        
        localMemcached = Boolean.parseBoolean( args[0]);        
      }
      
      MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
      if (localMemcached) {
        System.out.println("connect to local");
        mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));        
      }else 
      {
        System.out.println("connect to aws");      
        mcc = new MemcachedClient(new InetSocketAddress("mymemcached.ut71ls.cfg.use1.cache.amazonaws.com", 11211));        
      }
        
       System.out.println("Connection to server sucessfully");
            
      //not set data into memcached server
      System.out.println("set status:" + mcc.set("tutorialspoint", 900, "memcached11").isDone());
      
      //Get value from cache
      System.out.println("Get from Cache:"+mcc.get("tutorialspoint"));
   }
}