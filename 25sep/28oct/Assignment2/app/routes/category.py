from fastapi import APIRouter, HTTPException
from app.schemas import CategoryCreate, CategoryResponse
from app.crud import get_category, get_categories, create_category, update_category, delete_category

router = APIRouter(prefix="/categories", tags=["Categories"])


@router.get("/", response_model=list[CategoryResponse])
async def read_categories(skip: int = 0, limit: int = 20):
    return await get_categories(skip=skip, limit=limit)


@router.get("/{category_id}", response_model=CategoryResponse)
async def read_category(category_id: int):
    category = await get_category(category_id)
    if not category:
        raise HTTPException(status_code=404, detail="Category not found")
    return category


@router.post("/", response_model=CategoryResponse)
async def add_category(category: CategoryCreate):
    return await create_category(category)


@router.put("/{category_id}", response_model=CategoryResponse)
async def modify_category(category_id: int, category_update: CategoryCreate):
    return await update_category(category_id, category_update)


@router.delete("/{category_id}")
async def remove_category(category_id: int):
    await delete_category(category_id)
    return {"detail": "Category deleted successfully"}
