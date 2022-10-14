# Lennard-Jones-Potential

The Lennard-Jones potential describes the behavior of neutral intermolecular movements. At close distances the particles have a severe repulsive force. When nearby they experience an attractive force. The potential is:

$$V_{LJ}(r) = 4\epsilon \left[ (\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$ 

$r$ is the distance between the particles and $\epsilon$ is the potential well depth. At distance $\sigma$ the potential is 0.

The force is:

$$F_{LJ}(r) = \epsilon \frac{24}{r} \left[ 2(\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$

This program uses the velocity Verlet algorithm to update the positions and velocities:

$$v_{{t + \Delta t}} = v_{t} + \frac{m (F_{t} + F_{t + \Delta t})\Delta t}{2}$$

$$ x_{t + \Delta t} = x_{t} +  v_{t}\Delta t + \frac{F_{t}\Delta t^{2}}{2m}$$


The components of the force are calculated by:

$$ fx = F(r) \frac{dx}{r}  $$

Where $dx$ is the distance between the particles in the x dimension . 

This program follows these steps.

**1.** A desired amount of particles are initialized with positions and velocities from a distribution. The forces on each particle are calculated from these positions. The kinetic, potential, and total energy of the system are calculated and stored for graphing later. These particles are confined to a box of user set length.

**2.** The particle positions are visually represented on a JPanel that will be updated for animation. 

**3.** A step forward in time is taken.

**4.** The positions are updated from the forces and velocities.

**5.** Boundary conditions are checked to ensure that particles stay within the defined box size.

**6.** The forces are updated from the new positions.

**7.** The velocities are updated from the new forces and the last set of forces. 

**8.** The kinetic, potential, and total energy are calculated and stored.

**9.** The JPanel is updated with the new positions.

**10.** Returns to step 3 until the total time is reached.

The kinetic, potential, and total energy values are exported to ***lennard.csv*** and can be graphed in python with ***lennard-jones.py***

For more details see the wikipedia articles on the [Lennard-Jones potential](https://en.wikipedia.org/wiki/Lennard-Jones_potential), and the [velocity Verlet algorithm](https://en.wikipedia.org/wiki/Verlet_integration#Velocity_Verlet).

-------

[Here is a video that has two different runs next to each other.](https://www.youtube.com/watch?v=CZ2V0Xi6pXI)

Below are the energies of two different runs with 12 particles with initial velocities.

![](https://i.imgur.com/WYOfafv.png)

![](https://i.imgur.com/UhjjT7E.png)
