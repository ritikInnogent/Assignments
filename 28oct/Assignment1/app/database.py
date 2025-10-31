#Database connection setup

from sqlalchemy import create_engine
from dotenv import load_dotenv
from sqlalchemy.orm import sessionmaker,declarative_base
import os   


load_dotenv()   # this function load enviroment variables from .env files

DATABASE_URL = os.getenv("DATABASE_URL")  #here we get databaseurl form enviroment variables


#now create sqlalchemy engine to connect with database 
engine = create_engine(DATABASE_URL)

SessionLocal = sessionmaker(autocommit=False , autoflush= False, bind=engine)  #here we create a configured session class

Base = declarative_base()   #base class for Sqlalchemy models


#now we create an dependency function to get DB session inside API routes
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

