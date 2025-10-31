from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from typing import List
from app import crud, schemas
from app.database import get_db
from app.logger import logger  # Import logger

router = APIRouter(prefix="/categories", tags=["Categories"])

@router.post("/", response_model=schemas.CategoryResponse)
def create_category(category: schemas.CategoryCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to create category: {category.name}")
    result = crud.create_category(db, category)
    logger.info(f"Category created successfully: {result.id}")
    return result

@router.get("/", response_model=List[schemas.CategoryResponse])
def read_categories(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    logger.info(f"Request to read categories with skip={skip} and limit={limit}")
    result = crud.get_categories(db, skip, limit)
    logger.info(f"{len(result)} categories fetched")
    return result

@router.get("/{category_id}", response_model=schemas.CategoryResponse)
def read_category(category_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to read category with id: {category_id}")
    category = crud.get_category(db, category_id)
    if not category:
        logger.error(f"Category not found with id: {category_id}")
        raise HTTPException(status_code=404, detail="Category not found")
    logger.info(f"Category fetched successfully: {category.id}")
    return category

@router.put("/{category_id}", response_model=schemas.CategoryResponse)
def update_category(category_id: int, category_update: schemas.CategoryCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to update category with id: {category_id}")
    result = crud.update_category(db, category_id, category_update)
    logger.info(f"Category updated successfully: {result.id}")
    return result

@router.delete("/{category_id}")
def delete_category(category_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to delete category with id: {category_id}")
    crud.delete_category(db, category_id)
    logger.info(f"Category deleted successfully: {category_id}")
    return {"detail": "Category deleted successfully"}
