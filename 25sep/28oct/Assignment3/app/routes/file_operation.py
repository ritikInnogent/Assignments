from fastapi import APIRouter, File, UploadFile, HTTPException
import pandas as pd
from app.prisma_client import prisma
from app.models import ProductCreate



from fastapi import Body


from fastapi.responses import StreamingResponse
import io

router = APIRouter()

@router.post("/uploadCSV")
async def upload_csv(file: UploadFile = File(...)):#tells fastapi that parameter comes from file uploaded form and ...ellipsis means required
  
    if not file.filename.endswith('.csv'):
        raise HTTPException(status_code=400, detail="Please upload only CSV files")

   
    data = pd.read_csv(file.file)
    #we use pandas method to read data from uploaded csv file and convert into dataframes
    #file.file is file object jo panda ko input dete h

    for index, row in data.iterrows():
        #  we assume that CSV mein ye columns hain: name, category, price, company_id
        product_data = ProductCreate(
            name=row['name'],
            price=float(row['price']),
            category=row['category'] 
        )
        try:
            await prisma.product.create(data=product_data.dict())
            #save in database with create object ( prisma ORM se)
        except Exception as e:
            # Agar koi error aaye, toh return karo error message
            raise HTTPException(status_code=400, detail=f"Error saving row {index}: {str(e)}")

    return {"message": "CSV file processed and data saved successfully"} # without error data save successfully






@router.post("/addProduct")
async def add_product(product: ProductCreate = Body(...)):
     #send data in json format, matchs format of productCreate
    try:
        saved_product = await prisma.product.create(data=product.dict())
         # convert pydantic model obj into dic so prisma can understand
        return {"message": "Product added successfully", "product": saved_product}
    except Exception as e:
        print(f"Error details: {e}")  # Console me error print hoga
        raise HTTPException(status_code=400, detail=f"Error adding product: {str(e)}")








@router.get("/downloadProducts")
async def download_products(format: str = 'csv'):
    products = await prisma.product.find_many()

    # Products ko list of dicts mein convert karte hain
    data = [{
        "name": p.name,
        "category": p.category,
        "price": p.price,
    } for p in products]

    # pandas creates dataframes df
    df = pd.DataFrame(data)

    # through this we make byte stream buffer in Memory jisme file store hote h
    buffer = io.BytesIO()
    
    if format == 'csv':
        # CSV file buffer m bina index ke write hote h
        df.to_csv(buffer, index=False)
        # Seek karke buffer start position pe le aate h because pure file read ho 
        buffer.seek(0)
        # StreamingResponse banakar file user ko csv file bhejte h as attachment with proper content type
        return StreamingResponse(buffer, media_type='text/csv', headers={"Content-Disposition": "attachment; filename=products.csv"})

    elif format in ['xls', 'xlsx']:
        # Excel file buffer m likhte h openpyxl engine ke sathhhh
        df.to_excel(buffer, index=False, engine='openpyxl')
        buffer.seek(0)
        return StreamingResponse(buffer, media_type='application/vnd.openxmlformats-officedocument.spreadsheetml.sheet', headers={"Content-Disposition": f"attachment; filename=products.{format}"})

    else:
        raise HTTPException(status_code=400, detail="Invalid file format requested. Use csv, xls, or xlsx.")
