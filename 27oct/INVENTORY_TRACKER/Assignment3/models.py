class Product:
    def __init__(self,name,stock,price,location,tags):
        self.name = name
        self.stock = stock
        self.price = price
        self.location = location
        self.tags = tags       # tags = [] list

    def totalValue(self):
        return self.stock * self.price
    
    def description(self):
        return f"the product name is {self.name} having {self.stock} pieces at amount {self.price} each in {self.tags} category at location {self.location}"
    
class FoodProduct(Product):
    def __init__(self, name, stock, price, location, tags, expiry):
        super().__init__(name, stock, price, location, tags)  
        self.expiry = expiry


    def description(self):
        prev_description = super().description()
        return prev_description + f" with expiry date is {self.expiry}"