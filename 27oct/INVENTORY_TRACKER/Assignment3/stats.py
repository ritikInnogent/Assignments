import numpy as np

def showStats(products):

    prices = []
    stocks = []
    for product in products:
        prices.append(product.price)
        stocks.append(product.stock)   


    # we simply convert list into numpy array for using its functionalities
    pricesArray = np.array(prices)
    stocksArray = np.array(stocks)

    averagePrice  = np.mean(pricesArray)

    maximumPrice = np.max(pricesArray)

    totalStock = np.sum(stocksArray)

    InventoryValue = stocksArray * pricesArray

    print(f"Average price of items:",averagePrice)
    print(f"Most expensive item price:",maximumPrice)
    print(f"Total count of all items in stock:",totalStock)
    
    print("Total value per product:")
    for product, Value in zip(products, InventoryValue):
        print("-", product.name , " : " ,Value)


def tagStats(products,tag):
    #first we simply filtered the products having the given tag
    filtered = []
    for product in products:
        if tag in product.tags:
            filtered.append(product)
            
        if not filtered:
            print("No product found with this tag")
            
    prices = []
    stocks = []

    for item in filtered:
        prices.append(item.price)
        stocks.append(item.stock) 
        
    pricesArray = np.array(prices)
    stocksArray = np.array(stocks) 
        
    totalValues =  pricesArray * stocksArray 
       
    if len(pricesArray) > 0:
       averagePrice = np.mean(prices)
    else:
       averagePrice = 0

    totalSumValue = np.sum(totalValues)

    print("Average price: ",averagePrice)
    print("Total inventory value: ", totalSumValue)




