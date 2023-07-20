# REST API

Backend

## Prerequisites

+ Docker https://www.docker.com/

### Docker Installation Tutorials:
+ For Mac Users: https://www.youtube.com/watch?v=gcacQ29AjOo
+ For Windows 11: https://www.youtube.com/watch?v=AAWNQ2wDVAg
+ For Windows 10:https://www.youtube.com/watch?v=5nX8U8Fz5S0
+ For Linux/Ubuntu: https://www.youtube.com/watch?v=TDLKQWsrSyk

## Setup backend environment
**0. Open cmd with "Run as administrator"**


**1. Clone the repository**

```bash
git clone https://gitlab.mentormate.bg/base/mmu/foundation/yellow-squad-project/back-end.git
```

**2.Navigate to project directory**
```bash
cd <path to project>
```
replace < path to project > with the path where you cloned the repository



**3. After navigating to the project directory, run the backend with docker compose**

```bash
docker-compose up -d
```
REST API will start running at <http://localhost:8080>

**4. You can stop the backend from running with**

```bash
docker-compose down
```

