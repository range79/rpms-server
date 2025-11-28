#  Range Package manager Server

## A git repository backend  
- supports login
- adding likes to repositorys
- sending frend requests 
- follow users 


---

## 🐳 Docker Compose Support

A basic `docker-compose.yml` file is included for setting up MongoDB:

```yaml
services:
  mongodb:
    image: 'mongo:latest'
    environment:
      - MONGO_INITDB_DATABASE=something

    ports:
      - '27017:27017'

  postgres:
    image: 'postgres:latest'
    environment:
      - POSTGRES_DB=users
      - POSTGRES_USER=  #postgress user name  you need fil  it
      - POSTGRES_PASSWORD= #postgress password
    ports:
      - '5432:5432'

```
# if you don't know how to start docker-compose here example
```bash
docker-compose up -d
```

---

## 📬 Contact

For feedback, contributions, or questions, feel free to open an issue or submit a pull request. All help is welcome!

---

## 📝 Changelog

See [CHANGELOG.md](CHANGELOG.md) for full version history and updates.


# Contributors

👥 See the full list of amazing contributors [here](CONTRIBUTORS.md).



