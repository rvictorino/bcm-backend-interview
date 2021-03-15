#BCM Backend Interview - Robin Victorino

## General information

2-hour commit: [99bc511](https://github.com/rvictorino/bcm-backend-interview/commit/99bc511e38ede264e349ffa94638f82bfeccc03c)

Since 2 hour passed before I was able to implement clients for all powerplants APIs, sum has been simulated by requesting data from Hawes twice.

## Requirement
 - JDK 8

## Usage

### Clone and compile
 - clone directory and `cd` into it
 - launch command `./gradlew build`

### Execute
 - `java -jar ./build/libs/bcm-backend-interview-1.0-SNAPSHOT.jar <from> <to> <json|csv>`

## TODO
Thoughts about what can be improved:
 - [ ] Implement clients for Barnsley and Hounslow APIs
 - [ ] Implement output formatter for CSV
 - [ ] Implement data filling when APIs fail
 - [ ] Implement error handling, choose a strategy for failing API calls
 - [ ] Add some logging, improve user interaction (error/success messages)
 - [ ] Implement arguments validation
 - [ ] Add Unit Tests
 - [ ] Think about what could be refactored after all APIs clients are implemented (template pattern ?)
 - [ ] Load all static data (e. g.: API endpoints) from some config file
 - [ ] Refactor aggregation to move sum operation to a more relevant place
 - [ ] Extract output formatting strategy to a proper class
 - [ ] Rethink about how dependencies are used - what could be improved in regard to that