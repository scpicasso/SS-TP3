import glob
import re
import shutil
import os

data_files = sorted(glob.glob("output*.txt"))
time = 0.0
files = []
i = 0

while(time < 27.50):
	diff = 1000000000
	chosen = ""
	for filename in data_files:
		filenum = float(re.search('\d+.\d+|\d+', filename).group(0))
		value = abs(filenum - time)
		if (diff > value):
			diff = value
			chosen = filename
	files.append(chosen)
	time = time + 0.02
for f in files:
	shutil.copy(f, "simulation")
	old_name = "simulation/" + f
	new_name = "simulation/output" + str(i)
	os.rename(old_name,new_name)
	i = i + 1


