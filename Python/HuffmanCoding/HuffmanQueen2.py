import pandas as pd
import numpy as np

from IPython.display import display_html
def display_side_by_side(*args):
    html_str=''
    for df in args:
        html_str+=df.to_html()
    display_html(html_str.replace('table','table style="display:inline"'),raw=True)

with open('song.txt','r') as file:
    lines = []
    for line in file:
        lines.append(line[:-1])
        
original = " ".join(lines)
print("Original text\n",original)
input("\nPress any key to continue...\n")

#DataCamp, ehm I mean, UPY courses paying off
freq={key: original.count(key) for key in original}

# Show Output
print("\n")
print ("Per char frequency is :\n {}".format(str(freq)))

main_table=pd.DataFrame(data = {'Character': list(freq.keys()),'Frequency': list(freq.values())}).sort_values(by='Frequency').reset_index().drop(columns='index')

total_char_freq=sum(main_table.Frequency)
print("\n")
print("Total character frequency found",total_char_freq)

total_char=main_table.Character.count()
print("Total number of characters found (linespace counts as a regular space)", total_char)

main_table['Probability'] = main_table['Frequency'] / total_char_freq
main_table['Log2(p)'] = np.log2(main_table.Probability)
main_table['p * log2(p)'] = main_table['Log2(p)'] * main_table.Probability

entropy=sum(main_table['p * log2(p)'])*-1
print("Calculated entropy:", entropy)
input("\nPress any key to continue...\n")

encoding=['1000011110','1110100000','1000011111','1110100001','100001110','111010110','111010001','111010100','111010101','111010111',
          '10000110','11101001','1000010','1010100','1010101','010100','010101','100000','011000','011001','101011','110010','110011',
          '111011','01011','01101','10001', '10100','11000','11100','11110','11111','0100','0111','1001','1011','1101','00']

print("Default encoding values (we calculated earlier)\n",encoding)   
input("\nPress any key to continue...\n")

main_table['Encoding']=encoding
main_table['Length']=main_table.Encoding.str.len()
main_table['Total_Length']=main_table.Length*main_table.Frequency

print("\n",main_table)

total_encoding_freq=sum(main_table.Total_Length)
print("\nTotal encoding frequency: ",total_encoding_freq)
input("\nPress any key to continue...\n")


##Compressing and Decompressing

#hash-ish
code_dict=pd.Series(main_table.Encoding.values,index=main_table.Character.values).to_dict()
print("Dictionary to use in the process:\n",code_dict)
input("\nPress any key to continue...\n")

encoded_text=original
for i in code_dict:
    encoded_text = encoded_text.replace(i, code_dict[i])

print("Character frequency found in compressed text",len(encoded_text))
input("\nPress any key to continue...\n")

print("\nResulting encoded file:\n",encoded_text)
input("\nPress any key to continue...\n")

decoded_text = ''
for i in range(len(original)):
    for char, code in code_dict.items():
        try:
            post = encoded_text.index(code)
            if post == 0:
                decoded_text = decoded_text + char
                encoded_text = encoded_text[len(code):]
        except:
            continue

print("\nResulting decoded file:\n",decoded_text)
input("\nPress any key to continue...\n")

print("Character frequency found in decompressed file",len(decoded_text)) 
input("\nReached the End of File\n")
