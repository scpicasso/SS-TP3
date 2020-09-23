#!/usr/bin/env python3

import random
from math import sqrt

#rx ry vx vy mass radius 

def generate_file(name, num_particles, length, height, gap, mass, radius):
  file = open(name, 'w')
  file.write(str(num_particles) + '\n' + '\n')
  file.write(str(length) + '\n' + '\n')
  file.write(str(height) + '\n' + '\n')
  file.write(str(gap) + '\n' + '\n')
  particles = []
  for i in range(0, num_particles):
    i = 0
    x = random.uniform(0.0016, (length - 0.0016)/2)
    y = random.uniform(0.0016, height - 0.0016)
    while (i < len(particles)):
      if(sqrt((particles[i][0] - x)**2 + (particles[i][1] - y)**2) < radius*2):
        i = 0
      else:
        i = i + 1
      if(i == 0):
        x = random.uniform(0.0016, (length - 0.0016)/2)
        y = random.uniform(0.0016, height - 0.0016)

    x_velocity = random.uniform(-0.01,0.01)
    y_velocity = sqrt(0.01-(x_velocity**2))
    if(random.randint(0,1) == 1):
      y_velocity = y_velocity*-1

    particles.append([x,y])
    file.write(str(x) + '  ' + str(y) + '  ' + 
      str(x_velocity) + ' ' + str(y_velocity) + ' ' +
      str(mass) + ' ' + str(radius) + '\n')
  return

def generate_solids_file(gap):
  dfile = open('solid.xyz', 'w')
  dfile.write(str(int((0.09 - gap)*1000 +240*2 + 90*2 - 5)) + '\n' + '\n')
  for i in range(0, 240):
    dfile.write(str(float(i)/1000) + '  ' + '0' + '  ' + '0.0005' + '\n')
  for i in range(0, 240):
    dfile.write(str(float(i)/1000) + '  ' + '0.09' + '  ' + '0.0005' + '\n')
  for i in range(1, 89):
    dfile.write('0' + '  ' + str(float(i)/1000) + '  ' + '0.0005' + '\n')
  for i in range(1, 89):
    dfile.write('0.24' + '  ' + str(float(i)/1000) + '  ' + '0.0005' + '\n')
  if(gap>0):
    first_wall = (0.09 - (0.09 - gap)/2)*1000
    second_wall = ((0.09 - gap)/2)*1000
    for i in range(int(first_wall), 89):
      dfile.write('0.12' + '  ' + str(float(i)/1000) + '  ' + '0.0005' + '\n')
    for i in range(1, int(second_wall)):
      dfile.write('0.12' + '  ' + str(float(i)/1000) + '  ' + '0.0005' + '\n')



num_particles = input("Insert number of particles:\n")
try:
  part_int = int(num_particles)
  gap = input("Insert gap size:\n")
  try:
    gap_int = float(gap)
    generate_file('input.txt', part_int, 0.24, 0.09, gap_int, 1, 0.0015)
    generate_solids_file(gap_int)
  except ValueError:
    print("Invalid input")
except ValueError:
	print("Invalid input")