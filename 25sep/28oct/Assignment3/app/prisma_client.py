from prisma import Prisma

prisma = Prisma()   #prisma client instance

async def connect(): #initiate database connection
    await prisma.connect()

async def disconnect(): #close
    await prisma.disconnect()
