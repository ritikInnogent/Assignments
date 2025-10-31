from fastapi import APIRouter, Depends, Query
from sqlalchemy.orm import Session
from typing import List, Optional
from app import crud, schemas
from app.database import get_db

router = APIRouter(prefix="/products", tags=["Products Search"])

@router.get("/search", response_model=List[schemas.ProductResponse])
def search_products(
    q: Optional[str] = Query(None, description="Search keyword for name/category"),
    #if we want only specific data then we remove optional in q
    company_id: Optional[int] = Query(None, description="Filter by company ID"),
    skip: int = 0,
    limit: int = 10,
    db: Session = Depends(get_db)
):
    return crud.search_products(db, q, company_id, skip, limit)
