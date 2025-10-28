from models import Product, FoodProduct
import services
import stats

def showMenu():
    print("\n ---Here is your Menu--- ")
    print("1. Show all products")
    print("2. Show low stock products")
    print("3. Add a new product")
    print("4. Update stock of a product")
    print("5. Delete a product")
    print("6. Show total value")
    print("7. Discount clearance products")
    print("8. Show statistics report")
    print("9. Exit")


def getProductInput():
    name = input("product name:")
    stock = int(input("Stock number:"))
    price = float(input("price:"))
    location = input("location (eg: indore:) ")
    tags = input("tags (eg: electronics,clearance) ")
    #strip is used to remove spaces and split is used to seprate
    tags = [tag.strip() for tag in tags.split(",")]
    return name, stock, price, location, tags


def main():
    while True:
       showMenu()
       choice = input("Enter your choice between 1 to 8 : ")

       if choice == "1":
           services.showAllProducts()

       elif choice == "2":
           services.showLowStock() 

       elif choice == "3":
           isFood = input("Your product type is food? (y/n)").lower()
           name, stock, price, location, tags = getProductInput()
           if isFood == "y":
                expiry = input("Enter your expiry date : (YYYY-MM-DD)")
                product = FoodProduct(name, stock, price, location, tags, expiry)
           else:
                product = Product(name, stock, price, location, tags)
           services.addProduct(product)

       elif choice == "4":
           productName = input("enter product name which you wants to update :")
           productStock = int(input("Enter new updated stock"))
           services.updateStock(productName,productStock)

       elif choice == "5":
           productName = input("enter product name which you wants to delete :")
           services.deleteProduct(productName)

       elif choice == "6":
           services.totalValue()

       elif choice == "7":
           services.discountClearance()
       elif choice == "8":
           stats.showStats(services.products)  
           
           tag = input("Enter tag for filtered stats (e.g., clearance): ").strip().lower()
           stats.tagStats(services.products, tag)
       elif choice == "9":
           print("your Program will exit!!")
           break
       else:
           print("Please enter a valid number between (1 to 8)")


#run main function only when is in this file
if __name__ == "__main__":
    main()
