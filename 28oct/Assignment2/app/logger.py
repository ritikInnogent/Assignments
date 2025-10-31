
import logging
from datetime import datetime




logging.basicConfig(
    level=logging.INFO,     #isse info level aur usse upar ke sab logs display honge
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',  # Har log me time, level, aur message hoga
    handlers=[
        logging.FileHandler(f'api_logs_{datetime.now().strftime("%Y%m%d")}.log'),
        logging.StreamHandler()
    ]
)

#we simply create a logger object so use this in otherfiles we simply import it 

logger = logging.getLogger(__name__)