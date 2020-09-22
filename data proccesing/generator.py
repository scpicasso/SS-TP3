#!/usr/bin/env python3

import random
import math

#rx ry vx vy mass radius 

def generate_file(name, num_particles, length, height, mass, radius, gap):
	file = open(name, 'w')
	file.write(str(num_particles) + '\n' + '\n')
	file.write(str(length) + '\n')
	file.write(str(height) + '\n')
	file.write(str(gap) + '\n')
	for i in range(0, num_particles):
		x_velocity = random.uniform(-0.01,0.01)
		y_velocity = math.sqrt(0.01-(x_velocity**2))
		if(random.randint(0,1) == 1):
			y_velocity = y_velocity*-1

		file.write(str(random.uniform(0, (length - 1)/2)) + '  ' + 
		str(random.uniform(0, height - 1)) + '  ' + 
		str(x_velocity) + ' ' + str(y_velocity) + ' ' +
		str(mass) + ' ' + str(radius) + '\n')
	return

def generate_solids_file():
  dfile = open('solid.xyz', 'w')
  dfile.write('949' + '\n' + '\n')
  for i in range(0, 75):
    dfile.write('99' + '  ' + str(i) + '  ' + '64' + '\n')
  for i in range(126, 200):
    dfile.write('99' + '  ' + str(i) + '  ' + '64' + '\n')
  for i in range(0,200):
  	dfile.write('0' + '  ' + str(i) + '  ' + '64' + '\n')
  	dfile.write('199' + '  ' + str(i) + '  ' + '64' + '\n')
  	dfile.write(str(i) + '  ' + '0' + '  ' + '64' + '\n')
  	dfile.write(str(i) + '  ' + '199' + '  ' + '64' + '\n')

num_particles = input("Insert number of particles:\n")
try:
	part_int = int(num_particles)
	gap = input("Insert gap size:\n")
	try:
		gap_d = float(gap)
		generate_file('input.txt', part_int, 0.24, 0.09, 1, 0.0015, gap_d)
		generate_solids_file()
	except ValueError:
		print("Invalid input")
except ValueError:
	print("Invalid input")