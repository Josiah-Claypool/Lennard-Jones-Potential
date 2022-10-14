# Lennard-Jones-Potential


$$V_{LJ}(r) = \epsilon \left[ (\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$ 

$$F_{LJ}(r) = \epsilon \frac{6}{r} \left[ 2(\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$

$$ v_{{t + \Delta t}} = v_{t} + \frac{m (F_{t} + F_{t + \Delta t})\Delta t}{2}$$



$$ x_{t + \Delta t} = x_{t} +  v_{t}\Delta t + \frac{F_{t}\Delta t^{2}}{2m}$$

$$ KE = \sum_{i}^{N}\frac{m_{i}}{2}(v_{x_{i}}^{2} + v_{y_{i}}^{2}) $$

$$ dx = x_{j} - x_{i}  $$

$$ dy = y_{j} - y_{i}  $$

$$ fx = F(r) \frac{dx}{r}  $$


$$ fy = F(r) \frac{dy}{r}  $$


**1.** A desired amount of molecules are initialized with positions and a distribution of velocities. The forces on each molecule are calculated from these positions. The kinetic, potential, and total energy of the system is calculated and are stored for graphing later.

**2.** The molecule positions are visually represented on a JPanel that will be updated for animation. 

**3.** A step forward in time is taken.

**4.** The positions are updated from the forces and velocities.

**5.** Boundary conditions are checked to ensure that molecules stay within the defined box size.

**6.** The forces are updated from the new positions.

**7.** The velocities are updated from the new forces and the last set of forces. 

**8.** The kinetic, potential, and total energy are calculated and stored.

**9.** The JPanel is updated with the new positions.

**10.** Returns to step 3 until the total time is reached.

The kinetic, potential, and total energy values are exported as to ***lennard.csv*** and can be graphed in python with ***lennard_jones.py***

-------

[Here is a video that has two different runs next to each other.](https://www.youtube.com/watch?v=CZ2V0Xi6pXI)

Below are the energies of two different runs with 12 molecules with initial velocities.

![](https://i.imgur.com/WYOfafv.png)

![](https://i.imgur.com/UhjjT7E.png)
