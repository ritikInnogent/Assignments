
import logging

# Basic setup hai logging ka
logging.basicConfig(
    level=logging.INFO,  #isse info level aur usse upar ke sab logs display honge
    format="%(asctime)s - %(levelname)s - %(message)s",  # Har log me time, level, aur message hoga
    datefmt="%Y-%m-%d %H:%M:%S"  #Time ka format aur ye OPTIONAL hota hai
)


#we simply create a logger object so use this in otherfiles we simply import it 
logger = logging.getLogger(__name__)
