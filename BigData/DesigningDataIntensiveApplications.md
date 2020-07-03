## Designing Data-Intensive Applications Chapter 1 & 2 Questions by: Jorge Agustín Erosa

~~Important: on the table and choose questions the answers are in order or are the first option~~

- [Chapter 1](#chapter-1)
- [Chapter 2](#chapter-2)
- [Chapter 3](#chapter-3)
- [Chapter 4](#chapter-4)


#### Chapter 1

##### 1. What are some of the standard building blocks that provide commonly needed functionality for a data-intensive application: 
  - Store data so that they, or another application, can find it again later (databases)
  - Remember the result of an expensive operation, to speed up reads (caches)
  - Allow users to search data by keyword or filter it in various ways (search indexes)
  - Send a message to another process, to be handled asynchronously (stream pro‐
cessing)
  - Periodically crunch a large amount of accumulated data (batch processing)
  
##### 2. Why do we use an umbrella term like data systems if there are so many different tools designed for different things?
  - Because many applications now have such demanding or wide-ranging requirements that a single tool can no longer meet all of its data processing and storage needs. Instead, the work is broken down into tasks that can be performed effi‐
ciently on a single tool, and those different tools are stitched together using application code.

##### 3. What are the three concerns that are important in most software systems?
  - Reliability, Scalability and Maintainability.

##### 4. What does Reliability refer to?
  - The system should continue to work correctly (performing the correct function at the desired level of performance) even in the face of adversity (hardware or software faults, and even human error).

##### 5. What does Scalability refer to?
  - As the system grows (in data volume, traffic volume, or complexity), there should be reasonable ways of dealing with that growth. 

##### 6. What does Maintainability refer to?
  - Over time, many different people will work on the system (engineering and operations, both maintaining current behavior and adapting the system to new use cases), and they should all be able to work on it productively.

##### 7. Typical expectations for reliable software:
  - The application performs the function that the user expected.
  - It can tolerate the user making mistakes or using the software in unexpected ways.
  - Its performance is good enough for the required use case, under the expected load and data volume.
  - The system prevents any unauthorized access and abuse.

##### 8. What are fault-tolerant or resilient systems?
  - Systems that anticipate faults and can cope with them

##### 9. What does describing workload refer to?
  - Typically the cost of operations (space allowed of your program)

##### 10. What are some of the questions you would use to describe performance?
  - When you increase a load parameter and keep the system resources (CPU, memory, network bandwidth, etc.) unchanged, how is the performance of your system affected?
  - When you increase a load parameter, how much do you need to increase the resources if you want to keep performance unchanged?
  
##### 11. What does the term "elastic" mean when talking about a system?
  - The system can automatically add computing resources when they detect a load increase, whereas other systems are scaled manually (a human analyzes the capacity and decides to add more machines to the system). An elastic system can be useful if load is highly unpredictable, but manually scaled systems are simpler and may have fewer operational surprises
  
##### 12. What are the 3 design principles to aim for, to make maintainability easier?
  - Operability: Make it easy for operations teams to keep the system running smoothly.
  - Simplicity: Make it easy for new engineers to understand the system, by removing as much complexity as possible from the system. (Note this is not the same as simplicity of the user interface.)
  - Evolvability: Make it easy for engineers to make changes to the system in the future, adapting it for unanticipated use cases as requirements change. Also known as extensibility, modifiability, or plasticity.
  
##### 13. How do data systems make routine tasks easier?
  - Providing visibility into the runtime behavior and internals of the system, with good monitoring
  - Providing good support for automation and integration with standard tools   
  - Avoiding dependency on individual machines (allowing machines to be taken down for maintenance while the system as a whole continues running uninterrupted)
  - Providing good documentation and an easy-to-understand operational model (“If I do X, Y will happen”)
  - Providing good default behavior, but also giving administrators the freedom to override defaults when needed
  - Self-healing where appropriate, but also giving administrators manual control over the system state when needed
  - Exhibiting predictable behavior, minimizing surprises 
  

#### Chapter 2

##### 1. Why NoSQL, why did it start?
  - A need for greater scalability than relational databases can easily achieve, including very large datasets or very high write throughput
  - A widespread preference for free and open source software over commercial database products
  - Specialized query operations that are not well supported by the relational model
  - Frustration with the restrictiveness of relational schemas, and a desire for a more dynamic and expressive data model
  
##### 2. Why do we call it NoSQL when it could be SQL and what has it changed into?
  - NoSQL was originally intended simply as a catchy Twitter hashtag for a meetup on open source, distributed, nonrelational databases in 2009, now the meaning has changed to Not Only SQL.
  
##### 3. What do Object-relational mapping (ORM) frameworks do?
  - reduce the amount of boilerplate code required for this translation layer (OOP -> SQL), but they can’t completely hide the differences between the two models.
  
##### 4. Multitable schema vs JSON
  - If you want to fetch a profile in the relational example, you need to either perform multiple queries (query each table by user_id ) or perform a messy multiway join between the users table and its subordinate tables. In the JSON representa‐
tion, all the relevant information is in one place, and one query is sufficient.

##### 5. Hierarchical vs Network models
  - H->1 parent, N-> can have multiple parents
  
##### 6. Relational vs Document models
  - The main arguments in favor of the document data model are schema flexibility, better performance due to locality, and that for some applications it is closer to the data structures used by the application.
  - The relational model counters by providing better support for joins, and many-to-one and many-to-many relationships.

##### 7. Declarative vs Imperative query language
  - An imperative language tells the computer to perform certain operations in a certain order. You can imagine stepping through the code line by line, evaluating conditions, updating variables, and deciding whether to go around the loop one more time.
  - In a declarative query language, like SQL or relational algebra, you just specify the pattern of the data you want—what conditions the results must meet, and how you want the data to be transformed (e.g., sorted, grouped, and aggregated)—but not how to achieve that goal.

##### 8. MapReduce declarative or imperative?
  - MapReduce is neither a declarative query language nor a fully imperative query API, but somewhere in between: the logic of the query is expressed with snippets of code, which are called repeatedly by the processing framework.

##### 9. Typical examples of graph models
  - Social graphs: Vertices are people, and edges indicate which people know each other.
  - The web graph: Vertices are web pages, and edges indicate HTML links to other pages.
  - Road or rail networks: Vertices are junctions, and edges represent the roads or railway lines between them.
  
  
#### Chapter 3

##### 1. What does the word "log" usually refer to?
  - The word log is often used to refer to application logs, where an application outputs text that describes what’s happening

##### 2. What is an index? Does it affect the contents of the database?
  - is an additional structure that is derived from the primary data. Many databases allow you to add and remove indexes, and this doesn’t affect the contents of the database; it only affects the performance of queries
  
##### 3. Name some advantages of append-only logs
  - Appending and segment merging are sequential write operations, which are generally much faster than random writes, especially on magnetic spinning-disk
hard drives. To some extent sequential writes are also preferable on flash-based solid state drives (SSDs). 
  - Concurrency and crash recovery are much simpler if segment files are appendonly or immutable. For example, you don’t have to worry about the case where a
crash happened while a value was being overwritten, leaving you with a file containing part of the old and part of the new value spliced together.
  - Merging old segments avoids the problem of data files getting fragmented over time.

##### 4. Explain size-tiered and leveled compaction as strategies to determine the order and timing of how SSTables are compacted and merged.
  - In size-tiered compaction,newer and smaller SSTables are successively merged into older and larger SSTables.
  - In leveled compaction, the key range is split up into smaller SSTables and older data is moved into separate “levels,” which allows the compaction to proceed more incrementally and use less disk space.
  
##### 5. What do full-text search engines commonly allow?
  - a search for one word to be expanded to include synonyms of the word, to ignore grammatical variations of words, and to search for occurrences of words near each other in the same document, and support various other features that depend on linguistic analysis of the text.
  
##### 6. Are Cassandra and HBase column oriented?
  - Cassandra and HBase have a concept of column families, which they inherited from Bigtable. However, it is very misleading to call them column-oriented: within each column family, they store all columns from a row together, along with a row key, and they do not use column compression. Thus, the Bigtable model is still mostly row-oriented.

##### 7. Name another advantage of sorted order:
  - it can help with compression of columns. If the primary sort column does not have many distinct values, then after sorting, it will have long sequences where the same value is repeated many times in a row. A simple run-length encoding could compress that column down to a few kilobytes—even if the table has billions of rows.
  
##### 8. What do we mean when we say that OLTP systems are typically user-facing?
  - It  means that they may see a huge volume of requests. In order to handle the load, applications usually only touch a small number of records in each query. The application requests records using some kind of key, and the storage engine uses an index to find the data for the requested key. Disk seek time is often the bottleneck here.
  
##### 9. How do Data Warehouses and similar analytic systems compare to OLTP systems?
  - They handle a much lower volume of queries than OLTP systems, but each query is typically very demanding, requiring many millions of records to be scanned in a short time. Disk bandwidth (not seek time) is often the bottleneck here, and columnoriented storage is an increasingly popular solution for this kind of workload


#### Chapter 4

##### 1. What's the difference when dealing with updates on server-side and client-side applications?
  - With server-side applications you may want to perform a rolling upgrade (also known as a staged rollout), deploying the new version to a few nodes at a time,
checking whether the new version is running smoothly, and gradually working your way through all the nodes. This allows new versions to be deployed without service downtime, and thus encourages more frequent releases and better evolvability.
  - With client-side applications you’re at the mercy of the user, who may not install
the update for some time.

##### 2. What are Backward and Forward compatibility and why are they necessary/useful?
  - Backward compatibility means newer code can read data that was written by older code. Forward compatibility means older code can read data that was written by newer code.
  - Sometimes old and new versions of the code, and old and new data formats may potentially all coexist in the system at the same time. These are necessary to keep the application running smoothly.
  
##### 3. What is encoding and what is decoding?
  - The translation from the in-memory representation to a byte sequence is called encoding (also known as serialization or marshalling), and the reverse is called decoding (parsing, deserialization, unmarshalling).
  
##### 4. Name some programming languages built-in support for encoding in-memory objects into byte sequences:
  - Java has java.io.Serializable, Ruby has Marshal, Python has pickle, etc.
  
##### 5. Is it a good or a bad idea to use your language’s built-in encoding? Explain
  - It's bad.
  - The encoding is often tied to a particular programming language, and reading the data in another language is very difficult. If you store or transmit data in such an encoding, you are committing yourself to your current programming language for potentially a very long time, and precluding integrating your systems
with those of other organizations (which may use different languages).
  - In order to restore data in the same object types, the decoding process needs to be able to instantiate arbitrary classes. This is frequently a source of security problems: if an attacker can get your application to decode an arbitrary byte sequence, they can instantiate arbitrary classes, which in turn often allows them to do terrible things such as remotely executing arbitrary code. 
  - Versioning data is often an afterthought in these libraries: as they are intended for quick and easy encoding of data, they often neglect the inconvenient problems of forward and backward compatibility.
  - Efficiency (CPU time taken to encode or decode, and the size of the encoded structure) is also often an afterthought. For example, Java’s built-in serialization is notorious for its bad performance and bloated encoding

##### 6. Name some issues with Standardized encoding  (JSON, XML, and CSV)
  - There is a lot of ambiguity around the encoding of numbers. In XML and CSV, you cannot distinguish between a number and a string that happens to consist of
digits (except by referring to an external schema). JSON distinguishes strings and numbers, but it doesn’t distinguish integers and floating-point numbers, and it
doesn’t specify a precision. This is a problem when dealing with large numbers; for example, integers greater than 2 53 cannot be exactly represented in an IEEE 754 double-precision floating-point number, so such numbers become inaccurate when parsed in a language that uses floating-point numbers (such as JavaScript). An example of numbers larger than 2 53 occurs on Twitter, which uses a 64-bit number to identify each tweet. The JSON returned by Twitter’s API includes tweet IDs twice, once as a JSON number and once as a decimal string, to work around the fact that the numbers are not correctly parsed by JavaScript applications.
  - JSON and XML have good support for Unicode character strings (i.e., human-readable text), but they don’t support binary strings (sequences of bytes without
a character encoding). Binary strings are a useful feature, so people get around this limitation by encoding the binary data as text using Base64. The schema is
then used to indicate that the value should be interpreted as Base64-encoded. This works, but it’s somewhat hacky and increases the data size by 33%.
  - There is optional schema support for both XML  and JSON . These schema languages are quite powerful, and thus quite complicated to learn and implement. Use of XML schemas is fairly widespread, but many JSON-based tools don’t bother using schemas. Since the correct interpretation of data (such as numbers and binary strings) depends on information in the schema, applications that don’t use XML/JSON schemas need to potentially hardcode the appropriate encoding/decoding logic instead.
  - CSV does not have any schema, so it is up to the application to define the meaning of each row and column. If an application change adds a new row or column,
you have to handle that change manually. CSV is also a quite vague format (what happens if a value contains a comma or a newline character?). Although its escaping rules have been formally specified , not all parsers implement them correctly.

##### 7. Name some advantages of binary encodings based on schemas:
  - They can be much more compact than the various “binary JSON” variants, since they can omit field names from the encoded data.
  - The schema is a valuable form of documentation, and because the schema is required for decoding, you can be sure that it is up to date (whereas manually
maintained documentation may easily diverge from reality).
  - Keeping a database of schemas allows you to check forward and backward compatibility of schema changes, before anything is deployed.
  - For users of statically typed programming languages, the ability to generate code from the schema is useful, since it enables type checking at compile time.
  
##### 8. Explain the phrase "data outlives code":
  - When you deploy a new version of your application (of a server-side application, at least), you may entirely replace the old version with the new version within a few minutes. The same is not true of database contents: the five-year-old data will still be there, in the original encoding, unless you have explicitly rewritten it since then.
 
##### 9. Briefly explain the network connection relationship between clients, servers and service:
  - The servers expose an API over the network, and the clients can connect to the servers to make requests to that API. The API exposed by the server is known as a service.
 
##### 10. Explain service oriented architecture (SOA), more recently refined and rebranded as microservices architecture:
  - A server can itself be a client to another service (for example, a typical web app server acts as client to a database). This approach is often used to decompose a large application into smaller services by area of functionality, such that one service makes a request to another when it requires some functionality or data from that other service.
  
##### 11. 
