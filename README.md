# What is Done
Flight Data Management (FDM) application is created as part of this maven project.
Both below case studies are implemented in this project:

Step 1: Create a RESTful API for Flight Data

Step 2: Integrating CrazySupplier Data

# List of API end-points

1. Integrating CrazySupplier Data - (stubbed data)

   GET: http://localhost:8080/v2/crazy-api/flights?from=ATL&to=AMS&outboundDate=2025-06-20T00:00:00Z&inboundDate=2025-08-05T00:00:00Z

2. Get filtered - Flight details - Both from DB and CrazySupplierData

   POST: http://localhost:8080/v1/flights

3. Add New Flight entry

   POST: http://localhost:8080/v2/crazy-api/flights?from=ATL&to=AMS&outboundDate=2025-06-20T00:00:00Z&inboundDate=2025-08-05T00:00:00Z

4. Update an existing flight entry

   PATCH: http://localhost:8080/v1/flight/{id}

5. Delete an Entry

   DELETE: http://localhost:8080/v1/flight/{id}

# Postman collection
   /postman/CaseStudy 1 and 2 collection.postman_collection.json





