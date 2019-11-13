#Importing the necessary libraries
import pandas as pd

#Importing the file, separating on whitespaces, skipping row with --- and declaring -9999 as NaN 
df1 = pd.read_csv('mex.txt',sep="\s+",skiprows=[1],na_values=['-9999'])

# Resetting indexes so we can fix the columns the way we want them to look like
df2=df1.reset_index()

# Completing columns and dropping the ones we don't need anymore
df2["STATION_NAME"] = df2["level_1"] + ' ' + df2["STATION"] + ' ' + df2["STATION_NAME"]
df2['STATION']=df2['level_0']
df2=df2.drop(columns=['level_0','level_1'])

# Creating 2 new columns with only the year and the month so we can work easier
df2['DATE']=df2['DATE'].astype(str)
df2['YEAR']=df2['DATE'].str.slice(start=0, stop=4)
df2['MONTH']=df2['DATE'].str.slice(start=4, stop=6)
df2['TAVG_C']=((df2['TAVG']-32) * (5/9))

# Grouping firstly by year and then by month and creating average values out of it (date column disappears)
df3=df2.groupby(['YEAR','MONTH']).mean()

#grouping by month and calculating the ref temp in a separate dataframe and then turning it into a list 
df4=df2.groupby(['MONTH']).mean()
df4['Ref_Temp']=df4['TAVG_C']
df4=df4.drop(columns=['ELEVATION', 'LATITUDE', 'LONGITUDE', 'PRCP', 'TMAX', 'TMIN', 'TAVG','TAVG_C'])
rtlist=df4['Ref_Temp'].tolist()

# Resetting our main dataframe's indexes so the year and month indexes are normal again
df3=df3.reset_index()

# Allocating the ref_temp from our list into its corresponding rows
df3.loc[df3['MONTH']=='01', 'Ref_Temp'] = rtlist[0]
df3.loc[df3['MONTH']=='02', 'Ref_Temp'] = rtlist[1]
df3.loc[df3['MONTH']=='03', 'Ref_Temp'] = rtlist[2]
df3.loc[df3['MONTH']=='04', 'Ref_Temp'] = rtlist[3]
df3.loc[df3['MONTH']=='05', 'Ref_Temp'] = rtlist[4]
df3.loc[df3['MONTH']=='06', 'Ref_Temp'] = rtlist[5]
df3.loc[df3['MONTH']=='07', 'Ref_Temp'] = rtlist[6]
df3.loc[df3['MONTH']=='08', 'Ref_Temp'] = rtlist[7]
df3.loc[df3['MONTH']=='09', 'Ref_Temp'] = rtlist[8]
df3.loc[df3['MONTH']=='10', 'Ref_Temp'] = rtlist[9]
df3.loc[df3['MONTH']=='11', 'Ref_Temp'] = rtlist[10]
df3.loc[df3['MONTH']=='12', 'Ref_Temp'] = rtlist[11]

# Creating the column diff under which we will operate
df3['Diff']=df3['Ref_Temp']-df3['TAVG_C']

# New clean datafram to work with only some values
monthlyMex=df3.drop(columns=['LATITUDE', 'LONGITUDE', 'PRCP', 'ELEVATION','TMAX', 'TMIN', 'TAVG','SNWD'])
monthlyMex=monthlyMex.rename(columns={"Diff": "Diff_M","YEAR": "YEAR", "TAVG_C": "TAVG_C_M", "Ref_Temp": "Ref_Temp_M"})
monthlyMex['MONTH']=monthlyMex['MONTH'].astype(str)
monthlyMex['YEAR']=monthlyMex['YEAR'].astype(str)
monthlyMex["DATE_m"] = monthlyMex["YEAR"] + monthlyMex['MONTH']
monthlyMex["DATE_m"]=monthlyMex["DATE_m"].astype(int)
monthlyMex['MONTH']=monthlyMex['MONTH'].astype(int)
monthlyMex['YEAR']=monthlyMex['YEAR'].astype(int)

