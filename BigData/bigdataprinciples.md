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
  
##### 2. Why is Apache Thrift desirable when talking about enforceable schemas?:
  - Because it acts as a canvas for multiple programming languages, you can design the schema and then appply it to any programming language you want.
  
##### 3. What are unions, structs and properties used in Apache Thrift?:
  - unions are useful for representing nodes and allow the schema to evolve as the data evolves, structs are natural representations of edges (struct containing two nodes), and properties use a combination of both.

##### 4. What do the name and fields of struct indicate?:
  - The relationship it represents, and the fields in the struct contain the entities involved in the relationship.

##### 5. Why would you want to use a DataUnit union?:
  - To wrap and store all of the data together to provide a single interface to access your information. This way the data is easier to manage.
  
##### 6. Besides the wrapped-data what else is the DataUnit paired with and what do each store?:
  - The Pedigree struct which contains the metadata, the timestamp for the information, but could also potentially contain debugging information or the source of the data.The final Data struct corresponds to a fact from the fact-based model.
  
##### 7. What can you do to evolve the schema and what are the limitations?:
  - Fields may be renamed
  - A field may be removed, but you must never reuse that field ID
  - Only optional fields can be added to existing structs

##### 8. What can you do in Apache Thrift to work around a serialization framework limitations such as “Ages should be non-negative” or “true-as-of timestamps should not be in the future” ?:
  - Wrap your generated code in additional code that checks the additional properties you care about, like ages being non-negative. Check the extra properties at the very beginning of your batch-processing workflow.



#### Chapter 4

##### 1. Why must the storage solution for the master dataset be optimized to handle a large, constantly growing set of data?.
  - Because data is immutable and eternally true. Consequently, each piece of your data will be written once and only once.
Therefore, there's no need to ever alter your data, the only write operation will be to add a new data unit to your dataset

##### 2. What are the Write/Read requirements for the dataset?:
  - Efficient appends of new data
  - Scalable storage
  - Support for parallel processing
  - Tunable storage and processing costs
  - Enforceable immutability

##### 3. Why is generating a UUID to use as a key the only really viable idea in a key-value storage?:
  - Because there’s no natural key in the data model, nor is one necessary because the data is meant to be consumed in bulk. 

##### 4. Why is key-value storage considered non-optimal in this cas?
  - Because they are meant to be used as mutable stores. It also has a ton of things we don't need such as random reads, random writes, and all the machinery behind making those work.

##### 5. Filesystems pros and cons:
  - Pros: stored sequentially on disk, we have full control over the bytes of a file, and we have the full freedom to compress them however we want, it implements fine-grained permissions systems, which are perfect for enforcing immutability, 
  - Cons: it exists on just a single machine, so you can only scale to the storage limits and processing power of that one machine

##### 6. Difference between regular filesystems and distributed filesystems:
  - Distributed filesystems spread their storage across a cluster of computers. They scale by adding more machines to the cluster. They have fault tolerance because of their distributed nature.
  - In distributed filesystems you may not be able to write to the middle of a file or even modify a file at all after creation. Also having small files can be inefficients, 64 mb min is usually a good measure
  
##### 7. How does HDFS work in general?:
  - Hadoop is deployed across multiple servers as clusters and HDFS manages them. It basically works with a namenode that stores the information of the file/block and its location; and also works with multiple datanodes (typically 3) which are the replicas of the file.

##### 8. Natural advantage of this system?:
  - Fault tolerance:
  - With each block replicated across multiple nodes, your data remains available even when individual nodes are offline.
  - Files are spread across multiple machines for scalability and also to enable parallel processing.
  
##### 9. The files are immutable what should I do with my Distributed Filesystem (hdfs)?:
  - Spread the master dataset among many files, and store all those files in the same folder
  
##### 10. How can we achieve Vertically Partitioning of data on a distributed filesystem?:
  - By sorting your data into separate folders. For example, suppose you’re storing login information on a distributed filesystem. Each login contains a username, IP address, and timestamp. To vertically partition by day, you can create a separate folder for each day of data. Each day folder would have many files containing the logins for that day. Now if you only want to look at a particular subset of your dataset, you can just look at the files in those particular folders and ignore the other files.
