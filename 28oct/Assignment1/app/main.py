#main application entry point
from fastapi  import FastAPI 
from app.database import Base, engine
from app.routers import company, category, product, search

if __name__ == "__main__":
  Base.metadata.create_all(bind=engine)
  print("created all tables")
#create all tables (company , product , category) in database
app = FastAPI()
app.include_router(company.router)
app.include_router(category.router)
app.include_router(product.router)
app.include_router(search.router)
