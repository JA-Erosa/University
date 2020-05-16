## Big Data Principles Chapters 1 and 2 Questions

#### Chapter 1

##### 1. List some of the RDBMS problems Big Data solves: 
  - Cant keep up with user requests/database register
  - Need to build more backend and adding a queue in between systems
  - Leads to bottleneck.

##### 2. List some problems of Hadoop and Cassandra as "stand-alone" technologies:
  - Hadoop: high latency
  - Cassandra: mutable data, limited data model
  
##### 3. What does a data system do (according to the book)?:
  - It answers questions based on information that was acquired in the past up to the present
  
##### 4. List at least 3 properties of Big Data Systems:
  - Human-fault tolerance, low-latency, scalability/extensibility, generalize, Ad hoc query, low maintenance, debuggability
  
##### 5. What does "ad hoc query" mean and what would be considered its opposite?:
  - "for this purpose", queries on the fly, not precomputed
  - contrary to stored procedure

##### 6. What is the concept that a distributed database system can only have 2 of the 3: Consistency, Availability and Partition Tolerance called?:
  - CAP Theorem
 
##### 7. Identify the layer: known as a precomputed query, master copy runs in a while true loop to update:
  - batch layer
  
##### 8. Identify the layer: stable batch view to read:
  - serving layer
 
##### 9. Identify the layer: incremental computation, only looks at recent data, works as complexity isolation:
  - speed layer
  
##### 10. Match the product to the technology type:
Big Data Tech                 | Product
------------------------------| -------------
Batch computation systems     | Hadoop
Serialization frameworks      | Thrift
Random-access NoSQL database  | Cassandra
Messaging/queuing systems     | Kafka
Realtime computation system   | Storm


#### Chapter 2



