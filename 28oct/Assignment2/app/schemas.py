from pydantic import BaseModel, Field
from typing import Optional, List


# Company schemas
class CompanyBase(BaseModel):
    name: str
    location: Optional[str] = None


class CompanyCreate(CompanyBase):
    pass


class CompanyResponse(CompanyBase):
    id: int
    products: Optional[List["ProductResponse"]] = []  # List of products matlb one-to-many relation

    class Config:
        from_attributes = True  


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
    category_id: int = Field(..., alias="categoryId")  # add alias to map Prisma camelCase fields
    company_id: int = Field(..., alias="companyId")


class ProductCreate(ProductBase):
    pass


class ProductResponse(ProductBase):
    id: int

    category: Optional["CategoryResponse"] = None
    company: Optional["CompanyResponse"] = None

    class Config:
        from_attributes = True
        validate_by_name = True  # allow population by alias or fieldname means both camelcase and snakecase accepteed
