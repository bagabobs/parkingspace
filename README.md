## Description
The function of this project is to display a list of the nearest parking building locations and their parking occupancy status from a specified location. The parking building locations and their occupancy status are obtained from the Singapore government's API.

## Application Design
This application has three tasks, which is:
- Extracting information regarding the locations of parking buildings in Singapore from a CSV document obtained from the Singapore government website. The coordinates of these buildings are in the SVY21 format, thus requiring conversion to the commonly used standard format in this case, which is WGS84. This task will be run once when the application boot up.
- Populating data on available parking spaces. This data is obtained from the API provided by the Singapore government. There will be an API endpoint for populate the data and the application will populate the data once when the application boot up.
- Calculate the distance between the coordinates of the parking buildings and the coordinates input by the user. After the calculation, sort the results by the closest distance. There will be an API endpoint for this.

### Testing
For testing this application, we will use Testcontainers as the testing library for working with PostgreSQL.

### Project Structure
This project follows the hexagonal architecture, also known as the ports-and-adapters architecture, where in the hexagonal architecture, all components are loosely coupled and connected through ports and adapters. Ports are interfaces or contracts that interact with external resource or component. Adapters, on the other hand, Adapters are implementations of the contracts or interfaces that define by Ports.
 
We present this architecture through the following folder structure:
- domain : It contains the domain model of this application.
- application : It contains the services that utilize the domain.
  - port : Contains interfaces or contracts that define how an application interacts with external resources or external components.
    - in : Contains contracts or interfaces that drive the application or the incoming interface.
    - out : Contains contracts to get resources from external systems.
  - service : Contains the implementation of the incoming interface and uses the outgoing interface from the adapter.
- adapter : The implementation of the contracts defined by Ports.
  - in : The implementation of the contracts that drive the application.
  - out : The implementation of the contracts that fetch resources from external sources.

### How To Run The Application
1. Make sure you installed docker and it is running
2. Clone the repository.
```
git clone https://github.com/bagabobs/parkingspace.git
```
3. Go to the repository folder
```
cd parkingspace
```
4. Build the project
```
./mvnw install
```
5. Then run the application using docker compose
```
docker compose -f docker-compose.yml up
```
6. Wait for the application to populate the data
7. You can hit the API using
```
curl -i "http://www.localhost:8080/get_car_parks?latitude=1.3411134805883342&longitude=103.98265048254667&page=1&size=1"
```
