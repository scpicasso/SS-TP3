import glob
import re

value = 0.02
data_files = sorted(glob.glob("output.*.txt$"))
print(data_files)
time = 0.0
files = []

while(value < 27.60):
	min_diff = 1000000000
	chosen = ""
	for filename in data_files:
		filenum = int(re.search("_([0-9]+).txt", filename).group(1))
		print(filenum)
    	if (min_diff > abs(filenum - value)):
    		min_diff = abs(filenum - value)
    		chosen = filename
		
	references[i] = chosen
	i = i+1
	time = time + 0.02

filesshutil.copy(os.path.join(folders, filename), destination_folder)

