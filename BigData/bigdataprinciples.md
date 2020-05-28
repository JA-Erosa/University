## Big Data Principles Chapters 1-4 Questions by: Jorge Agustín Erosa

~~Important: on the table and choose questions the answers are in order or are the first option~~

- [Chapter 1](#chapter-1)
  
- [Chapter 2](#chapter-2)

- [Chapter 3](#chapter-3)
  
- [Chapter 4](#chapter-4)




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

##### 1. What is only part of the Lambda Architecture that absolutely must be safeguarded from corruption?:
  - Master Dataset

##### 2. Choose the correct term: It's the general collection of knowledge relevant to your Big Data system.It’s synonymous with the colloquial usage of the word data.
  - information
  - data
  - queries
  - views
  
##### 3. Choose the correct term: It refers to the information that can’t be derived from anything else. Data serves as the axioms from which everything else derives.
  - data
  - information
  - queries
  - views
  
##### 4. Choose the correct term: Questions you ask of your data. For example, you query your financial transaction history to determine your current bank account balance.
  - queries
  - data
  - information
  - views
  
##### 5. Choose the correct term: Information that has been derived from your base data. They are built to assist with answering specific types of queries.
  - views
  - data
  - queries
  - information

##### 6. List the 3 key properties of data:
  - rawness, immutability and perpetuity
  
##### 7. Complete the sentence "The _____ your data, the more questions you can ask of it":
  - rawer

##### 8. Define graph schema:
  - A graph schema is a "dictionary" that defines the types of entities, vertices and edges , in the graph and how those types of entities are related to one another. 
  
##### 9. Is queryable at any time in its history, Tolerates human errors, Handles partial information, Has the advantages of both normalized and denormalized forms. These are all benefits of:
  - The fact-based model
  
  
  
  
#### Chapter 3

##### 1. What's the problem with using schemaless formats like json and what can you use instead?:
  - Using schemaless data almost always leads to bugs or developers misunderstanding which ultimately leads to data corruption. The problem is it won't show right away and will give you little context of the error. You can instead make use of Serialization Frameworks (enforceable schema) which raises errors at the moment of writing the data, with full context and prevents it from corrupting the master data.
  
#### 2. Why is Apache Thrift desirable when talking about enforceable schemas?:
  - Because it acts as a canvas for multiple programming languages, you can design the schema and then appply it to any programming language you want.
  
#### 3. What are unions, structs and properties used in Apache Thrift?:
  - unions are useful for representing nodes and allow the schema to evolve as the data evolves, structs are natural representations of edges (struct containing two nodes), and properties use a combination of both.

#### 4. What do the name and fields of struct indicate?:
  - The relationship it represents, and the fields in the struct contain the entities involved in the relationship.

#### 5. Why would you want to use a DataUnit union?:
  - To wrap and store all of the data together to provide a single interface to access your information. This way the data is easier to manage.
  
#### 6. Besides the wrapped-data what else is the DataUnit paired with and what do each store?:
  - The Pedigree struct which contains the metadata, the timestamp for the information, but could also potentially contain debugging information or the source of the data.The final Data struct corresponds to a fact from the fact-based model.
  
#### 7. What can you do to evolve the schema and what are the limitations?:
  - Fields may be renamed
  - A field may be removed, but you must never reuse that field ID
  - Only optional fields can be added to existing structs

#### 8. What can you do in Apache Thrift to work around a serialization framework limitations such as “Ages should be non-negative” or “true-as-of timestamps should not be in the future” ?:
  - Wrap your generated code in additional code that checks the additional properties you care about, like ages being non-negative. Check the extra properties at the very beginning of your batch-processing workflow.



#### Chapter 4

