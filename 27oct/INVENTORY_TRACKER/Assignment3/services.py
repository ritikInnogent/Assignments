products= []     #list of products
lowStock = 5

def showAllProducts():
    if not products:
        print("products stack is empty !! please enter products")
    for product in products:
        print(product.description())


def showLowStock():
    for product in products:
        if product.stock < lowStock:
            print("Available product in low Stock")
            print("Low Stock",product.description())


def addProduct(product):
    products.append(product)
    print("products added successfullly",product.description())


def updateStock(productName , productStock):
    for product in products:
        if product.name.lower() == productName.lower():
          product.stock = productStock
          print("stock updated in" , product.name , "to" , productStock)
          return
    print("Product not found !!")


def delete_product(productName):
    global products  
    found = False  
    for product in products:
        if product.name.lower() == productName.lower():
            products.remove(product) 
            found = True
            print("Product deleted successfully")
            break  
    if not found:
        print("Product not found, please enter a valid product name")


def total_value():
    total = 0  
    for product in products:
        totalProduct = product.value()  
        total = total + totalProduct
    print("Total value of all product is", total)

def discount_clearance():
    for product in products:
        if "clearance" in product.tags:
            product.price = product.price * 0.5
            print(f"Discount applied to {product.name} and the new price is {product.price}")