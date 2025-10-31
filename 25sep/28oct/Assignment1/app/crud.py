import logging
from sqlalchemy.orm import Session
from app import models, schemas
from fastapi import HTTPException
from sqlalchemy import or_
from typing import List, Optional

from app.logger import logger # Logger object


# CRUD for Company 
def get_company(db: Session, company_id: int):
    logger.info(f"Fetching company with id {company_id}")
    return db.query(models.Company).filter(models.Company.id == company_id).first()

def get_companies(db: Session, skip: int = 0, limit: int = 20):
    logger.info(f"Fetching all companies with skip={skip} limit={limit}")
    return db.query(models.Company).offset(skip).limit(limit).all()


def create_company(db: Session, company: schemas.CompanyCreate):
    logger.info(f"Trying to create company: {company.name}")
    if db.query(models.Company).filter(models.Company.name == company.name).first():
        logger.warning(f"Duplicate company creation attempt: {company.name}")
        raise HTTPException(status_code=400, detail="Your given Company already exists")
    newCompany = models.Company(name=company.name, location=company.location)
    db.add(newCompany)
    db.commit()
    db.refresh(newCompany)
    logger.info(f"Company created successfully: {newCompany.id}")
    return newCompany


def update_company(db: Session, company_id: int, company_update: schemas.CompanyCreate):
    logger.info(f"Updating company id {company_id}")
    company = get_company(db, company_id)
    if not company:
        logger.error(f"Company not found for update id={company_id}")
        raise HTTPException(status_code=404, detail="Given Company is not found")
    company.name = company_update.name
    company.location = company_update.location
    db.commit()
    db.refresh(company)
    logger.info(f"Company updated successfully with id {company_id}")
    return company


def delete_company(db: Session, company_id: int):
    logger.info(f"Deleting company id {company_id}")
    company = get_company(db, company_id)
    if not company:
        logger.error(f"Company not found for delete id={company_id}")
        raise HTTPException(status_code=404, detail="Company not found")
    db.delete(company)
    db.commit()
    logger.info(f"Company deleted successfully id {company_id}")


# CRUD for Category 
def get_category(db: Session, category_id: int):
    logger.info(f"Fetching category with id {category_id}")
    return db.query(models.Category).filter(models.Category.id==category_id).first()


def get_categories(db: Session, skip: int = 0, limit: int = 10):
    logger.info(f"Fetching categories with skip={skip} limit={limit}")
    return db.query(models.Category).offset(skip).limit(limit).all()


def create_category(db: Session, category: schemas.CategoryCreate):
    logger.info(f"Trying to create category: {category.name}")
    if db.query(models.Category).filter(models.Category.name == category.name).first():
        logger.warning(f"Duplicate category creation attempt: {category.name}")
        raise HTTPException(status_code=400, detail="Given Category already exists")
    new_category = models.Category(name=category.name)
    db.add(new_category)
    db.commit()
    db.refresh(new_category)
    logger.info(f"Category created successfully")
    return new_category


def update_category(db: Session, category_id: int, category_update: schemas.CategoryCreate):
    logger.info(f"Updating category id {category_id}")
    category = get_category(db, category_id)
    if not category:
        logger.error(f"Category not found for update id={category_id}")
        raise HTTPException(status_code=404, detail="Given Category not found")
    category.name = category_update.name
    db.commit()
    db.refresh(category)
    logger.info(f"Category updated successfully id {category_id}")
    return category


def delete_category(db: Session, category_id: int):
    logger.info(f"Deleting category id {category_id}")
    category = get_category(db, category_id)
    if not category:
        logger.error(f"Category not found for delete id={category_id}")
        raise HTTPException(status_code=404, detail="Given Category not found")
    db.delete(category)
    db.commit()
    logger.info(f"Category deleted successfully id {category_id}")


# CRUD for Product
def get_product(db: Session, product_id: int):
    logger.info(f"Fetching product with id {product_id}")
    return db.query(models.Product).filter(models.Product.id == product_id).first()


def get_products(db: Session, skip: int = 0, limit: int = 10):
    logger.info(f"Fetching products with skip={skip} limit={limit}")
    return db.query(models.Product).offset(skip).limit(limit).all()


def create_product(db: Session, product: schemas.ProductCreate):
    logger.info(f"Trying to create product: {product.name} for company {product.company_id}")
    existing = db.query(models.Product).filter(
        models.Product.name == product.name,
        models.Product.company_id == product.company_id
    ).first()
    if existing:
        logger.warning(f"Duplicate product creation attempt: {product.name} for company {product.company_id}")
        raise HTTPException(status_code=400, detail="Product already exists for this company")
    new_product = models.Product(
        name=product.name,
        price=product.price,
        company_id=product.company_id,
        category_id=product.category_id
    )
    db.add(new_product)
    db.commit()
    db.refresh(new_product)
    logger.info(f"Product created successfully id {new_product.id}")
    return new_product


def update_product(db: Session, product_id: int, product_update: schemas.ProductCreate):
    logger.info(f"Updating product id {product_id}")
    product = get_product(db, product_id)
    if not product:
        logger.error(f"Product not found for update id={product_id}")
        raise HTTPException(status_code=404, detail="Given Product not found")
    product.name = product_update.name
    product.price = product_update.price
    product.company_id = product_update.company_id
    product.category_id = product_update.category_id
    db.commit()
    db.refresh(product)
    db.refresh(product)
    logger.info(f"Product updated successfully id {product_id}")
    return product

def delete_product(db: Session, product_id: int):
    logger.info(f"Deleting product id {product_id}")
    product = get_product(db, product_id)
    if not product:
        logger.error(f"Product not found for delete id={product_id}")
        raise HTTPException(status_code=404, detail="Product not found")
    db.delete(product)
    db.commit()
    logger.info(f"Product deleted successfully id {product_id}")

#search product based on product name and category name or option company_id  
def search_products(db: Session, q: Optional[str], company_id: Optional[int], skip: int = 0, limit: int = 10) -> List[models.Product]:
    logger.info(f"Searching products with q={q}, company_id={company_id}, skip={skip}, limit={limit}")
    query = db.query(models.Product)

    if q:
        search_pattern = f"%{q}%"   # % is wildcard character in SQL
        query = query.filter(
            or_(
                models.Product.name.ilike(search_pattern),   # not case sensitive
                models.Product.category.has(models.Category.name.ilike(search_pattern)),  
                # has se hum related table ke column ko search kr skte hai 
            )
        )

    if company_id:
        query = query.filter(models.Product.company_id == company_id)
        
    results = query.offset(skip).limit(limit).all()
    logger.info(f"Search found {len(results)} results")
    return results
