from fastapi import APIRouter, HTTPException, Query
from typing import Optional
from app.schemas import ProductCreate, ProductResponse
from app.crud import get_product, get_products, create_product, update_product, delete_product

router = APIRouter(prefix="/products", tags=["Products"])


@router.get("/", response_model=list[ProductResponse])
async def read_products(skip: int = 0, limit: int = 10):
    return await get_products(skip=skip, limit=limit)


@router.get("/{product_id}", response_model=ProductResponse)
async def read_product(product_id: int):
    product = await get_product(product_id)
    if not product:
        raise HTTPException(status_code=404, detail="Product not found")
    return product


@router.post("/", response_model=ProductResponse)
async def add_product(product: ProductCreate):
    return await create_product(product)


@router.put("/{product_id}", response_model=ProductResponse)
async def modify_product(product_id: int, product_update: ProductCreate):
    return await update_product(product_id, product_update)


@router.delete("/{product_id}")
async def remove_product(product_id: int):
    await delete_product(product_id)
    return {"detail": "Product deleted successfully"}
