import pandas as pd
import matplotlib.pyplot as plt
import numpy as np

plt.rcParams['figure.dpi'] = 300

df = pd.read_csv('lennard.csv', 'r', names=['All'])
print("read")

df[['Total Energy', 'Potential Energy', 'Kinetic Energy']] = df['All'].str.split(',', n=2, expand=True)
df.drop(['All'], axis=1, inplace=True)

print("converting")

df['Total Energy'] = df['Total Energy'].astype(float)
df['Potential Energy'] = df['Potential Energy'].astype(float)
df['Kinetic Energy'] = df['Kinetic Energy'].astype(float)

print("building lists")

energy_list = df['Total Energy'].values
potential_list = df['Potential Energy'].values
kinetic_list = df['Kinetic Energy'].values


half = len(energy_list)/2
time = np.arange(0, half, 0.5)
print(len(time))

print("plotting")


fig, (ax1, ax2, ax3) = plt.subplots(3, 1)
fig.suptitle('Energy Over Time')

ax1.plot(time, potential_list)
ax1.set_ylabel("Potential")
ax1.axes.xaxis.set_ticklabels([])


ax2.plot(time, kinetic_list)
ax2.set_ylabel("Kinetic")
ax2.axes.xaxis.set_ticklabels([])


ax3.plot(time, energy_list)
ax3.set_ylabel("Total")
ax3.set_xlabel("Time")

plt.show()


