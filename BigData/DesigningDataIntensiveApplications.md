## Designing Data-Intensive Applications Chapter 1 Questions by: Jorge Agustín Erosa

~~Important: on the table and choose questions the answers are in order or are the first option~~

- [Chapter 1](#chapter-1)
  


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
  - Providing good support for automation and integration with standard tools   - Avoiding dependency on individual machines (allowing machines to be taken down for maintenance while the system as a whole continues running uninter‐
rupted)
  - Providing good documentation and an easy-to-understand operational model (“If I do X, Y will happen”)
  - Providing good default behavior, but also giving administrators the freedom to override defaults when needed
  - Self-healing where appropriate, but also giving administrators manual control over the system state when needed
  - Exhibiting predictable behavior, minimizing surprises 
