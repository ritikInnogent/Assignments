import logging
from fastapi import HTTPException
from app.database import prisma
from app.schemas import CompanyCreate, ProductCreate, CategoryCreate

from app.logger import logger



# CRUD for Company
async def get_company(company_id: int):
    logger.info(f"Fetching company with id {company_id}")
    company = await prisma.company.find_unique(where={"id": company_id}, include={"products": True})
    return company

async def get_companies(skip: int = 0, limit: int = 20):
    logger.info(f"Fetching companies skip={skip}, limit={limit}")
    companies = await prisma.company.find_many(skip=skip, take=limit)
    return companies

async def create_company(company: CompanyCreate):
    logger.info(f"Trying to create company: {company.name}")
    existing = await prisma.company.find_unique(where={"name": company.name})
    if existing:
        logger.warning(f"Duplicate company creation attempt: {company.name}")
        raise HTTPException(status_code=400, detail="Company already exists")
    new_company = await prisma.company.create(data={"name": company.name, "location": company.location})
    logger.info(f"Company created successfully with id {new_company.id}")
    return new_company

async def update_company(company_id: int, company_update: CompanyCreate):
    logger.info(f"Updating company id {company_id}")
    company = await get_company(company_id)
    if not company:
        logger.error(f"Company not found for update id {company_id}")
        raise HTTPException(status_code=404, detail="Company not found")
    updated = await prisma.company.update(
        where={"id": company_id},
        data={"name": company_update.name, "location": company_update.location}
    )
    logger.info(f"Company updated successfully id {company_id}")
    return updated

async def delete_company(company_id: int):
    logger.info(f"Deleting company id {company_id}")
    company = await get_company(company_id)
    if not company:
        logger.error(f"Company not found for delete id {company_id}")
        raise HTTPException(status_code=404, detail="Company not found")
    await prisma.company.delete(where={"id": company_id})
    logger.info(f"Company deleted successfully id {company_id}")


# CRUD for Category
async def get_category(category_id: int):
    logger.info(f"Fetching category with id {category_id}")
    category = await prisma.category.find_unique(where={"id": category_id}, include={"products": True})
    return category

async def get_categories(skip: int = 0, limit: int = 20):
    logger.info(f"Fetching categories skip={skip}, limit={limit}")
    categories = await prisma.category.find_many(skip=skip, take=limit)
    return categories

async def create_category(category: CategoryCreate):
    logger.info(f"Trying to create category: {category.name}")
    existing = await prisma.category.find_unique(where={"name": category.name})
    if existing:
        logger.warning(f"Duplicate category creation attempt: {category.name}")
        raise HTTPException(status_code=400, detail="Category already exists")
    new_category = await prisma.category.create(data={"name": category.name})
    logger.info(f"Category created successfully with id {new_category.id}")
    return new_category

async def update_category(category_id: int, category_update: CategoryCreate):
    logger.info(f"Updating category id {category_id}")
    category = await get_category(category_id)
    if not category:
        logger.error(f"Category not found for update id {category_id}")
        raise HTTPException(status_code=404, detail="Category not found")
    updated = await prisma.category.update(
        where={"id": category_id},
        data={"name": category_update.name}
    )
    logger.info(f"Category updated successfully id {category_id}")
    return updated

async def delete_category(category_id: int):
    logger.info(f"Deleting category id {category_id}")
    category = await get_category(category_id)
    if not category:
        logger.error(f"Category not found for delete id {category_id}")
        raise HTTPException(status_code=404, detail="Category not found")
    await prisma.category.delete(where={"id": category_id})
    logger.info(f"Category deleted successfully id {category_id}")


# CRUD for Product
async def get_product(product_id: int):
    logger.info(f"Fetching product with id {product_id}")
    product = await prisma.product.find_unique(where={"id": product_id}, include={"company": True, "category": True})
    return product

async def get_products(skip: int = 0, limit: int = 10):
    logger.info(f"Fetching products skip={skip}, limit={limit}")
    products = await prisma.product.find_many(skip=skip, take=limit, include={"company": True, "category": True})
    return products

async def create_product(product: ProductCreate):
    logger.info(f"Trying to create product: {product.name} for company {product.company_id} and category {product.category_id}")
    existing = await prisma.product.find_first(
        where={
            "name": product.name,
            "companyId": product.company_id,
            "categoryId": product.category_id,
        }
    )
    if existing:
        logger.warning(f"Duplicate product creation attempt: {product.name} for company {product.company_id}")
        raise HTTPException(status_code=400, detail="Product already exists for this company and category")
    data = {
        "name": product.name,
        "price": product.price,
        "companyId": product.company_id,
        "categoryId": product.category_id,
    }
    new_product = await prisma.product.create(data=data, include={"company": True, "category": True})
    logger.info(f"Product created successfully id {new_product.id}")
    return new_product

async def update_product(product_id: int, product_update: ProductCreate):
    logger.info(f"Updating product id {product_id}")
    product = await get_product(product_id)
    if not product:
        logger.error(f"Product not found for update id {product_id}")
        raise HTTPException(status_code=404, detail="Product not found")
    updated = await prisma.product.update(
        where={"id": product_id},
        data={
            "name": product_update.name,
            "price": product_update.price,
            "companyId": product_update.company_id,
            "categoryId": product_update.category_id,
        },
        include={"company": True, "category": True}
    )
    logger.info(f"Product updated successfully id {product_id}")
    return updated

async def delete_product(product_id: int):
    logger.info(f"Deleting product id {product_id}")
    product = await get_product(product_id)
    if not product:
        logger.error(f"Product not found for delete id {product_id}")
        raise HTTPException(status_code=404, detail="Product not found")
    await prisma.product.delete(where={"id": product_id})
    logger.info(f"Product deleted successfully id {product_id}")
