![UPY logo]()
#### Elaborated by Saúl Luna Estévez & Jorge Agustín Erosa Herrera

#Information Management Data 5A

## Huffman Compression
In 1951, David Huffman and his classmates in the subject “Information Theory” were allowed to choose between taking a final exam or presenting a paper. Professor Robert. M. Fano assigned the working conditions under the premise of finding the most efficient binary code. Huffman, unable to demonstrate which code was more efficient, gave up and began studying for the final exam. While in this process the idea of using ordered frequency binary trees came to his mind and he quickly proved that this was the most efficient method.

With this study, Huffman surpassed his professor, who had worked with the inventor of information theory Claude Shannon in order to develop a similar code. Huffman fixed most of the bugs in the Shannon-Fano encoding algorithm. The solution was based on the process of building the tree from bottom to top instead of the other way around.

In computer science and information theory, Huffman encoding is an algorithm used for data compression. The term refers to the use of a variable length code table to encode a certain symbol (such as a character in a file), where the table has been filled in a specific way based on the estimated probability of occurrence of each possible value of said symbol. It was developed by David A. Huffman while a PhD student at MIT, and published in "A Method for the Construction of Minimum-Redundancy Codes".

Huffman encoding uses a specific method to choose the representation of each symbol, which gives rise to a prefix code (that is, the bit string representing a particular symbol is never a prefix to the bit string of a different symbol) representing the most common characters using the shortest bit strings, and vice versa. Huffman was able to design the most efficient compression method of this type: no alternative representation of a set of input symbols produces a smaller mean output when the symbol frequencies match those used to create the code. Later a method was found to do this in linear time if the probabilities of the input symbols (also known as "weights") are ordered.

Compression and Data Science deal with exactly the same problem: understanding the data. The better you can understand the data the better the model you can make from it and then with careful encoding you can have a minimum description length for the data. So not only data science can be used for data compression, data science IS data compression and data compression IS data science.

If we think about generative models, the models that create data similar to what we have then we can argue that the best generative model is also the best compression model because you need to understand data to generate, mimic data accurately and if you can understand data then you can compress it.

## Methodology
The technique used is Huffman's own algorithm. It consists of creating a binary tree in which the leaf nodes are labeled with the characters, along with their frequencies, and each pair of nodes that add less frequency are joined consecutively, creating a new intermediate node labeled with said sum. This action is carried out until there are no leaf nodes left to join any superior node, and the binary tree has been formed.

Subsequently, the edges that join each of the nodes are labeled with zeros and ones (right and left son, respectively, for example). The resulting code for each character is the reading, following the branch, from the root to each character (or vice versa) of each of the edge labels.
Building the tree:
1. Create a leaf node for each unique character and build a min heap of all leaf nodes (Min Heap is used as a priority queue. The value of frequency field is used to compare two nodes in min heap. Initially, the least frequent character is at root)
2. Extract two nodes with the minimum frequency from the min heap.
3. Create a new internal node with a frequency equal to the sum of the two nodes frequencies. Make the first extracted node as its left child and the other extracted node as its right child. Add this node to the min heap.
4. Repeat steps#2 and #3 until the heap contains only one node. The remaining node is the root node and the tree is complete.

## Results

## Conclusions
In conclusion, data compression and data science are related as it helps to better understand the data, compresses it and is easier to read, which increases the efficiency of the program and helps the result of data analysis.