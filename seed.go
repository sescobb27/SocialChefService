package main

import (
	"crypto/sha256"
	"database/sql"
	"fmt"
	_ "github.com/lib/pq"
	"io"
	"log"
	"os"
	"time"
)

const (
	db_name           string = "socialchef_test"
	connection_format string = "user=%s dbname=%s sslmode=disable password=%s host=%s"
)

func assertNoError(err error) {
	if err != nil {
		log.Fatal(err)
	}
}

func encryptPassword(data []string) string {
	hash := sha256.New()
	for _, v := range data {
		io.WriteString(hash, v)
	}
	return fmt.Sprintf("%x", hash.Sum(nil))
}

func stablishConnection() (*sql.DB, error) {
	user := os.Getenv("POSTGRESQL_USER")
	pass := os.Getenv("POSTGRESQL_PASS")
	host := os.Getenv("PGHOST")
	connection_params := fmt.Sprintf(connection_format, user, db_name, pass, host)
	db, err := sql.Open("postgres", connection_params)

	assertNoError(err)
	return db, nil
}

func insertUsers(user *User) {
	query := `insert into users
    (id, created_at, email, lastname, name, password_hash, rate, username)
values
    ($1,$2,$3,$4,$5,$6,$7,$8)`
	db, err := stablishConnection()
	assertNoError(err)
	defer db.Close()

	_, err = db.Exec(query, user.Id, user.Created_at, user.Email, user.Last_name,
		user.Name, user.Password, user.Rate, user.Username)
	assertNoError(err)
}

type User struct {
	Id                                         int
	Name, Last_name, Username, Email, Password string
	Rate                                       float32
	Created_at                                 time.Time
}

func makeUsers() {
	names := []string{"Simon", "Edgardo", "Juan", "Camilo"}
	last_names := []string{"Escobar", "Sierra", "Norenia", "Mejia"}
	usernames := []string{"sescob", "easierra", "jknore", "jcmejia"}
	emails := []string{"sescob@gmail.com", "easierra@gmail.com",
		"jknore@gmail.com", "jcmejia@gmail.com"}
	passwords := []string{"qwerty", "123456", "AeIoU!@",
		"S3CUR3P455W0RD!\"#$%&/()="}

	for i := 0; i < 4; i++ {
		now := time.Now()
		data := []string{passwords[i], now.String()}
		p := encryptPassword(data)
		u := &User{i, names[i], last_names[i], usernames[i], emails[i], p, 0.0, now}
		insertUsers(u)
	}
}

type Product struct {
	Id          int
	Created_at  time.Time
	Description string
	Name        string
	Price       float64
	Rate        float32
	Image       string
	Chef_id     int
}

func insertProducts(p *Product) {
	query := `insert into products
      (id, created_at, description, image, name, price, rate, chef_id)
  values
      ($1,$2,$3,$4,$5,$6,$7,$8)`
	db, err := stablishConnection()
	assertNoError(err)
	defer db.Close()

	_, err = db.Exec(query, p.Id, p.Created_at, p.Description, p.Image, p.Name,
		p.Price, p.Rate, p.Chef_id)
	assertNoError(err)
}

func makeProducts() {
	names := []string{"plato1", "plato2", "plato3", "plato4"}
	// categories := []string{"pastas", "carne", "ensalada", "bandeja"}
	// locations := []string{"Poblado", "Laureles", "Envigado", "Sabaneta"}
	descriptions := []string{"Descripcion1", "Descripcion2",
		"Descripcion3", "Descripcion4"}
	prices := []float64{18500.0, 12300.0, 5000.0, 7300.0}
	images := []string{"images/default.png",
		"images/default.png", "images/default.png", "images/default.png"}
	rates := []float32{1.9, 2.5, 3.2, 4.8}

	for i := 0; i < 4; i++ {
		p := &Product{i, time.Now(), descriptions[i], names[i], prices[i], rates[i],
			images[i], i}
		insertProducts(p)
	}
}

func restore() {
	db, err := stablishConnection()
	assertNoError(err)
	defer db.Close()

	_, err = db.Exec("truncate table users restart identity CASCADE")
	assertNoError(err)
	_, err = db.Exec("truncate table products restart identity CASCADE")
	assertNoError(err)
}

func main() {
	restore()
	makeUsers()
	makeProducts()
}
