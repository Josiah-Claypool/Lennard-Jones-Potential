# Lennard-Jones-Potential

$$V_{LJ}(r) = \epsilon \left[ (\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$

$$F_{LJ}(r) = \epsilon \frac{6}{r} \left[ 2(\frac{\sigma}{r})^{12} - (\frac{\sigma}{r})^{6} \right]$$

$$ v_{x_{i + 1}} = v_{x_{i}} + \frac{m_{i}}{2}(F_{x_{i}} + F_{x_{i + 1}})\Delta t$$

$$ x_{i + 1} = x_{i} +  v_{x_{i}}\Delta t + \frac{F_{x_{i}}}{2m_{i}}\Delta t^{2}$$

$$ KE = \sum_{i}^{N}\frac{m_{i}}{2}(v_{x_{i}}^{2} + v_{y_{i}}^{2}) $$

$$ dx = x_{j} - x_{i}  $$

$$ dy = y_{j} - y_{i}  $$

$$ fx = F(r) \frac{dx}{r}  $$


$$ fy = F(r) \frac{dy}{r}  $$
