from fastapi import APIRouter, HTTPException
from app.schemas import CompanyCreate, CompanyResponse
from app.crud import get_company, get_companies, create_company, update_company, delete_company

router = APIRouter(prefix="/companies", tags=["Companies"])


@router.get("/", response_model=list[CompanyResponse])
async def read_companies(skip: int = 0, limit: int = 20):
    return await get_companies(skip=skip, limit=limit)


@router.get("/{company_id}", response_model=CompanyResponse)
async def read_company(company_id: int):
    company = await get_company(company_id)
    if not company:
        raise HTTPException(status_code=404, detail="Company not found")
    return company


@router.post("/", response_model=CompanyResponse)
async def add_company(company: CompanyCreate):
    return await create_company(company)


@router.put("/{company_id}", response_model=CompanyResponse)
async def modify_company(company_id: int, company_update: CompanyCreate):
    return await update_company(company_id, company_update)


@router.delete("/{company_id}")
async def remove_company(company_id: int):
    await delete_company(company_id)
    return {"detail": "Company deleted successfully"}