# Defining Overall Outliers, these are months that were either colder or hotter than the designated thresholds. 
upper_threshold=monthlyMex['TAVG_C_M'].mean()+(2*monthlyMex['TAVG_C_M'].std())
lower_threshold=monthlyMex['TAVG_C_M'].mean()-(2*monthlyMex['TAVG_C_M'].std())
monthlyMex['OOutliers']=None
monthlyMex.loc[(monthlyMex['TAVG_C_M']>upper_threshold) | (monthlyMex['TAVG_C_M']<(lower_threshold)),'OOutliers'] = True
monthlyMex.loc[(monthlyMex['TAVG_C_M']>=(lower_threshold)) & (monthlyMex['TAVG_C_M']<=(upper_threshold)) ,'OOutliers'] = False
print("The upper threshold to detect anomalies was set at: ", upper_threshold)
print("The lower threshold to detect anomalies was set at: ", lower_threshold)
OverallOutliers=monthlyMex.loc[monthlyMex['OOutliers']==True]
print(OverallOutliers)

#Reading the previously created Sodankyla file from our other python file
monthlyHelsinkiLokka= pd.read_csv('SodankylaHelsinkiDays.csv')

#Merging the Mex and Sodankyla files by date (yearmo)
three=pd.merge(monthlyHelsinkiLokka,monthlyMex, on = 'DATE_m')
three=three.drop(columns=['MONTH_x', 'Unnamed: 0', 'DATE_m'])
three=three.rename(columns={'MONTH_y':'MONTH'})
three['MONTH']=three['MONTH'].astype(int)

#Getting the mean by month of all 3 stations (Mex Sodankyla and Helsinki)
final=three.groupby(['MONTH']).mean()

# Grouping the info by month, and getting the differences in temperature
final['Monthly_Diff_MH']=final['TAVG_C_M']-final['TAVG_C_H']
final['Monthly_Diff_ML']=final['TAVG_C_M']-final['TAVG_C_L']
final=final.reset_index()

# Printing the differences between Mexico City and Helsinki during the summer months
june=final['TAVG_C_M'][5]-final['TAVG_C_H'][5]
july=final['TAVG_C_M'][6]-final['TAVG_C_H'][6]
august=final['TAVG_C_M'][7]-final['TAVG_C_H'][7]
print("June temperatures between Mexico City and Helsinki (1975-2016) have an average difference in temperature of ",june)
print("July temperatures between Mexico City and Helsinki (1975-2016) have an average difference in temperature of ",july)
print("August temperatures between Mexico City and Helsinki (1975-2016) have an average difference in temperature of ",august)

# Printing the differences between Mexico City and Sodankyla during the summer months
june=final['TAVG_C_M'][5]-final['TAVG_C_L'][5]
july=final['TAVG_C_M'][6]-final['TAVG_C_L'][6]
august=final['TAVG_C_M'][7]-final['TAVG_C_L'][7]
print("June temperatures between Mexico City and Sodankyla (1975-2016) have an average difference in temperature of ",june)
print("July temperatures between Mexico City and Sodankyla (1975-2016) have an average difference in temperature of ",july)
print("August temperatures between Mexico City and Sodankyla (1975-2016) have an average difference in temperature of ",august)

# Printing the differences between Mexico City and Helsinki during the summer
summercomp=final.loc[(final['MONTH']>5) & (final['MONTH']<9)]
summer=(summercomp['TAVG_C_M'].mean())-(summercomp['TAVG_C_H'].mean())
summer1=(summercomp['TAVG_C_M'].mean())-(summercomp['TAVG_C_L'].mean())
print("Summer temperatures between Mexico City and Helsinki (1975-2016) have an average difference in temperature of ", summer)
print("Summer temperatures between Mexico City and Sodankyla (1975-2016) have an average difference in temperature of ", summer1)

# Saving file to a csv
final.to_csv(r'MexvsHelsinkiLokka.csv')

# Summer averages/means
summerM=summercomp['TAVG_C_M'].mean()
summerH=summercomp['TAVG_C_H'].mean()
summerL=summercomp['TAVG_C_L'].mean()
print("Summer average temperature for Mexico City (1975-2016) was ",summerM)
print("Summer average temperature for Helsinki (1975-2016) was ",summerH)
print("Summer average temperature for Lokka (1975-2016) was ",summerL)

# Summer std deviation
summerM=summercomp['TAVG_C_M'].std()
summerH=summercomp['TAVG_C_H'].std()
summerL=summercomp['TAVG_C_L'].std()
print("Summer standard deviation in temperature for Mexico City (1975-2016) was ",summerM)
print("Summer standard deviation in temperature for Helsinki (1975-2016) was ",summerH)
print("Summer standard deviation in temperature for Lokka (1975-2016) was ",summerL)