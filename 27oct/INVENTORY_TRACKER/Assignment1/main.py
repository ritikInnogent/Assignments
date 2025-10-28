products = []    #empty list to store product objects

lowStock = 5


def showMenu():
    print("\n ---Here is your Menu---")
    print("1. Show all products")
    print("2. Show low stock products")
    print("3. Add a new product")
    print("4. Update stock of a product")
    print("5. Delete a product")
    print("6. Show total value")
    print("7. Discount clearance products")
    print("8. Exit")


def showAllProducts():
    if not products:
        print("products stack is empty !! please enter products")
    for item in products:
        print(item)


def showLowStock():
    for item in products:
        if item['stock']< lowStock:
            print("Available product in low Stock")
            print(item)


def addProduct():
    name = input("product name:")
    stock = int(input("Stock number:"))
    price = float(input("price:"))
    location = input("location (eg: indore:) ")
    tags = input("tags (eg: electronics,clearance) ")
    #strip is used to remove spaces and split is used to seprate
    tags = [tag.strip() for tag in tags.split(",")]
    product = {
        "name": name,
        "stock": stock,
        "price": price,
        "location": location,
        "tags": tags
    }
    products.append(product)
    print("products added successfullly")


def updateStock():
    name = input("enter product name to update")
    for item in products:
        if item["name"].lower() == name.lower():
          item["stock"] = int(input("new updated stock:"))
          print("stock updated successfully")
          return
    print("Product not found !!")


def deleteProduct():
    name = input("enter product name to delete:")
    found = False
    for item in products:
        if item["name"].lower() == name.lower():
            products.remove(item)
            found = True
            print("Product deleted successfully")
            break
    if not found:
        print("product not found")


def totalValue():
    total = 0
    for item in products:
        total = total + item["stock"]*item["price"]
    print("Total value of all products is:",total)


def discountClearance():
    for item in products:
        if "clearance" in item["tags"]:
            item["price"] = item['price'] * 0.5
            print("Discount applied successfully")
            print(item)


while True:
    showMenu()
    choice = input("Enter your choice between 1 to 8 : ")

    if choice == "1":
        showAllProducts()
    elif choice == "2":
        showLowStock()
    elif choice == "3":
        addProduct()
    elif choice == "4":
        updateStock()
    elif choice == "5":
        deleteProduct()
    elif choice == "6":
        totalValue()
    elif choice == "7":
        discountClearance()
    elif choice == "8":
        print("your Program will exit !")
        break
    else:
        print("Please enter a valid number between (1 to 8)")

      






