# GoogleMaps API Tutorial <img src=https://github.com/JA-Erosa/University/blob/master/Python/GoogleAPITutorial/gm.png width="100" height="100">

by Jorge Agustín Erosa, Jesús Catzín, Saúl Luna & Mario Morales 

====================================

Welcome! On this tutorial, we’ll learn how to use some functions of the Google Maps API. 

API stands for Application Programming Interface, which is basically a communication protocol between the server and the client. What does this mean? It’s a program designed to search for information (on a given address) depending on the client’s needs. 

# How to start in Google Cloud and get you “KEY”

Before starting to work with the API, we’ll a google cloud API key. For this, you need to access the website [GoogleCloud](https://cloud.google.com), then access the “Try it for free” option that displays on top of the screen. You’ll need to fill all the required information, which includes a credit or debit card. Don’t worry! Google won’t charge you for what we’re about to do, the key, has a limited amount of requests available but during the making of this particular project none of the team members reached it, the highest request number being 475, so under this number, Google shouldn't charge you any money.  

<img src="https://raw.githubusercontent.com/JA-Erosa/University/blob/master/Python/GoogleAPITutorial/key1.jpeg" width="400" height="250"> <img src="https://raw.githubusercontent.com/JA-Erosa/University/blob/master/Python/GoogleAPITutorial/key2.jpeg" width="400" height="250">
<img src="https://raw.githubusercontent.com/JA-Erosa/University/blob/master/Python/GoogleAPITutorial/key3.jpeg" width="400" height="250"> <img src="https://raw.githubusercontent.com/JA-Erosa/University/blob/master/Python/GoogleAPITutorial/key4.jpeg" width="400" height="250">

## Search Nearby

For the original documentation, click [here](https://developers.google.com/places/web-service/search)

First we define the "search_nearby_places" function and set the relevant parameters with the function we want to do according to the documentation. these are: 

   - key: is the API key we took out earlier. 

   - location: where we will enter longitude and latitude. 

   - radius: in which we will indicate the radius where we are going to abuse around the specified location. 

   - rankby: although this parameter is mandatory, we will not use it for now, so we will declare it as None. 
   
   - type: we indicate the type of establishment we're looking for (restaurant, hospital, etc.)

Then we use the URL "https://maps.googleapis.com/maps/api/place/nearbysearch/json" with which we will search for restaurants near the specified place in a certain radius and for this we will define the parameters as shown below.

```python
key='Add Your Key here'
def search_nearby_places(key,lat,lon,type1,rad,place=None):
    '''
    This function is used to find nearby places given a set of coordinates.    
    As an input, you should define the variables latitude, longitude, type(restaurant, hospital, etc.) and radius(in meters.
    This will return a json file with results as a general list with one element that would be the dictionary.
    '''
    #Endpoint
    endpoint_url='https://maps.googleapis.com/maps/api/place/nearbysearch/json?'
    parameters={
        'key': key, 
        'location': str(lat)+','+str(lon),
        'rankby':place,
        'radius':rad,
        'types': type1
    }
    #Request
    res=requests.get(endpoint_url, params=parameters)
    #Data Parsing
    results=json.loads(res.content)
    return(results)
 ```
 Then we'll call the function with the established parameters:
 ```python
 search_near=search_nearby_places(key='key',lat='latitude',lon='longitude',type1='typeOfEstablishment',rad=radiusofsearch)
 ```
 This will return a json with a ton of results with different fields.
 If we want to turn that json file into a pandas dataframe we can use the line:
 ```python
  result=pd.read_json(json.dumps(search_near['results']), orient='records')
  ```
  After this you'll have a nice table and you can decide which columns to use.

## Find Place ID

Now we will make a function to find the id of the place having the name and location 

First we define the "find_place" function and set the relevant parameters with the function we want to do according to the documentation. these are: 

   - Key: is the API key we took out earlier. 

   - locationbias: where we will enter longitude and latitude alongside a radius

   - input: Here we put the name of the place. 

   - inputtype: although this parameter is mandatory, we will not use it for now, so we will declare it as None. 

Then we use the URL “https://maps.googleapis.com/maps/api/place/findplacefromtext/json" with which we will search for place id using the name of the place and the location and for this we will define the parameters as shown in the picture 

```python
def find_id(key,name,lat,lon,rad='200',inputtype='textquery'): 
    ''' 
    This function is used to find the google place id. 
    As an input, you should define the variables name and latitude,longitude. 
    This will return a string that will be the place id. 
    ''' 

    #Endpoint 
    endpoint_url='https://maps.googleapis.com/maps/api/place/findplacefromtext/json?' 
    parameters={ 
        'key': key, 
        'input':name, 
        'inputtype':inputtype, 
        'locationbias': 'circle:'+rad+'@'+str(lat)+','+str(lon), 
    } 
    #Request 
    res=requests.get(endpoint_url, params=parameters) 
    #Data Parsing 
    results=json.loads(res.content) 
    results=results['candidates'][0]['place_id'] 
    return(results)     
```

## Find Place Reviews

For the original documentation, click [here](https://developers.google.com/places/web-service/details#PlaceDetailsResults)

Now we make a function with which we can find the reviews of the places in both English and Spanish 

First we define the "find_reviews" function and set the relevant parameters with the function we want to do according to the documentation. these are: 

   - Key: is the API key we took out earlier. 

   - language: Here we indicate the language in which we want the reviews, in this case, we enter “en” which is the key of English and “es” which is the key of Spanish. 

   - place_id: Here we put the id of the place that we want to see the reviews. 

Then we use the URL “https://maps.googleapis.com/maps/api/place/details/json"  with which we will search for the details of a place and for this we will define the parameters as shown below.

```python
 def find_reviews(key,pid,lan='es,en'): 
    ''' 
    This function is used to find the reviews of a given place (in english and in spanish). 
    As an input, you should define the place id. 
    This will return a json file with the reviews of a place. 
    ''' 
    #Endpoint 
    endpoint_url='https://maps.googleapis.com/maps/api/place/details/json?' 
    parameters={ 
        'key': key, 
        'place_id':pid, 
        'language_1':lan, 
    } 
    #Request 
    res=requests.get(endpoint_url, params=parameters) 
    #Data Parsing 
    results=json.loads(res.content) 
    results=results['result']['reviews'] 
    return(results) 
```

## Find similar places

Now we will do a function in order to find the top similar places given one of reference. 

First, we define the " find_similar " function and set the relevant parameters with the function we want to do according to the documentation. these are: 

   - Key: is the API key we took out earlier. 

   - input: here we will put the name 

   - inputtype: The type of input. This can be one of either textquery or phonenumber in this case we will use textquery 

   - fields: Defines the information to be returned, in this case we need the geometry which will give us the coordinates

   - radius: in which we will indicate the radius where we are going to use around the specified location, we'll set it to 300               meters. 

   - rankby: we'll rank by prominence (basically by a private google rating which involves a set of variables)

```python
def find_similar(key,name,it='textquery',rad=300, rank='prominence'): 
    '''
    In this function we'll use 2 api calls, one to get the coordinates of a place and another to get similar places 
    As an input you should put only the name of the place you want to search similar items for 
    As an output, we'll have a json file with a lot of places similars to the one you entered within a 300 meter radius 
    ''' 
    #Endpoint 
    endpoint_url1='https://maps.googleapis.com/maps/api/place/findplacefromtext/json?' 
    parameters1={ 
        'key': key, 
        'input':name, 
        'inputtype':it, 
        'fields':'geometry/location,type' 
    } 
    #Request 
    res1=requests.get(endpoint_url1, params=parameters1) 
    #Data Parsing 
    result=json.loads(res1.content) 
    resultslat=str(result['candidates'][0]['geometry']['location']['lat']) 
    resultslon=str(result['candidates'][0]['geometry']['location']['lng']) 
    types=result['candidates'][0]['types'][0] 
   #Endpoint 
    endpoint_url2='https://maps.googleapis.com/maps/api/place/nearbysearch/json?' 
    parameters2={ 
        'key': key, 
        'location':resultslat+','+resultslon, 
        'type':types, 
        'radius': rad, 
        'rankby': rank 
    } 
    #Request 
    res2=requests.get(endpoint_url2, params=parameters2) 
    #Data Parsing 
    results=json.loads(res2.content) 
    results=results['results'] 
    return(results) 
```


# GoogleMaps Python Library
====================================

Now we'll move to the easy part. Once we understand how the google api works, we can just install the google maps library for python. Already having pip, just do:
```
 $ pip install -U googlemaps
```
Or you can try with this if you have some problem:
```
python -m pip install googlemaps
```

After you have it, import the library into your file. We'll then declare our client variable, which will be the default calling method to access all other functions from the library.
We'll declare it by typing:
```python
import googlemaps
gmaps = googlemaps.Client(key='Add Your Key here')
```

## Search Nearby

This functions are easier to use, and the parameters are: 

   - Locations: Where you need to give the latitude and longitude in a tuple. In this case in the follow way: latlon=(20.988459,-89.736768)
   
   - Type1: Where this is the type of place you want to find (restaurant, school etc).
   
   - Rad: Where this is the radius to use 
   
   
```python
def search_nearby_places2(latlon,type1,rad):
    '''
    This function is used to find nearby places given a set of coordinates.
    
    As an input, you should define the variables latitude, longitude, type(restaurant, hospital, etc.) and radius(in meters.
    
    This will return a json file with results as a general list with one element that would be the dictionary.
    '''
    result=rls=gmaps.places_nearby(type=type1,location=latlon,radius=rad)
    results=result['results']
    
    return(results)
```
This is a function to make the dataframe more presentable:
```python
def search_nearby_places2_frame(frame):
    '''
    This function is to see the previous one in a nice dataframe.
    
    You just need to define the frame from the last function and enter it in this one.
    
    The output will be a dataframe with the columns Name, Place Id, Rating, Place Types, Total of User Ratings.     
    '''
    result=pd.read_json(json.dumps(frame),orient='records')
    results=result.drop(columns=['geometry', 'icon', 'opening_hours', 'photos', 'id', 'plus_code', 'reference', 'scope', 'vicinity'])
    return(results)
 ```
## Find Place ID

This use the follows parameters:

  - Locations: Where you need to give the latitude and longitude in a tuple. In this case in the follow way: latlon=(20.988459,-89.736768)
  
  - Name: This the name of the place you want to find 
  
  - Rad: The distance to find the places (in meters)
  
```python
def find_id2(latlon,name,rad=200):
    '''
    This function is used to find the google place id.
    
    As an input, you should define the variables name and latitude,longitude.
    
    This will return a string that will be the place id.
    '''
    result=rls=gmaps.places(query=name,location=latlon,radius=rad)
    results=result['results'][0]['place_id']
    
    return(results)
```

## Find Place Reviews

How to use the parameters:
 
   - pid2: This is the ID of the place that you need to give to the function.
   
   - lan: These are the the languages that is gonna (In this case we have specificate every one, you can change it, remember check the documentation) 
   
 
```python
def find_reviews2(pid,lan='en,es'):
    '''
    This function is used to find the reviews of a given place (in english and in spanish).
    
    As an input, you should define the place id.
    
    This will return a json file with the reviews of a place.
    '''
    results=gmaps.place(place_id=pid, language=lan)
    
    #Data Parsing
    
    results=results['result']['reviews']
    return(results)
```
This is the functions that take the file return and convert that to the dataframe
```python
def find_reviews2_frame(frame):
    '''
    This function is to see the previous one in a nice dataframe.
    
    You just need to define the frame from the last function and enter it in this one.
    
    The output will be a dataframe with the columns Author Name, Language, Rating, Text and Time.
    '''
    result=pd.read_json(json.dumps(frame),orient='records')
    results=result.drop(columns=['author_url', 'profile_photo_url', 'relative_time_description'])
    return(results)
    
``` 

## Find Similar Places

How to use the parameters:

   - name: This is the name of the place that we want to find 
   
   - Rad: The radius to find around to the place (in meters)
   
   - Rank: To rank according to the prominence
   
```python

def find_similar2(name,rad=300, rank='prominence'):
    '''
    In this function we'll use 3 libary calls, one to get the id of a place, another to get the coordinates of the place and another to get similar places
    
    As an input you should put only the name of the place you want to search similar items for
    
    As an output, we'll have a json file with a lot of places similars to the one you entered within a 300 meter radius
    '''
    result1=gmaps.find_place(name,input_type='textquery')
    result2=result1['candidates'][0]['place_id']
    result3=gmaps.place(result2)
    resultslat=result3['result']['geometry']['location']['lat']
    resultslon=result3['result']['geometry']['location']['lng']
    types1=result3['result']['types'][0]

    similar=gmaps.places_nearby(location=(resultslat,resultslon),radius=rad,rank_by=rank,type=types1)
    results=similar['results']
    return(results)
```
This is the function to make that in a dataframe:

```python
def find_similar2_frame(frame):
    '''
    Function just to get a nice data frame
    
    For an input you need the variable where the previous function is stored
    
    The output will be a nice data frame with columns Place Name, Plaece Id, Rating and Total of Use Ratings.
    '''
    result=pd.read_json(json.dumps(frame),orient='records')
    results=result.drop(columns=['id','types','geometry', 'icon', 'opening_hours', 'photos', 'plus_code', 'price_level', 'reference', 'scope', 'vicinity'])
    return(results)
    
```

# Observations

As we can observe it's way easier to use the google library. But one of the reasons was because we already read and understood how the backend API works. That way we understand how parameters are used, and how Google wants us to  declare it. Have fun using trying the API and remember if you use it for comercial purposes you'll need to pay google a monthly fee. It's a win-win situation.
Hope you enjoyed and understood this tutorial! 

# Resources

- [Get Started with Google Maps Platform](https://developers.google.com/maps/gmp-get-started) 
- [Places API](https://developers.google.com/places/)
- [Places Search](https://developers.google.com/places/web-service/search)
- [Place Details](https://developers.google.com/places/web-service/details#PlaceDetailsResults)
- [Google Maps Python Library](https://googlemaps.github.io/google-maps-services-python/docs/#module-googlemaps)
