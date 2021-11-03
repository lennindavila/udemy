const express = require('express');
//const bodyParser = require('body-parser'); //body-parser is deprecated
const cors = require('cors');

const app = express();

var corsOption = {
    origin: "http://localhost:3001"
}

app.use(cors(corsOption));

//parse request of content-type - application/json
app.use(express.json()); //bodyParser.json() is deprecated

//parse request of content-type - application/x-www-form-urlencoded
app.use(express.urlencoded({extended:true})); //bodyParser.urlencoded() is deprecated

const db = require('./app/models');
db.sequelize.sync();

//para borrar las tablas si ya existen
/*
db.sequelize.sync({force:true}).then( () => {
    console.log("Eliminamos tablas si ya existen")
} );
*/
//simple route
app.get("/",(req,res) => {
    res.json({message:"Bienvenido a mi aplicacion"});
} );

//set port, listen for requests
const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
    console.log(`Servidor esta corriendo en el puerto ${PORT}`);
});