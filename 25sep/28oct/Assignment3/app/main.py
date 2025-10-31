from fastapi import FastAPI
from app.prisma_client import connect, disconnect
from app.routes import file_operation

app = FastAPI()

#connect with db when app is started
@app.on_event("startup")
async def startup_event():
    await connect()

@app.on_event("shutdown")
async def shutdown_event():
    await disconnect()

app.include_router(file_operation.router, prefix="/api")
