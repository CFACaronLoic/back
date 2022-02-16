import json
import requests
from elasticsearch import Elasticsearch
import ssl

host = "http://localhost:9200"
headers = {'content-type': 'application/json', 'Accept-Charset': 'UTF-8'}
es = Elasticsearch(host)

#page a retourner en JSON
p = 1
#nombre de livre
l = 0
print("start traitement")
while l<=1664:
    page = str(p)
    url = "https://gutendex.com/books/?page="+page
    r = requests.get(url)
    jsonObj = r.json()
    result = jsonObj['results']

    for key in result:
        l = l + 1
        if(l<=1664):
            temp = ""
            if(key['authors']):
                for i in key['authors']:
                    temp = i['name']
                
                del key['authors']
                del key['translators']
                key["authors"] = temp
            resp = es.index(index="books", id=l,  document=key)
            print(resp['result'])
    p = p + 1
print("end traitement")