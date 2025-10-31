from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from typing import List
from app import crud, schemas
from app.database import get_db
from app.logger import logger  # Import logger

router = APIRouter(prefix="/companies", tags=["Companies"])

@router.post("/", response_model=schemas.CompanyResponse)
def create_company(company: schemas.CompanyCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to create company: {company.name}")
    result = crud.create_company(db, company)
    logger.info(f"Company created successfully: {result.id}")
    return result

@router.get("/", response_model=List[schemas.CompanyResponse])
def read_companies(skip: int = 0, limit: int = 10, db: Session = Depends(get_db)):
    logger.info(f"Request to read companies with skip={skip} limit={limit}")
    result = crud.get_companies(db, skip, limit)
    logger.info(f"{len(result)} companies fetched")
    return result

@router.get("/{company_id}", response_model=schemas.CompanyResponse)
def read_company(company_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to read company with id: {company_id}")
    company = crud.get_company(db, company_id)
    if not company:
        logger.error(f"Company not found with id: {company_id}")
        raise HTTPException(status_code=404, detail="Company not found")
    logger.info(f"Company fetched successfully: {company.id}")
    return company

@router.put("/{company_id}", response_model=schemas.CompanyResponse)
def update_company(company_id: int, company_update: schemas.CompanyCreate, db: Session = Depends(get_db)):
    logger.info(f"Request to update company with id: {company_id}")
    result = crud.update_company(db, company_id, company_update)
    logger.info(f"Company updated successfully: {result.id}")
    return result

@router.delete("/{company_id}")
def delete_company(company_id: int, db: Session = Depends(get_db)):
    logger.info(f"Request to delete company with id: {company_id}")
    crud.delete_company(db, company_id)
    logger.info(f"Company deleted successfully: {company_id}")
    return {"detail": "Company deleted successfully"}
