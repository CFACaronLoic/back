import json
import requests
from elasticsearch import Elasticsearch
import ssl

host = "http://localhost:9200"
headers = {'content-type': 'application/json', 'Accept-Charset': 'UTF-8'}
es = Elasticsearch(host)

#page a retourner en JSON
page = "1"

url = "https://gutendex.com/books/?page="+page

r = requests.get(url)

jsonObj = r.json()

result = jsonObj['results']

finalJSON = []

j = 0
for key in result:
    j = j + 1
    temp = ""
    if(key['authors']):
        for i in key['authors']:
            temp = i['name']
        
        del key['authors']
        del key['translators']
        key["authors"] = temp
    resp = es.index(index="books", id=j,  document=key)
    print(resp['result'])