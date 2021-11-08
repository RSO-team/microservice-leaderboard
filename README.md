# RSO: Microservice Leaderboard

Microservice which manages leaderboard in our service

## Prerequisites

```bash
docker run -d --name pg-leaderboard -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=leaderboard -p 5432:5432 postgres:13
```