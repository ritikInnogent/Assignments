from fastapi import FastAPI
from app.database import prisma
from app.routes import company, category, product

app = FastAPI()  # create fastapi app 

# event decorators means it start the app and connect prisma client to DB
@app.on_event("startup")
async def startup():
    await prisma.connect()

@app.on_event("shutdown")
async def shutdown():
    await prisma.disconnect()

app.include_router(company.router)
app.include_router(category.router)
app.include_router(product.router)
