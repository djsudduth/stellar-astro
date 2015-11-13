# stellar-astro
Stellar Astrophysics code

# Polytropes
This java application utilizes the Runge-Kutta fourth order numerical method to solve the Lane-Emden equation.

Packaged application: Polytrope.jar

Input Parameters:
- Number of iterations (required)
- Iteration step size (required)
- Polytropic index (required)
- Output data file path (optional)  NOTE: file is comma delimited with a header
- Output file data skip value (optional)  NOTE: this value will output only every (total_iterations / skip_value)

Usage:
> "java -jar Polytrope.jar 70000 0.0001 3.0 c:/astro/data/polytropicindex3.csv 1500"

(in this example there are 70000 iterations are calculated for a polytropic index of 3.0 but only ~1500 values will be output to the data file located at the desired path)



