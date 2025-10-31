#pydantic schemas for validation
from pydantic import BaseModel

# Company schemas
class CompanyBase(BaseModel):  #through basemodel we automatically validate or structure the data
    name: str
    location: str | None = None

class CompanyCreate(CompanyBase):
    pass

class CompanyResponse(CompanyBase):
    id: int      # each company has an unique id which we pass in response

    class Config:
        from_attributes = True       # SQLAlchemy ke ORM objects ko automatically Pydantic-compatible bana deta h


# Category schemas
class CategoryBase(BaseModel):
    name: str

class CategoryCreate(CategoryBase):
    pass

class CategoryResponse(CategoryBase):
    id: int

    class Config:
        from_attributes = True  

# Product schemas
class ProductBase(BaseModel):
    name: str
    price: float
    category_id: int
    company_id: int

class ProductCreate(ProductBase):
    pass

class ProductResponse(ProductBase):
    id: int
    category: CategoryResponse
    company: CompanyResponse

    class Config:
        from_attributes = True
