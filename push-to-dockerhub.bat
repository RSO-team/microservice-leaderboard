docker build -t lgaljo/rt_basketball_leaderboard -f Dockerfile_with_maven_build .
docker tag lgaljo/rt_basketball_leaderboard lgaljo/rt_basketball_leaderboard:latest
docker push -a lgaljo/rt_basketball_leaderboard