from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from typing import List
from app import crud, schemas
from app.database import get_db
from app.logger import logger  # import logger to use inside this file

router = APIRouter(prefix="/products", tags=["Products"])

@router.post("/", response_model=schemas.ProductResponse)
def create_product(product: schemas.ProductCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to create product: {product.name}")
    result = crud.create_product(db, product)
    logger.info(f"Product created successfully: {result.id}")
    return result

@router.get("/", response_model=List[schemas.ProductResponse])
def read_products(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    logger.info(f"Request to read products with skip={skip} limit={limit}")
    result = crud.get_products(db, skip, limit)
    logger.info(f"{len(result)} products fetched")
    return result

@router.get("/{product_id}", response_model=schemas.ProductResponse)
def read_product(product_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to read product with id: {product_id}")
    product = crud.get_product(db, product_id)
    if not product:
        logger.error(f"Product not found with id: {product_id}")
        raise HTTPException(status_code=404, detail="Product not found")
    logger.info(f"Product fetched successfully: {product.id}")
    return product

@router.put("/{product_id}", response_model=schemas.ProductResponse)
def update_product(product_id: int, product_update: schemas.ProductCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to update product with id: {product_id}")
    result = crud.update_product(db, product_id, product_update)
    logger.info(f"Product updated successfully: {result.id}")
    return result

@router.delete("/{product_id}")
def delete_product(product_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to delete product with id: {product_id}")
    crud.delete_product(db, product_id)
    logger.info(f"Product deleted successfully: {product_id}")
    return {"detail": "Product deleted successfully"}
