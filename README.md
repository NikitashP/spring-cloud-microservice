# spring-cloud-microservice
Simple implementation of Microservices Using Spring Cloud.

1. In the parent folder: spring-cloud-microservice do: mvn clean install

<Perform Steps 2-6 in parallel on individual command prompts>

2. Launch config-microservice
	1. mvn spring-boot:run
	2. this will launch on port 8888
3. Launch discovery-microservice
	1. mvn spring-boot:run 
	2. this will launch on port 8761

4. Launch api-gateway-microservice
	1. mvn spring-boot:run
	2. this will launch on port 10000

5. Launch product-catalog-microservice
	1. mvn spring-boot:run
		this will launch on port 8081
	2. mvn spring-boot:run -Drun.jvmArguments='-Dserver.port=8900'
		this will launch on port 8900
		
6. Launch price-microservice
	1. mvn spring-boot:run
		this will launch on port 8080

7. Load : http://localhost:8761/
	1. you should be able to see:
	![image](https://cloud.githubusercontent.com/assets/11174625/25307809/5d22238c-27c5-11e7-93b7-6f2050b4424a.png)

8. You can Spawn multiple instances of your microservice using the command 5.2. All these instances must show up in the
above console

9. All calls to actual services will go via API Gateway, which will act like a proxy to the microservice having business logic.

10. Working with Product Catalog:

- Adding product to the catalog

curl -i -X POST -H "Content-Type:application/json" -d "{  \"productName\" :
\"cherry\",  \"productType\" : \"fruit\" }" http://localhost:10000/productcatalogservice/catalog
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
X-Application-Context: gateway:10000
Location: http://localhost:10000/productcatalogservice/catalog/3
Date: Sat, 22 Apr 2017 20:42:25 GMT
Content-Type: application/hal+json;charset=UTF-8
Transfer-Encoding: chunked

{
  "productName" : "cherry",
  "productType" : "fruit",
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog/3"
    },
    "product" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog/3"
    }
  }
}

-- View all products on the catalog

curl http://localhost:10000/productcatalogservice/catalog
{
  "_embedded" : {
    "products" : [ {
      "productName" : "apple",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/1"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/1"
        }
      }
    }, {
      "productName" : "apple",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/2"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/2"
        }
      }
    }, {
      "productName" : "cherry",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog"
    },
    "profile" : {
      "href" : "http://localhost:10000/productcatalogservice/profile/catalog"
    },
    "search" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog/search"
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 3,
    "totalPages" : 1,
    "number" : 0
  }
}

-- find specific item on the catalog

curl http://localhost:10000/productcatalogservice/catalog/search/findByProductName?name=cherry
{
  "_embedded" : {
    "products" : [ {
      "productName" : "cherry",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog/search/findByProductName?name=cherry"
    }
  }
}

curl http://localhost:10000/productcatalogservice/catalog/search/findByProductType?type=fruit
{
  "_embedded" : {
    "products" : [ {
      "productName" : "apple",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/1"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/1"
        }
      }
    }, {
      "productName" : "apple",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/2"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/2"
        }
      }
    }, {
      "productName" : "cherry",
      "productType" : "fruit",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        },
        "product" : {
          "href" : "http://localhost:10000/productcatalogservice/catalog/3"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/productcatalogservice/catalog/search/findByProductType?type=f
ruit"
    }
  }
}

11. Working with Pricing service:

--Adding price of a product

curl -i -X POST -H "Content-Type:application/json" -d "{  \"productName\" :
\"cherry\",  \"price\" : \"10.00\" }" http://localhost:10000/pricingservice/price
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
X-Application-Context: gateway:10000
Location: http://localhost:10000/pricingservice/price/2
Date: Sat, 22 Apr 2017 20:40:29 GMT
Content-Type: application/hal+json;charset=UTF-8
Transfer-Encoding: chunked

{
  "productName" : "cherry",
  "price" : "10.00",
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/pricingservice/price/2"
    },
    "price" : {
      "href" : "http://localhost:10000/pricingservice/price/2"
    }
  }
}

-- getting price of the item

curl http://localhost:10000/pricingservice/price/search/findByProductName?name=cherry
{
  "_embedded" : {
    "price" : [ {
      "productName" : "cherry",
      "price" : "10.00",
      "_links" : {
        "self" : {
          "href" : "http://localhost:10000/pricingservice/price/2"
        },
        "price" : {
          "href" : "http://localhost:10000/pricingservice/price/2"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:10000/pricingservice/price/search/findByProductName?name=cherry"
    }
  }
}
